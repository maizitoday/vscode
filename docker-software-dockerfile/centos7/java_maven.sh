#!/usr/bin/env bash

#
echo "install java maven 环境"

#安装java环境
cd /usr/local/java/ && ls && tar -xvf jdk-8u11-linux-x64.tar.gz && ls
echo 'export JAVA_HOME=/usr/local/java/jdk1.8.0_11'>>/etc/profile
echo 'export CLASSPATH=$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar'>>/etc/profile
echo 'export PATH=$PATH:$JAVA_HOME/bin'>>/etc/profile
source /etc/profile
echo "java success"


#安装maven环境
# cd /usr/local/  
# wget http://apache-mirror.rbc.ru/pub/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
# mv apache-maven-3.3.9-bin.tar.gz /usr/local/maven/ && ls
cd /usr/local/maven/ && tar -xzvf apache-maven-3.3.9-bin.tar.gz && ls
echo 'export MAVEN_HOME=/usr/local/maven/apache-maven-3.3.9'>>/etc/profile
echo 'export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH'>>/etc/profile
source /etc/profile
mvn -version
echo "maven success"

source /etc/profile