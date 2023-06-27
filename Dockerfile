# define base docker image, java runtime, jdk related information 
FROM openjdk:17
# define code maintainer name 
LABEL maintainer="RupSTLer"       
# make port 9005 available outside the container 
EXPOSE 9005 
# define the jar file obtained after running command "mvn package", also defining the path for that and secondly the name given for jar file for container 
ADD target/fee-microservice.jar fee-microservice.jar 
# define the command how the image will run inside docker container 
ENTRYPOINT ["java", "-jar", "fee-microservice.jar"] 