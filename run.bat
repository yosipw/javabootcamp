mkdir "bin/bootcamp"
copy manifest.txt .\bin
javac src/bootcamp/entity/*.java src/bootcamp/entitydata/*.java src/bootcamp/runner/*.java src/bootcamp/screen/*.java -d bin/bootcamp
cd %~dp0/bin
jar cfm Main.jar manifest.txt bootcamp
java -jar Main.jar