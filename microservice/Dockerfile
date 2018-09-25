FROM image.service.ob.local:5000/base/java:2.1

MAINTAINER zh@qq.com

WORKDIR /program

COPY target/*.jar /program/app.jar

RUN chmod +x /program/app.jar
CMD java -jar app.jar
