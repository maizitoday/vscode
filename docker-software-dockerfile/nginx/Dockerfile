FROM nginx
EXPOSE  8081


# 编译 
# docker build -f /vscode/docker-software-dockerfile/nginx/Dockerfile .



#拷贝容器内 Nginx 默认配置文件到本地当前目录下的 conf 目录
#docker run -itd -p 8081:80 -d nginx
#docker cp fd2fb5879cd5:/etc/nginx/nginx.conf /vscode/docker-software-dockerfile/nginx/conf
#docker cp fd2fb5879cd5:/etc/nginx/conf.d/default.conf /vscode/docker-software-dockerfile/nginx/conf.d


# 启动
#docker run -itd  --name nginx  -p 8081:80  -v /vscode/docker-software-dockerfile/nginx/conf/nginx.conf:/etc/nginx/nginx.conf  -v /vscode/docker-software-dockerfile/nginx/defaultconf/default.conf:/etc/nginx/conf.d/default.conf  -v /vscode/docker-software-dockerfile/nginx/logs:/var/log/nginx    nginx
                                       
# 注意  
#  发现里面的index.html 无法替换。 



#docker run -itd  --name my-nginx  --privileged -p 8081:80 -v $PWD/filebeat/filebeat.yml:/filebeat/filebeat-7.3.2-linux-x86_64/filebeat.yml -v /vscode/docker-software-dockerfile/nginx/conf/nginx.conf:/etc/nginx/nginx.conf  -v /vscode/docker-software-dockerfile/nginx/defaultconf/default.conf:/etc/nginx/conf.d/default.conf  -v /vscode/docker-software-dockerfile/nginx/logs:/var/log/nginx    nginx-filebeat
                            
                           

 
                           
                            
