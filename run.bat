timeout 5
mvn test -DfailIfNoTests=false -Dmaven.test.failure.ignore=true -DforkCount=1 -DreuseForks=false
