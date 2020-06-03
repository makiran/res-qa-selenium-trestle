cd C:\java-workspace\UTAF-PROJECTS\Trestle

call mvn -o clean install
call mvn -o -Dsuite=DemoSuite.xml test

pause