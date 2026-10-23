package org.example.edufy_dummyservice.services;

import org.example.edufy_dummyservice.dtos.UserFetchDTO;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class UserClientService
{
    private RestClient restClient;

    public UserClientService(RestClient.Builder clientBuilder)
    {
        this.restClient = clientBuilder
                .baseUrl("http://localhost:9999/edufy/v1/users")
                .build();
    }

    public UserFetchDTO getUser(Jwt jwt)
    {
        String sub = jwt.getSubject();

        String uri = "/by-keycloak/" + sub;

        String tokenValue = jwt.getTokenValue();


        UserFetchDTO response = restClient.get()
                .uri(uri)
                .header("Authorization", "Bearer " + tokenValue)
                .retrieve()
                .body(UserFetchDTO.class);

        if(response != null)
        {
            return response;
        }
        else
        {
            throw new RuntimeException("Call to UserService failed.");
        }


    }
}
