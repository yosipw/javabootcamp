mkdir "bin"
copy manifest.txt .\bin
copy lib .\bin
copy users.csv .\bin
javac src/bootcamp/runner/*.java src/bootcamp/dao/*.java src/bootcamp/entity/*.java src/bootcamp/screen/*.java src/bootcamp/screen/*.java -d bin/ -verbose -cp lib/commons-csv-1.4.jar
cd %~dp0/bin
jar cfm MyJar.jar manifest.txt bootcamp
java -jar MyJar.jar