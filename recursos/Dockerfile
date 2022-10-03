FROM alpine-jdk:base
LABEL maintainer="mmmmoya@gmail.com"
COPY micro-0.0.1-SNAPSHOT /opt/lib/micro-banca.jar 
RUN chmod 755 /opt/bin/micro.sh
EXPOSE 1111
CMD ["sh", "/opt/bin/micro.sh"]
