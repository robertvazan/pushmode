# Part of PushMode: https://pushmode.machinezoo.com
import os
import textwrap
import contextlib
import re
import string

# Attempting to use relative paths would mess up the file tree depending on where the script runs.
# We will instead locate java sources relative to the running script.

rootdir = os.path.dirname(os.path.realpath(__file__))
sources = rootdir + '/src/main/java/com/machinezoo/pushmode/dom'

# Let's start by creating a knowledge base of information about HTML and SVG.
# This data was manually collected from various online sources.

html_elements = [
    'a',
    'abbr',
    'address',
    'area',
    'article',
    'aside',
    'audio',
    'b',
    'base',
    'bdi',
    'bdo',
    'blockquote',
    'body',
    'br',
    'button',
    'canvas',
    'caption',
    'cite',
    'code',
    'col',
    'colgroup',
    'data',
    'datalist',
    'dd',
    'del',
    'details',
    'dfn',
    'div',
    'dl',
    'dt',
    'em',
    'embed',
    'fieldset',
    'figcaption',
    'figure',
    'footer',
    'form',
    'h1',
    'h2',
    'h3',
    'h4',
    'h5',
    'h6',
    'head',
    'header',
    'hr',
    'html',
    'i',
    'iframe',
    'img',
    'input',
    'ins',
    'kbd',
    'label',
    'legend',
    'li',
    'link',
    'main',
    'map',
    'mark',
    'meta',
    'meter',
    'nav',
    'noscript',
    'object',
    'ol',
    'optgroup',
    'option',
    'output',
    'p',
    'param',
    'picture',
    'pre',
    'progress',
    'q',
    'rp',
    'rt',
    'rtc',
    'ruby',
    's',
    'samp',
    'script',
    'section',
    'select',
    'slot',
    'small',
    'source',
    'span',
    'strong',
    'style',
    'sub',
    'summary',
    'sup',
    'table',
    'tbody',
    'td',
    'template',
    'textarea',
    'tfoot',
    'th',
    'thead',
    'time',
    'title',
    'tr',
    'track',
    'u',
    'ul',
    'var',
    'video',
    'wbr',
]

svg_elements = [
    'a',
    'altGlyph',
    'altGlyphDef',
    'altGlyphItem',
    'animate',
    'animateColor',
    'animateMotion',
    'animateTransform',
    'circle',
    'clipPath',
    'color-profile',
    'cursor',
    'defs',
    'desc',
    'ellipse',
    'feBlend',
    'feColorMatrix',
    'feComponentTransfer',
    'feComposite',
    'feConvolveMatrix',
    'feDiffuseLighting',
    'feDisplacementMap',
    'feDistantLight',
    'feFlood',
    'feFuncA',
    'feFuncB',
    'feFuncG',
    'feFuncR',
    'feGaussianBlur',
    'feImage',
    'feMerge',
    'feMergeNode',
    'feMorphology',
    'feOffset',
    'fePointLight',
    'feSpecularLighting',
    'feSpotLight',
    'feTile',
    'feTurbulence',
    'filter',
    'font',
    'font-face',
    'font-face-format',
    'font-face-name',
    'font-face-src',
    'font-face-uri',
    'foreignObject',
    'g',
    'glyph',
    'glyphRef',
    'hkern',
    'image',
    'line',
    'linearGradient',
    'marker',
    'mask',
    'metadata',
    'missing-glyph',
    'mpath',
    'path',
    'pattern',
    'polygon',
    'polyline',
    'radialGradient',
    'rect',
    'script',
    'set',
    'stop',
    'style',
    'svg',
    'switch',
    'symbol',
    'text',
    'textPath',
    'title',
    'tref',
    'tspan',
    'use',
    'view',
    'vkern',
]

html_attributes = [
    'accept',
    'accept-charset',
    'accesskey',
    'action',
    'allowfullscreen',
    'alt',
    'async',
    'autocapitalize',
    'autocomplete',
    'autofocus',
    'autoplay',
    'charset',
    'checked',
    'cite',
    'class',
    'cols',
    'colspan',
    'content',
    'contenteditable',
    'contextmenu',
    'controls',
    'coords',
    'crossorigin',
    'data',
    'datetime',
    'default',
    'defer',
    'dir',
    'disabled',
    'download',
    'draggable',
    'dropzone',
    'enctype',
    'for',
    'form',
    'formaction',
    'formmethod',
    'formnovalidate',
    'formtarget',
    'headers',
    'height',
    'hidden',
    'high',
    'href',
    'hreflang',
    'http-equiv',
    'icon',
    'inputmode',
    'integrity',
    'ismap',
    'kind',
    'label',
    'lang',
    'language',
    'list',
    'loop',
    'low',
    'max',
    'maxlength',
    'media',
    'method',
    'min',
    'minlength',
    'multiple',
    'muted',
    'name',
    'novalidate',
    'open',
    'optimum',
    'pattern',
    'ping',
    'placeholder',
    'poster',
    'preload',
    'radiogroup',
    'readonly',
    'required',
    'reversed',
    'rel',
    'role',
    'rows',
    'rowspan',
    'sandbox',
    'scope',
    'selected',
    'selectionDirection',
    'selectionEnd',
    'selectionStart',
    'shape',
    'size',
    'sizes',
    'slot',
    'span',
    'spellcheck',
    'src',
    'srcdoc',
    'srclang',
    'srcset',
    'start',
    'step',
    'style',
    'tabindex',
    'target',
    'title',
    'type',
    'typemustmatch',
    'usemap',
    'value',
    'volume',
    'width',
    'wrap',
]

svg_attributes = [
    'accumulate',
    'additive',
    'alignment-baseline',
    'amplitude',
    'attributeName',
    'attributeType',
    'azimuth',
    'baseFrequency',
    'baseline-shift',
    'begin',
    'bias',
    'calcMode',
    'class',
    'clipPathUnits',
    'clip-path',
    'clip-rule',
    'color',
    'color-interpolation',
    'color-interpolation-filters',
    'color-profile',
    'color-rendering',
    'cursor',
    'cx',
    'cy',
    'd',
    'diffuseConstant',
    'direction',
    'display',
    'divisor',
    'dominant-baseline',
    'dur',
    'dx',
    'dy',
    'edgeMode',
    'elevation',
    'end',
    'externalResourcesRequired',
    'fill',
    'fill-opacity',
    'fill-rule',
    'filter',
    'filterUnits',
    'flood-color',
    'flood-opacity',
    'font-family',
    'font-size',
    'font-size-adjust',
    'font-stretch',
    'font-size',
    'font-variant',
    'font-weight',
    'from',
    'fr',
    'fx',
    'fy',
    'gradientTransform',
    'gradientUnits',
    'height',
    'href',
    'image-rendering',
    'in',
    'in2',
    'k1',
    'k2',
    'k3',
    'k4',
    'kernelMatrix',
    'kernelUnitLength',
    'kerning',
    'keySplines',
    'keyTimes',
    'letter-spacing',
    'lighting-color',
    'limitingConeAngle',
    'local',
    'marker-end',
    'marker-mid',
    'marker-start',
    'markerHeight',
    'markerUnits',
    'markerWidth',
    'mask',
    'maskContentUnits',
    'maskUnits',
    'max',
    'min',
    'mode',
    'numOctaves',
    'opacity',
    'operator',
    'order',
    'overflow',
    'overline-position',
    'overline-thickness',
    'paint-order',
    'pathLength',
    'patternContentUnits',
    'patternTransform',
    'patternUnits',
    'pointer-events',
    'points',
    'pointsAtX',
    'pointsAtY',
    'pointsAtZ',
    'preserveAlpha',
    'preserveAspectRatio',
    'primitiveUnits',
    'r',
    'radius',
    'refX',
    'refy',
    'repeatCount',
    'repeatDur',
    'requiredFeatures',
    'restart',
    'result',
    'rx',
    'ry',
    'scale',
    'seed',
    'shape-rendering',
    'specularConstant',
    'specularExponent',
    'stdDeviation',
    'stitchTiles',
    'stop-color',
    'stop-opacity',
    'strikethrough-position',
    'strikethrough-thickness',
    'stroke',
    'stroke-dasharray',
    'stroke-dashoffset',
    'stroke-linecap',
    'stroke-linejoin',
    'stroke-miterlimit',
    'stroke-opacity',
    'stroke-width',
    'style',
    'surfaceScale',
    'systemLanguage',
    'tabindex',
    'targetX',
    'targetY',
    'text-anchor',
    'text-decoration',
    'text-rendering',
    'textLength',
    'to',
    'transform',
    'type',
    'underline-position',
    'underline-thickness',
    'values',
    'vector-effect',
    'version',
    'viewBox',
    'visibility',
    'width',
    'word-spacing',
    'writing-mode',
    'x',
    'x1',
    'x2',
    'xChannelSelector',
    'y',
    'y1',
    'y2',
    'yChannelSelector',
    'z',
]

attributes = sorted(set(html_attributes) | set(svg_attributes))

boolean_attributes = [
    'async',
    'autofocus',
    'autoplay',
    'checked',
    'controls',
    'default',
    'defer',
    'disabled',
    'download',
    'formnovalidate',
    'hidden',
    'ismap',
    'loop',
    'multiple',
    'muted',
    'novalidate',
    'open',
    'ping',
    'readonly',
    'required',
    'reversed',
    'selected',
    'typemustmatch',
]

integer_attributes = [
    'cols',
    'colspan',
    'font-weight',
    'height',
    'maxlength',
    'minlength',
    'numOctaves',
    'order',
    'rows',
    'rowspan',
    'selectionEnd',
    'selectionStart',
    'size',
    'span',
    'start',
    'tabindex',
    'width',
]

double_attributes = [
    'amplitude',
    'azimuth',
    'baseFrequency',
    'baseline-shift',
    'bias',
    'cx',
    'cy',
    'diffuseConstant',
    'divisor',
    'dx',
    'dy',
    'elevation',
    'fill-opacity',
    'flood-opacity',
    'font-size-adjust',
    'fr',
    'fx',
    'fy',
    'height',
    'high',
    'k1',
    'k2',
    'k3',
    'k4',
    'kernelUnitLength',
    'kerning',
    'letter-spacing',
    'limitingConeAngle',
    'low',
    'markerHeight',
    'markerWidth',
    'max',
    'min',
    'opacity',
    'optimum',
    'overline-position',
    'overline-thickness',
    'pathLength',
    'pointsAtX',
    'pointsAtY',
    'pointsAtZ',
    'r',
    'radius',
    'refX',
    'refy',
    'repeatCount',
    'rx',
    'ry',
    'scale',
    'seed',
    'specularConstant',
    'specularExponent',
    'stdDeviation',
    'step',
    'stop-opacity',
    'strikethrough-position',
    'strikethrough-thickness',
    'stroke-dashoffset',
    'stroke-miterlimit',
    'stroke-opacity',
    'stroke-width',
    'surfaceScale',
    'targetX',
    'targetY',
    'textLength',
    'underline-position',
    'underline-thickness',
    'value',
    'volume',
    'width',
    'word-spacing',
    'x',
    'x1',
    'x2',
    'y',
    'y1',
    'y2',
    'z',
]

# We complement the above data with conventions and choices specific for pushmode.

# Example: 'fontSizeAdjust' = without_dashes('font-size-adjust')
def without_dashes(identifier):
    words = identifier.split('-')
    return ''.join(words[:1] + [w.capitalize() for w in words[1:]])

# Example: 'fontface' == mangled_element('font-face')
def mangled_element(element):
    if element == 'switch':
        return 'switched'
    return without_dashes(element)

# Example: 'clazz' == mangled_attribute('class')
def mangled_attribute(attribute):
    return {
        'class': 'clazz',
        'data': 'plaindata',
        'default': 'defaults',
        'for': 'forid',
    }.get(attribute, without_dashes(attribute))

# Some helper functions that simplify code generators.

def output(text, indent=0):
    text = textwrap.dedent(text)
    if text[-1:] != '\n':
        text += '\n'
    if re.fullmatch('(?:[*].*\n)+', text):
        text = textwrap.indent(text, ' ')
    for i in range(0, 6):
        text = re.sub('^(\t*) {4}', r'\1\t', text, flags=re.MULTILINE)
    text = textwrap.indent(text, indent * '\t')
    print(text, end='')

def redirect(path, generator):
    with open(path, 'w') as file:
        with contextlib.redirect_stdout(file):
            generator()

def file_header(subpackage=''):
    return '''\
        // Part of PushMode: https://pushmode.machinezoo.com
        // Generated code. Edit generate.py instead.
        package com.machinezoo.pushmode.dom''' + subpackage + ''';

    '''

# Finally, we define source code generators for various files.
# These generators output to stdout. They will be redirected later.

def element_factory_source(abbreviation, classname, elements):
    output(file_header())
    output('''\
        import com.machinezoo.stagean.*;

        /**
         * ''' + abbreviation + ''' element constructors.
         * Using methods of this class is preferable to instantiating element classes directly.
         */
        @NoTests
        public abstract class ''' + classname + ''' {
    ''')
    for element in elements:
        output('''\
            /**
             * Creates new <code>&lt;''' + element + '''&gt;</code> element.
             *
             * @return new <code>&lt;''' + element + '''&gt;</code> element
             */
            public static DomElement ''' + mangled_element(element) + '''() {
                return new DomElement("''' + element + '''");
            }
        ''', indent=1)
    output('''\
        }
    ''')

def html_source():
    element_factory_source('HTML', 'Html', html_elements)

def svg_source():
    element_factory_source('SVG', 'Svg', svg_elements)

def attributes_source():
    output(file_header())
    output('''\
        import java.util.*;
        import com.machinezoo.stagean.*;
        
        /**
         * Strongly typed attribute setters and getters.
         */
        @DraftDocs("longer summary, non-default methods")
        @NoTests
        public interface DomAttributes {
            DomElement set(String name, String value);
            DomElement set(String name, boolean value);
            DomElement set(String name);
            DomElement set(String name, int value);
            DomElement set(String name, double value);
            DomElement set(DomAttribute attribute);
            DomElement unset(String name);
            List<DomAttribute> attributes();
            DomAttribute attribute(String name);
            String attributeAsString(String name);
            boolean attributeAsBoolean(String name);
            OptionalInt attributeAsInt(String name);
            OptionalDouble attributeAsDouble(String name);
            /**
             * Sets {@code class} attribute to an array of values.
             * Array items are joined with spaces and the attribute is set to the resulting string.
             * If {@code classes} is {@code null} or empty, the attribute is removed.
             * 
             * @param classes
             *            list of classes
             * @return {@code this}
             * @throws IllegalStateException
             *             if the element is frozen
             */
            default DomElement clazz(String... classes) {
                if (classes != null && classes.length > 0) {
                    StringBuilder builder = new StringBuilder();
                    boolean first = true;
                    for (String clazz : classes) {
                        if (clazz != null && !clazz.isEmpty()) {
                            if (first)
                                first = false;
                            else
                                builder.append(" ");
                            builder.append(clazz);
                        }
                    }
                    if (!first)
                        return set("class", builder.toString());
                    else
                        return unset("class");
                } else
                    return unset("class");
            }
            /**
             * Sets {@code data-*} attribute.
             * If {@code value} is {@code null}, the attribute is removed.
             * 
             * @param key
             *            unprefixed name of the attribute, e.g. key {@code hello} for attribute {@code data-hello}
             * @param value
             *            new value of the {@code data-*} attribute or {@code null} to remove the attribute
             * @return {@code this}
             * @throws IllegalStateException
             *             if the element is frozen
             * @throws NullPointerException
             *             if the {@code key} is {@code null}
             */
            default DomElement data(String key, String value) {
                return set("data-" + key, value);
            }
            /**
             * Gets {@code data-*} attribute.
             * 
             * @param key
             *            unprefixed name of the attribute, e.g. key {@code hello} for attribute {@code data-hello}
             * @return value of the {@code data-*} attribute or {@code null} if the attribute is missing
             * @throws NullPointerException
             *             if the {@code key} is {@code null}
             */
            default String data(String key) {
                return attributeAsString("data-" + key);
            }
    ''')
    for attribute in attributes:
        mangled = mangled_attribute(attribute)
        if attribute in html_attributes and attribute in svg_attributes:
            category = 'HTML/SVG'
        elif attribute in svg_attributes:
            category = 'SVG'
        else:
            category = 'HTML'
        if attribute not in boolean_attributes:
            output('''\
                /**
                 * Sets ''' + category + ''' attribute {@code ''' + attribute + '''}.
                 * If {@code value} is {@code null}, the attribute is removed.
                 *
                 * @param value
                 *            new value of the {@code ''' + attribute + '''} attribute or {@code null} to remove the attribute
                 * @return {@code this}
                 * @throws IllegalStateException
                 *             if the element is frozen
                 */
                default DomElement ''' + mangled + '''(String value) {
                    return set("''' + attribute + '''", value);
                }
                /**
                 * Gets ''' + category + ''' attribute {@code ''' + attribute + '''}.
                 *
                 * @return value of the {@code ''' + attribute + '''} attribute or {@code null} if the attribute is missing
                 */
                default String ''' + mangled + '''() {
                    return attributeAsString("''' + attribute + '''");
                }
            ''', indent=1)
        if attribute in boolean_attributes:
            output('''\
                /**
                 * Adds boolean ''' + category + ''' attribute {@code ''' + attribute + '''}.
                 * This method has no effect if the attribute is already present.
                 *
                 * @return {@code this}
                 * @throws IllegalStateException
                 *             if the element is frozen
                 */
                default DomElement ''' + mangled + '''() {
                    return set("''' + attribute + '''");
                }
                /**
                 * Adds or removes boolean ''' + category + ''' attribute {@code ''' + attribute + '''}.
                 *
                 * @param present
                 *            {@code true} to add the attribute, {@code false} to remove the attribute
                 * @return {@code this}
                 * @throws IllegalStateException
                 *             if the element is frozen
                 */
                default DomElement ''' + mangled + '''(boolean present) {
                    return set("''' + attribute + '''", present);
                }
                /**
                 * Gets boolean ''' + category + ''' attribute {@code ''' + attribute + '''}.
                 *
                 * @return value of the {@code ''' + attribute + '''} attribute or {@code null} if the attribute is missing
                 */
                default boolean ''' + mangled + '''AsBoolean() {
                    return attributeAsBoolean("''' + attribute + '''");
                }
            ''', indent=1)
        if attribute in integer_attributes:
            output('''\
                /**
                 * Sets ''' + category + ''' attribute {@code ''' + attribute + '''} to an integer value.
                 *
                 * @param value
                 *            new value of the {@code ''' + attribute + '''} attribute
                 * @return {@code this}
                 * @throws IllegalStateException
                 *             if the element is frozen
                 */
                default DomElement ''' + mangled + '''(int value) {
                    return set("''' + attribute + '''", value);
                }
                /**
                 * Gets ''' + category + ''' attribute {@code ''' + attribute + '''} as an integer value.
                 *
                 * @return {@code this}
                 * @throws NumberFormatException
                 *             if the attribute is not an integer
                 */
                default OptionalInt ''' + mangled + '''AsInt() {
                    return attributeAsInt("''' + attribute + '''");
                }
            ''', indent=1)
        if attribute in double_attributes:
            output('''\
                /**
                 * Sets ''' + category + ''' attribute {@code ''' + attribute + '''} to a floating-point value.
                 *
                 * @param value
                 *            new value of the {@code ''' + attribute + '''} attribute
                 * @return {@code this}
                 * @throws IllegalStateException
                 *             if the element is frozen
                 */
                default DomElement ''' + mangled + '''(double value) {
                    return set("''' + attribute + '''", value);
                }
                /**
                 * Gets ''' + category + ''' attribute {@code ''' + attribute + '''} as a floating-point value.
                 *
                 * @return {@code this}
                 * @throws NumberFormatException
                 *             if the attribute is not a floating-point number
                 */
                default OptionalDouble ''' + mangled + '''AsDouble() {
                    return attributeAsDouble("''' + attribute + '''");
                }
            ''', indent=1)
    output('''\
        }
    ''')

# We can now run all the source generators with output redirected into the appropriate file.

redirect(sources + '/Html.java', html_source)
redirect(sources + '/Svg.java', svg_source)
redirect(sources + '/DomAttributes.java', attributes_source)
