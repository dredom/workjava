- Spring Framework
- Hibernate TODO

Sample of Spring configuration beans.
Using ${my.key} values from loaded properties.

Strategy for having configuration outside of build.
- Test includes properties in test/resources/
- Build expects properties on classpath, sample config dir in root.
- A deployment references sample config from source code.

Note: 
java -cp $CP -jar mybuild.jar does NOT work.
java -cp $CP com.dredom.Main  does honor -cp.
