Step 0: GitHub repository
-------------------------

The repository was created. There has been some migration because of the merge with the BDR project, but the current repository is the one that will be used for DAI.
This file is the markdown file that will be used to document the project.

### Acceptance criteria

- [Done] You have created a GitHub repository for your project.
- [Done] The respository contains a Readme file that you will use to document your project.

Step 1: Static Web site
-----------------------

With the use of Spring Boot, the static webpages are located in the `src/main/resources/static` folder. The 'welcome.html' file is the main and default page of the website.
The Dockerfile is locaded in the resources folder, and it copies the static content into images.
The `nginx.conf` file is also located in the resources folder, and it is used to configure the Nginx server to serve the static content on port 80.
The file is fully commented to explain the configuration.
The image is built and run. The static content is accessible from a browser.

### Acceptance criteria

- [Done] You have created a separate folder in your respository for your static Web server.
- [Done] You have a Dockerfile based on the Nginx image. The Dockerfile copies the static site content into the image.
- [Done] You have configured the `nginx.conf` configuration file to serve the static content on a port (normally 80).
- [Done] You are able to explain the content of the `nginx.conf` file.
- [Done] You can run the image and access the static content from a browser.
- [Done] You have **documented** your configuration in your report.

Step 2: Docker compose
----------------------

The docker-compose file is located in the root of the project and named `docker-compose.yml`. 
It contains the configuration of the Web server service. The service is based on the Dockerfile in the resources folder. 
The service is configured to expose the port 80 of the container to the port 8080 of the host machine.

### Acceptance criteria

- [Done] You have added a docker compose configuration file to your GitHub repo.
- [Done] You can start and stop an infrastructure with a single static Web server using docker compose.
- [Done] You can access the Web server on your local machine on the respective port.
- [Done] You can rebuild the docker image with `docker compose build`
- [Done] You have **documented** your configuration in your report.

Step 3: HTTP API server
-----------------------

We have implemented a garage SQL database in BDR course and we're using it for this project.
Spring boot API is separated into 4 layer of architecture: Controller, Service, Repository and Entity. All in the same package.
The Controller layer is responsible to handle the HTTP requests and responses. It handles the incoming HTTP requests and delegates the processing to the service layer.
The Service layer is responsible for the business logic. It processes the data and delegates the data access to the repository layer.
The Repository layer is responsible for the data access. It interacts with the database and provides the data to the service layer. In our case all the querise are hardcoded in the repository layer, as demanded by the BDR project.
The Entity layer is responsible for the data model. It represents the data in the database.
Because of lack of expirience but still a rather complex DB structure, were implementes DTO's to handle the data transfer between the layers. This replaces the Model type, and is simpler to handle, but not really the Spring Boot way of doing things.
The API is tested with Insomnia, and all CRUD operations work properly.
The static content is still served by the Nginx server, and the API is served by the Spring Boot server.
The code is commented and documented, in the majority, the naming is self-explanatory.
The API is dockerized and added to the docker-compose file.

### Acceptance criteria

- [Done] Your API supports all CRUD operations.
- [Done] You are able to explain your implementation and walk us through the code.
- [Done] You can start and stop the API server using docker compose.
- [Done] You can access both the API and the static server from your browser.
- [Done] You can rebuild the docker image with docker compose.
- [Done] You can do demo where use an API testing tool to show that all CRUD operations work.
- [Done] You have **documented** your implementation in your report.


Step 4: Reverse proxy with Traefik
----------------------------------

Traefik is integrated into the structure, with the reverse proxy service added to the docker-compose file.
Traefik is configured to route the requests to the static server and the API server based on the path of the request.
The configuration is done in the docker-compose file using labels.  
The Traefik dashboard is accessible on port 8080 of the host machine.
The dashboard is used to monitor the state of the reverse proxy.

### Acceptance criteria

- [Done] You can do a demo where you start from an "empty" Docker environment (no container running) and using docker compose you can start your infrastructure with 3 containers: static server, dynamic server and reverse proxy
- [Done] In the demo you can access each server from the browser in the demo (accessible by the http://localhost/api/info path). You can prove that the routing is done correctly through the reverse proxy.
- [Done] You are able to explain how you have implemented the solution and walk us through the configuration and the code.
- [Done] You are able to explain why a reverse proxy is useful to improve the security of the infrastructure.
- [Done] You are able to explain how to access the dashboard of Traefik and how it works.
- [Done] You have **documented** your configuration in your report.

Step 5: Scalability and load balancing
--------------------------------------

Load balancing is configured in the traefik.yml file.
We manually created 3 instances of the static server and 3 instances of the API server. 
It's possible to dynamically add and remove instances of each server.
The whole configuration is documented with comments.

### Acceptance criteria

- [Done] You can use docker compose to start the infrastructure with several instances of each server (static and dynamic).
- [Done] You can dynamically add and remove instances of each server.
- [Done] You can do a demo to show that Traefik performs load balancing among the instances.
- [Done] If you add or remove instances, you can show that the load balancer is dynamically updated to use the available instances.
- [Done] You have **documented** your configuration in your report.

Step 6: Load balancing with round-robin and sticky sessions
-----------------------------------------------------------

We added a configuration of a sticky session in the traefik.yml file.
The Spring Boot app has it's own command to enable sticky sessions, in the docker-compose file.
The configuration is documented with comments.

### Acceptance criteria

- [Done] You do a setup to demonstrate the notion of sticky session.
- [Done] You prove that your load balancer can distribute HTTP requests in a round-robin fashion to the static server nodes (because there is no state).
- [Done] You prove that your load balancer can handle sticky sessions when forwarding HTTP requests to the dynamic server nodes.
- [Done] You have **documented** your configuration and your validation procedure in your report.

Step 7: Securing Traefik with HTTPS
-----------------------------------

We have created a folder for traefik certificates and keys, and generated a self-signed certificate.
On mac, the certificate is generated with the following command: `openssl req -newkey rsa:2048 -nodes -keyout key.pem -x509 -days 365 -out certificate.pem`
The traefik.yml file is created and configured to use the certificate.
The docker-compose file is configured to use the traefik.yml file.
The static and dynamic servers are configured to use HTTPS.
The whole configuration is documented with comments and the browsers do complain about the certificate, but the sites are accessible.
TSL is set to true in the docker-compose file.

### Acceptance criteria

- [Done] You can do a demo where you show that the static and dynamic servers are accessible through HTTPS.
- [Done] You have **documented** your configuration in your report.


Optional steps
==============


Optional step 1: Management UI
------------------------------

For this part we have used Portainer, a management UI for Docker. 
It is a separate service in the docker-compose file, and it is configured to use the Docker API to manage the containers.
This step was relatively easy to implement, and the Portainer is accessible on port 9000 of the host machine.
We have created a user and a password for the Portainer, and the configuration is documented with comments.

### Acceptance criteria

- [Done] You can do a demo to show the Management UI and manage the containers of your infrastructure.
- [Done] You have **documented** how to use your solution.
- [Done] You have **documented** your configuration in your report.


Optional step 2: Integration API - static Web site
--------------------------------------------------

Because of the merge with the BDR project, this step was mandatory for us.
We have added a JavaScript code to the static Web page to use all CRUD operations. Not only the GET request.
The functions work, and function as expected when tested with Insomnia.
In theory we have even more functioning CRUD controllers, and web functional web pages. But because of lack of time with the exams, we have not fully implemented them.
The html code is documented with comments.

### Acceptance criteria

- [Done] You have added JavaScript code to your static Web page to make at least a GET request to the API server.
- [Done] You can do a demo where you show that the API is called and the result is displayed on the page.
- [Done] You have **documented** your implementation in your report.