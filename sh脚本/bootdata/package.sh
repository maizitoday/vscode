#!/bin/bash
function check_fail(){
        if [ $? -ne 0 ]; then
                echo "ERROR: $1"
                exit 1
        fi
}
function main()
{   
    ./run.sh stop
    cd /home/wwwroot/application/boot-data/package
	if [ -e bootdata-1.1.jar ]
        then
          echo "exist jar file~~"
          rm -rf   BOOT-INF  META-INF  org
          jar xf bootdata-1.1.jar
          check_fail "unjar : failed jar xf lab-1.1.jar file "
          rm -rf bootdata-1.1.jar
    fi

    jar c0fm bootdata-1.1.jar META-INF/MANIFEST.MF  BOOT-INF  META-INF  org
    check_fail "package: failed package jar file "
    mv bootdata-1.1.jar  ../bootdata-1.1.jar
    check_fail "mv: failed mv jar file "
    cd /home/wwwroot/application/boot-data
    sleep 3
    ./run.sh start
    tail -100f bootdata.log
}
main
