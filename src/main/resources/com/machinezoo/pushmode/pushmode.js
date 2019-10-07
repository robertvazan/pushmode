"use strict";

var PushMode = PushMode || new function() {
	var version = "VERSION";
	var submitter = new Submitter();
	var listeners = new ListenerManager();
	var patcher = new Patcher();
	var poller = new Poller();
	var inputSync = new InputSynchronization();
	var streamId;

	function XhrLoop(servlet, processor) {
		var xhr = null;
		var currentData = null;
		var healthy = false;
		var delay = 10;
		
		function reload() {
			location.replace(location.href);
		}
		
		function backoff() {
			delay *= 1.2;
			if (delay > 10000)
				delay = 10000;
			setTimeout(retry, delay);
		}
		
		function processResponse(args) {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					healthy = true;
					currentData = null;
					var response = xhr.responseText;
					xhr = null;
					processor(JSON.parse(response));
				} else if (xhr.status === 204)
					retry();
				else if (healthy && xhr.status == 205)
					setTimeout(reload, 1);
				else if (xhr.status == 205 || xhr.status === 404 || xhr.status === 405 || xhr.status === 403)
					setTimeout(reload, 10000);
				else if (xhr.status == 0)
					backoff();
				else
					setTimeout(retry, 10000);
			} else
				backoff();
		}
		
		function retry() {
			xhr = new XMLHttpRequest()
			xhr.addEventListener("loadend", processResponse);
			xhr.open('POST', "/pushmode" + servlet + "?v=" + version, true);
			xhr.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
			xhr.send(currentData);
		}
		
		this.active = function() {
			return currentData !== null;
		};
		
		this.send = function(data) {
			if (this.active())
				throw "Concurrent XHR query execution";
			currentData = JSON.stringify(data);
			retry();
		};
	};

	function InputSynchronization(spec) {
		var confirmedSeq = 0;
		var pending = {};
		
		this.await = function(key, editSeq, callback) {
			if (editSeq < confirmedSeq) {
				callback();
				delete pending[key];
			} else {
				pending[key] = {
					seq: editSeq,
					fn: callback
				};
			}
		};
		
		this.remove = function(key) {
			delete pending[key];
		}
		
		this.acknowledge = function(ackSeq) {
			confirmedSeq = ackSeq;
			var keys = Object.keys(pending);
			for (var i = 0; i < keys.length; ++i) {
				var key = keys[i];
				if (pending[key].seq < confirmedSeq) {
					pending[key].fn();
					delete pending[key];
				}
			}
		};
	}
	
	function Poller() {
		var xhr = new XhrLoop("/poller", process);
		var outputSeq = 0;

		function process(response) {
			if (response.o > outputSeq) {
				outputSeq = response.o;
				patcher.patch(response);
				inputSync.acknowledge(response.i);
			}
			setTimeout(refresh, 10);
		}
		
		function refresh() {
			var message = {
				s: streamId,
				o: outputSeq + 1
			};
			xhr.send(message);
		}

		this.start = function() {
			refresh();
		};
	};
	
	function Submitter() {
		var xhr = new XhrLoop("/submit", confirm);
		var eventQueue = [];
		var inputSeq = 0;
		var inFlight = 0;

		function confirm(response) {
			inputSeq += inFlight;
			inFlight = 0;
			flush();
		}
		
		function submit() {
			var events = eventQueue;
			eventQueue = [];
			inFlight = events.length;
			xhr.send({
				s: streamId,
				i: inputSeq,
				e: events
			});
		}

		function flush() {
			if (eventQueue.length > 0 && !xhr.active())
				submit();
		}
		
		this.last = function() {
			if (eventQueue.length > 0)
				return eventQueue[eventQueue.length - 1];
			return null;
		};
		
		this.push = function(event) {
			eventQueue.push(event);
			setTimeout(flush, 0);
			return inputSeq + inFlight + eventQueue.length - 1;
		};
	}
	
	function BindingListener(spec) {
		var element = document.getElementById(spec.e);
		var accepted = null;
		var forced = null;
		var editSeq = -1;
		this.spec = spec;
		this.serialize = null;
		this.deserialize = null;
		
		function key() {
			return spec.a + ":" + spec.e;
		}
		
		function reconcile() {
			 if (element[spec.a] !== forced)
				 element[spec.a] = accepted = forced;
		}
		
		this.push = function() {
			var last = submitter.last();
			if (last !== null && last.t === "b" && last.e === spec.e && last.a === spec.a)
				last.v = this.serialize(accepted);
			else {
				editSeq = submitter.push({
					t: "b",
					e: spec.e,
					a: spec.a,
					v: this.serialize(accepted)
				});
				inputSync.await(key(), editSeq, reconcile);
			}
		};
		
		this.update = function() {
			var updated = element[spec.a];
			if (updated !== accepted) {
				accepted = updated;
				this.push();
			}
		};

		this.initialize = function() {
			forced = accepted = this.deserialize(spec.v);
			this.update();
		};
		
		this.remove = function() {
			inputSync.remove(key());
		}
		
		this.coerce = function(value) {
			this.update();
			forced = this.deserialize(value);
			inputSync.await(key(), editSeq, reconcile);
		};
	}
	
	function StringBindingListener(spec) {
		BindingListener.call(this, spec);

		this.serialize = function(value) {
			return value;
		};
		
		this.deserialize = function(serialized) {
			return serialized;
		};
	}
	
	function BooleanBindingListener(spec) {
		BindingListener.call(this, spec);

		this.serialize = function(value) {
			return value.toString();
		};
		
		this.deserialize = function(serialized) {
			return serialized === "true";
		};
	}
	
	function EventListener(spec) {
		var element = document.getElementById(spec.e);

		function handle() {
			listeners.update(spec.e);
			if (!spec.v) {
				submitter.push({
					t: "e",
					e: spec.e,
					n: spec.n
				});
			}
		};
		
		this.remove = function() {
			element.removeEventListener(spec.n, this.listener, false);
		};

		this.listener = handle.bind(this);
		element.addEventListener(spec.n, this.listener, false);
	}
	
	function MultiMap() {
		var map = {};
		
		this.add = function(key, value) {
			var list = map[key];
			if (list === undefined)
				map[key] = list = [];
			list.push(value);
		};
		
		this.remove = function(key, value) {
			var list = map[key];
			if (list !== undefined) {
				var index = list.indexOf(value);
				if (index >= 0) {
					list.splice(index, 1);
					if (list.length === 0)
						delete map[key];
				}
			}
		};
		
		this.get = function(key) {
			var list = map[key];
			if (list !== undefined)
				return list;
			else
				return [];
		};
	}
	
	function ListenerManager() {
		var events = {};
		var bindings = new MultiMap();
		
		function eventKey(spec) {
			return spec.n + ":" + spec.e;
		}

		function findBinding(spec) {
			var list = bindings.get(spec.e);
			for (var i = 0; i < list.length; ++i)
				if (list[i].spec.a === spec.a)
					return list[i];
			throw "No such binding";
		}
		
		this.add = function(spec) {
			if (spec.t === "e")
				events[eventKey(spec)] = new EventListener(spec);
			else if (spec.t === "b") {
				var listener;
				if (spec.d === "b")
					listener = new BooleanBindingListener(spec);
				else
					listener = new StringBindingListener(spec);
				listener.initialize();
				bindings.add(spec.e, listener);
			} else
				throw "Unknown listener type";
		};
		
		this.remove = function(spec) {
			if (spec.t === "e") {
				events[eventKey(spec)].remove();
				delete events[eventKey(spec)];
			} else if (spec.t === "b") {
				var binding = findBinding(spec);
				binding.remove();
				bindings.remove(spec.e, binding);
			} else
				throw "Unknown listener type";
		};
		
		this.coerce = function(spec) {
			var binding = findBinding(spec);
			binding.coerce(spec.v);
		}
		
		this.update = function(elementId) {
			var list = bindings.get(elementId);
			for (var i = 0; i < list.length; ++i)
				list[i].update();
		}
	}

	function Patcher() {
		function setAttribute(element, params) {
			element.setAttribute(params.n, params.v);
		}

		function clearAttribute(element, name) {
			element.removeAttribute(name);
		}
		
		function insertNode(element, at, node) {
			if (at < element.childNodes.length)
				element.insertBefore(node, element.childNodes[at]);
			else
				element.appendChild(node);
		}
		
		function insertElement(element, params) {
			var template = document.createElement('template');
			template.innerHTML = params.h;
			insertNode(element, params.i, template.content ? template.content : template.firstChild);
		}
		
		function insertText(element, params) {
			insertNode(element, params.i, document.createTextNode(params.t));
		}

		function deleteNode(element, at) {
			element.removeChild(element.childNodes[at]);
		}

		function patchChild(element, at, iterator) {
			patchElement(element.childNodes[at], iterator);
		}
		
		function PatchIterator(types, params) {
			var typesAt = 1;
			var paramsAt = 1;

			this.popTypes = function() {
				return types.charAt(typesAt++);
			}
			
			this.popParams = function() {
				return params[paramsAt++];
			}
		}
		
		function patchElement(element, iterator) {
			while (true) {
				switch (iterator.popTypes()) {
				case 'a':
					setAttribute(element, iterator.popParams());
					break;
				case 'c':
					clearAttribute(element, iterator.popParams());
					break;
				case 'e':
					insertElement(element, iterator.popParams());
					break;
				case 't':
					insertText(element, iterator.popParams());
					break;
				case 'd':
					deleteNode(element, iterator.popParams());
					break;
				case 'p':
					patchChild(element, iterator.popParams(), iterator);
					break;
				case 'f':
					return;
				}
			}
		}
		
		this.patch = function(update) {
			var unsubscribe = update.u;
			for (var i = 0; i < unsubscribe.length; ++i)
				listeners.remove(unsubscribe[i]);
			if (update.t.length > 0)
				patchElement(document.documentElement, new PatchIterator(update.t, update.p));
			var subscribe = update.s;
			for (var i = 0; i < subscribe.length; ++i)
				listeners.add(subscribe[i]);
			var coerce = update.c;
			for (var i = 0; i < coerce.length; ++i)
				listeners.coerce(coerce[i]);
		};
	}

	function start() {
		streamId = document.documentElement.getAttribute("data-pushmode-stream");
		setTimeout(function() { poller.start() }, 10);
	}
	
	if (document.readyState == "complete" || document.readyState == "interactive")
		start();
	else
		document.addEventListener("DOMContentLoaded", start, false);
};
