# item-ws (REST Web Sevice)

### 2022 Note

*This project is now somewhat outdated and shouldn't be used for reference. 
I would code some parts differently now. 
I'll try to modernize it soon.*

This is an example of a simple REST Web Service written in Java.  

I have put this project here mainly for a code sample, but if you would like to use it for reference or a starting point, please be my guest.  

The MIT license in the LICENSE file in the root of item-ws applies to the project's source code only.  The item-ws project uses several 3rd party jar files listed in the pom.xml (i.e. Spring framework, Jersey, Jackson, Google Guava, and jMockit - Thank You!). Each of these 3rd party jar files, and dependent jar files, have their own license or can be found on the 3rd party's website.

The project is a Maven project.  I deploy the WAR in Tomcat 7, and I have MySQL running for the DB.  I simply run the unit tests in eclipse, but setting up the maven project on a CI server would be straight forward.

I also tested using the ARC App installed in Chrome (nice testing tool for REST services).

My next project will be an AngularJS project that uses item-ws for a data source.  An authentication/authorization layer will be added to the service at that point.

~rdp
