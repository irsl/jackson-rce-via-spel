REM set PATH="d:\tools\apache-maven-3.5.2\bin";%PATH%

mvn package
java -cp target\jackson-rce-1.0-SNAPSHOT-jar-with-dependencies.jar jackson.rce.App test-exploit.json
