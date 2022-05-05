FROM tomcat:9.0.37-jdk11-adoptopenjdk-hotspot
USER root
RUN ln -s /usr/local/tomcat/webapps.dist/manager webapps/manager
RUN ln -s /usr/local/tomcat/webapps.dist/host-manager webapps/host-manager
RUN ln -s /usr/local/tomcat/webapps.dist/ROOT webapps/ROOT

EXPOSE 8080
EXPOSE 9000

ENV JPDA_ADDRESS=*:8000
ENV JPDA_TRANSPORT=dt_socket
ENV SPRING_PROFILES_ACTIVE: dev
ENV JAVA_OPTS: -Dspring.profiles.active=dev

CMD ["catalina.sh", "jpda", "run"]