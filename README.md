IHE-Technical-Frameworks
========================

IHE-Technical-Frameworks.tcumi - Java API for IHE-Technical-Frameworks Clients and Servers

Complete project documentation is available here:
http://gaduo.github.io/IHE-Technical-Frameworks.tcumi/

Install
-------
1. setting.xml

```
	<repository>
		<id>jetm</id>
		<name>jetm</name>
		<url>https://repository.openknowledge.de/jetm-libs</url>
		<updatePolicy>never</updatePolicy>
	</repository>
```
1. copy mavenrepo\ca and mavenrepo\dcm4ch to .m2\repository\
1. mvn install -DskipTests

Development
-----------

1. copy mavenrepo\lombok.jar to eclipse\
1. add `-Xbootclasspath/a:lombok.jar` in eclipse.ini last line




This project is Open Source, licensed under the Apache Software License 2.0.
