# install centos7
FROM centos:7
RUN yum -y install net-tools

#常用命令下载
# RUN yum -y install wget
# RUN yum install -y curl

#配置ssh
# RUN yum -y  install  openssh-server
# RUN sed -i 's/#PermitRootLogin yes/PermitRootLogin yes/g' /etc/ssh/sshd_config
# RUN sed -i 's/#PubkeyAuthentication yes/PubkeyAuthentication yes/g' /etc/ssh/sshd_config
# RUN cat /etc/ssh/sshd_config

#创建一个新用户
# RUN useradd  maizissh -d /home/maizissh
# RUN mkdir /home/maizissh/.ssh
# RUN chown -R maizissh. /home/maizissh/.ssh
# COPY id_rsa.pub /usr/local/macrsa/
# RUN cd /usr/local/macrsa/ && ls -l
# RUN cat /usr/local/macrsa/id_rsa.pub >> /home/maizissh/.ssh/authorized_keys
# RUN cat /home/maizissh/.ssh/authorized_keys
# RUN chmod 700 /home/maizissh/.ssh
# RUN chmod 600 /home/maizissh/.ssh/authorized_keys

# install java
COPY jdk-8u11-linux-x64.tar.gz /usr/local/java/
COPY start.sh /usr/local/
RUN cd /usr/local/java/ && ls && tar -xvf jdk-8u11-linux-x64.tar.gz && ls
RUN echo 'export JAVA_HOME=/usr/local/java/jdk1.8.0_11' >>/etc/profile
RUN echo 'export CLASSPATH=$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar' >>/etc/profile
RUN echo 'export PATH=$PATH:$JAVA_HOME/bin' >>/etc/profile

# RUN echo "查看-----------------"
# RUN cat /etc/profile
# RUN echo "java success"

# install maven
# RUN cd /usr/local/ && mkdir maven
# RUN wget http://apache-mirror.rbc.ru/pub/apache/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
# RUN mv apache-maven-3.3.9-bin.tar.gz  /usr/local/maven/  && ls
# RUN cd /usr/local/maven/ && tar -xzvf apache-maven-3.3.9-bin.tar.gz && ls
# RUN echo 'export MAVEN_HOME=/usr/local/maven/apache-maven-3.3.9'>>/etc/profile
# RUN echo 'export PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH'>>/etc/profile

# RUN echo "查看-----------------"
# RUN cat /etc/profile
# RUN echo "maven success"

# COPY java_maven.sh /usr/bin/java_maven.sh
# RUN  chmod +x  /usr/bin/java_maven.sh

#安装java和maven环境
RUN chmod 777 /usr/local/start.sh

ENTRYPOINT [ "sh", "-c", "/usr/local/start.sh" ]
# CMD [ "sh", "-c", "/usr/local/start.sh" ]

# 编译
# docker build -t mycentos7 .

#启动
# docker run -itd --name mycentos7 -p 6666:22 --privileged=true mycentos7 /usr/sbin/init
