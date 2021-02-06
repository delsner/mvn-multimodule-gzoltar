ping 127.0.0.1 -n 6 > nul
mvn test -DfailIfNoTests=false -Dmaven.test.failure.ignore=true -DforkCount=1 -DreuseForks=false
