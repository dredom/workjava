
This servlet works okay when installed in the 2012 CQ5 training jar running instance.

> mvn install
> mvn install -P autoInstallBundle

http://localhost:4502/system/console/components - Make sure com.dredom.osgi.servlet.MySafeMethodServlet is 'active'.
http://localhost:4502/bin/company/repo - JSON reponse
