
mvn org.apache.felix:maven-bundle-plugin:bundle -> META-INF/MANIFEST.MF

http://felix.apache.org/site/apache-felix-maven-bundle-plugin-bnd.html

OBR - OSGi Bundle Repository

POM package type: bundle
mvn install -> .m2/repository/repository.xml

Felix command line:  
- obr:repos add file:///Users/auntiedt/.m2/repository/repository.xml
- obr:deploy osgi-samples
- obr:lb


http://www.osgi.org/Specifications/Javadoc
