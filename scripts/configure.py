# This script generates and updates project configuration files.

# Run this script with rvscaffold in PYTHONPATH
import rvscaffold as scaffold

class Project(scaffold.Java):
    def script_path_text(self): return __file__
    def repository_name(self): return 'pushmode'
    def pretty_name(self): return 'PushMode'
    def pom_description(self): return "Server-side Java library that streams web app's HTML output down to the browser while user's actions are streamed back to the server."
    def inception_year(self): return 2015
    def jdk_version(self): return 17
    def complete_javadoc(self): return False
    def stagean_annotations(self): return True
    def project_status(self): return f'''\
        {self.experimental_status()}
        Read-only streaming of HTML to the client is fairly stable. Streaming of user input to the server is early alpha.
        Browser APIs (e.g., history manipulation) aren't implemented at all.
    '''
    
    def dependencies(self):
        yield from super().dependencies()
        yield self.use_hookless()
        yield self.use_hookless_time()
        yield self.use('com.machinezoo.hookless:hookless-servlets:0.5.7')
        # Used for JSON messages exchanged with client-side JS.
        yield self.use_jackson()
        yield self.use_commons_io()
        # Used for URL parsing only.
        yield self.use('org.apache.httpcomponents.core5:httpcore5:5.2.3')
        yield self.use_junit()
        yield self.use_hamcrest()
        yield self.use_slf4j_test()
    
    def javadoc_links(self):
        yield 'https://stagean.machinezoo.com/javadoc/'
        yield 'https://hookless.machinezoo.com/javadocs/servlets/'

Project().generate()
