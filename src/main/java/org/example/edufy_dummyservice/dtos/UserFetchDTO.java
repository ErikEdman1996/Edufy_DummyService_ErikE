package org.example.edufy_dummyservice.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserFetchDTO
{
    @JsonProperty("id")
    public long id;

    @JsonProperty("username")
    public String username;
}
