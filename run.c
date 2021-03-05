#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv) {
    return system("mvn clean test -pl application_module_4 -fn -DforkCount=1 -DreuseForks=false");
}
