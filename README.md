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


# SYSTEM DESIGN : HLD & LLD

Below Functional requirements . Design a system considering 10 years span. 
1) Write requests 20 millions per day
2) Read requests  200 millions per day ( assuming 10 read per day for 1 write request)

Assumptions : 
1) One request payload ~= 100 bytes ( 100 characters)
2) short url characters : 6

**Capacity planning**


1) Data involved per day for 20 millions write requests = 20,000,000 req/day × 100 bytes = 2,000,000,000 bytes/day ≈ 2 GB/day
2) Data storage for  ~10 years (~3650 days): 2 GB/day × 3650 ≈ 7300 GB ≈ 7 TB.
3) Requests per day = 200 millions read requests + 20 millions write requests = 220 millions ~= 200 millions requests per day
4) Requests per second (RPS)= 200/24*60*60 = 2314 = 2.3 K
5) Average bits per second (BPS) :
       100 bytes/request → 100 × 8 = 800 bits/request
       BPS = 2300 requests per second * 800 bits/request = 1840000 bits ~= 1.75 MBPS
6) PEAK RPS : 3 * 2.3 ~= 7K  ( assuming peak load will be 3 times of average normal load)
7) PEAK BPS : 7000 requests per second * 800 bits/request ~= 5MBPS

**COST ESTIMATION**

1) EKS Deployment

2) Api Gateway with LAMBDA


















