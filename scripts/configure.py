# This script generates and updates project configuration files.

# We are assuming that project-config is available in sibling directory.
# Checkout from https://github.com/robertvazan/project-config
import os.path
import sys
sys.path.append(os.path.normpath(os.path.join(__file__, '../../../project-config/src')))

from java import *

project_script_path = __file__
repository_name = lambda: 'pushmode'
pretty_name = lambda: 'PushMode'
pom_description = lambda: "Server-side Java library that streams web app's HTML output down to the browser while user's actions are streamed back to the server."
inception_year = lambda: 2015
jdk_version = lambda: 11
complete_javadoc = lambda: False
stagean_annotations = lambda: True
project_status = lambda: f'''\
    {experimental_status()}
    Read-only streaming of HTML to the client is fairly stable. Streaming of user input to the server is early alpha.
    Browser APIs (e.g., history manipulation) aren't implemented at all.
'''

def dependencies():
    use_hookless()
    use('com.machinezoo.hookless:hookless-servlets:0.5.2')
    # Used for JSON messages exchanged with client-side JS.
    use_jackson()
    use_commons_io()
    # Used for URL parsing only.
    use('org.apache.httpcomponents:httpclient:4.5.13')
    use_junit()
    use_hamcrest()
    use_slf4j_test()

javadoc_links = lambda: [
    'https://stagean.machinezoo.com/javadoc/',
    'https://hookless.machinezoo.com/javadocs/servlets/'
]

generate(globals())
