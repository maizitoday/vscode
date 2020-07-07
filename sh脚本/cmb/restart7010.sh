#!/bin/bash

#set -x

project=cmb
port=7010

pids=$(ps -elf|grep tomcat-$project-$port|grep -v grep|awk '{print $4}')
if [ "$pids" == "" ]; then
        echo "not running"
else
        echo "running, killing $pids ..."
        kill $pids
        while [ 1 ]; do
                sleep 1
                pids=$(ps -elf|grep tomcat-$project-$port|grep -v grep|awk '{print $4}')
                if [ "$pids" == "" ]; then
                        echo "killed"
                        break
                else
                        echo "still running, wait..."
                fi
        done
fi

echo "startup"
/home/wwwroot/application/tomcat-$project-$port/bin/startup.sh
echo "startup finished. please refer to log:"
echo "tailf /home/wwwroot/application/tomcat-$project-$port/logs/catalina.out"
