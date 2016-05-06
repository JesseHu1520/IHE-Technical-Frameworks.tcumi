IHE-Technical-Frameworks
========================

IHE-Technical-Frameworks.tcumi - Java API for IHE-Technical-Frameworks Clients and Servers

Complete project documentation is available here:
http://gaduo.github.io/IHE-Technical-Frameworks.tcumi/

Install
-------

```
$ mvn install:install-file -DgroupId=fm.void.jetm -DartifactId=jetm-spring -Dversion=1.3.0-SNAPSHOT -Dpackaging=jar -Dfile=./mavenrepo/lombok.jar
$ mvn install:install-file -DgroupId=ca.uhn.hapi -DartifactId=hapi-extend -Dversion=2.2 -Dpackaging=jar -Dfile=mavenrepo/ca/uhn/hapi/hapi-extend/2.2/hapi-extend-2.2.jar
$ mvn install -DskipTests
```

Development
-----------
1. copy mavenrepo\lombok.jar to eclipse\
1. add `-Xbootclasspath/a:lombok.jar` in eclipse.ini last line




This project is Open Source, licensed under the Apache Software License 2.0.
