XCOPY "config-inputs/xml" "target/xml" /S /E
cd target
java -jar ehud-0.0.1-SNAPSHOT.jar
cd ..