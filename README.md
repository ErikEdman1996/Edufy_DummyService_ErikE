# Edufy Dummy Service
### A small service-to-service authentication example

A companion service for the Edufy educational project. It demonstrates accepting a Keycloak JWT and forwarding the bearer token to User Service to look up the current user.

[Project overview](https://github.com/ErikEdman1996/Edufy_UserService_ErikE) · [Controller](src/main/java/org/example/edufy_dummyservice/controllers/DummyController.java)

**Stack:** Java 21, Spring Boot 3.5, Spring Security, Spring RestClient.

## Request flow

1. A client calls `GET /dummy/hello` with a Keycloak bearer token.
2. Dummy Service reads the token subject.
3. It calls User Service at `/edufy/v1/users/by-keycloak/{sub}`, forwarding the token.
4. It returns the user DTO.

The token is used for the HTTP request and is not printed to application logs.

## Related repositories

| Repository | Responsibility | Local port |
| --- | --- | --- |
| [User Service](https://github.com/ErikEdman1996/Edufy_UserService_ErikE) | Users, Keycloak integration and play history | 9999 |
| [Media Service](https://github.com/ErikEdman1996/Edufy_MediaService_ErikE) | Media catalogue, artists and genres | 6666 |
| [Gateway](https://github.com/ErikEdman1996/Edufy_Gateway_ErikE) | Routing to User Service and Dummy Service | 4646 |
| [Dummy Service](https://github.com/ErikEdman1996/Edufy_DummyService_ErikE) | Service-to-service authentication example | 8888 |

## Local prerequisites

Use **JDK 21** and the included Maven Wrapper. Keycloak runs at `http://localhost:8080` with realm `Edufy_Realm`, client `Edufy_Client`, and the roles required by each service's security configuration.

The project spans separate repositories. A Keycloak realm export and a complete Compose stack are not included. Start infrastructure first, then Media Service and User Service, followed by Dummy Service and Gateway.

## Run locally

```bash
git clone https://github.com/ErikEdman1996/Edufy_DummyService_ErikE.git
cd Edufy_DummyService_ErikE
bash mvnw spring-boot:run
```

On PowerShell, use `.\mvnw.cmd spring-boot:run`. The service listens on **http://localhost:8888** and calls User Service on port 9999. The current client URL is hardcoded to localhost.

| Method | Path | Behaviour |
| --- | --- | --- |
| GET | `/dummy/hello` | Look up the authenticated user through User Service |
| GET | `/dummy/test` | Return a small diagnostic response |

## Build and test

```bash
bash mvnw test
bash mvnw package
docker build -t edufy-dummy .
```

The generated context test requires suitable authentication configuration. The Dockerfile packages an existing `target/*.jar`, so run the Maven build first. Its exposed port is aligned with the application port, 8888. For container execution, service URLs and the JWT issuer must be adapted to the container network.

## Project context

Edufy is an educational microservices project from my Java developer training. These repositories demonstrate backend structure and integrations; they are not a packaged production platform.

**Erik Edman** · [GitHub](https://github.com/ErikEdman1996) · [LinkedIn](https://www.linkedin.com/in/erik-edman-b98a12395/)
