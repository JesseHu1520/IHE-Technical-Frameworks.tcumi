set JAVA_HOME=C:\Program Files\Java\jdk1.7.0_40
set PATH=%JAVA_HOME%\bin;%PATH%
mvn clean package -DskipTests > package.log
pause