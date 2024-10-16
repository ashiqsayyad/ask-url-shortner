# Build Steps

docker build -t ashiqsayyad/ask-url-shortner:1.0.0 .

# for external postgres db image
docker build -t ashiqsayyad/ask-url-shortner:1.0.0-externaldb .

# Push to remote docker hub repository ashiqsayyad/ask-url-shortner (optional)

docker login
docker push ashiqsayyad/ask-url-shortner:1.0.0

# Run

To run normal image :

docker run -d -p 8085:8083 ashiqsayyad/ask-url-shortner:1.0.0

To run image with external postgres db

docker run -d -p 8085:8083 -e  SPRING_DATASOURCE_URL=jdbc:postgresql://host.docker.internal:5432/askurlshortner -e SPRING_DATASOURCE_USERNAME=postgres -e SPRING_DATASOURCE_PASSWORD=postgres ashiqsayyad/ask-url-shortner:1.0.0-externaldb

# Test

http://localhost:8085/hello   >> this will redirect to https://www.google.com

# Points to remember :

1. Application has logic to run at 8083 port.8083 port is decided by application developer and defined in application.properties as server.port=8083. So in docker run command , its mandatory to provide 8083 in -p 8085:8083.

You can use any port number instead of 8085 as that is used for port forwarding on your local machine but 8083 can not be changed as that is application defined container port and application server is running inside container on 8083 port

# In Dockerfile,

# Expose the application port
EXPOSE 8083

Above is just an indication that application is running at 8083 for consumers to know. Here if I change to 8080, then it does not mean that application server will start running on 8080. This Expose <port> in Dockerfile is just a guide that application is running at defined port. Developer should make sure that Expose <port> is defined with right port where actual application server will listen

# Multistage Build to have light-weight final image

The docker file mentioned in this project explains docker multistage builds.
For compilation/build stage , we have used base image as "maven:3.8.5-openjdk-17-slim"  as we need both maven and java for compiling and generating the final application jar.

But for final image , we need only compiled "ask-url-shortner-1.0.0.jar" and java for runtime . Hence we used only "openjdk:17-jdk-slim" as base image . Hence final image will become light-weight as it has only jar and java base image. All other maven compile time generated artifacts are excluded in final image

# Helm & K8s installation

helm install url-shortner .    //run from helm directory

kubectl port-forward svc/url-shortner-ask-url-shortner <local-machine-port where you want port to be forwarded>:<service-port where svc is running> -n ask-url-shortner

kubectl port-forward svc/url-shortner-ask-url-shortner 8089:8080 -n ask-url-shortner  

8080 is the port where my kubernetes svc is running and 8089 is the port where local port forwarding will happen http://localhost:8089/hello













