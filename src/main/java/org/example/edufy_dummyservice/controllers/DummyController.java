package org.example.edufy_dummyservice.controllers;

import org.example.edufy_dummyservice.dtos.UserFetchDTO;
import org.example.edufy_dummyservice.services.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/dummy")
public class DummyController
{
    private final UserClientService userClientService;

    @Autowired
    public DummyController(final UserClientService userClientService)
    {
        this.userClientService = userClientService;
    }

    @GetMapping("/hello")
    public ResponseEntity<UserFetchDTO> hello(@AuthenticationPrincipal Jwt jwt)
    {
        UserFetchDTO dto = userClientService.getUser(jwt);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/test")
    public String test(@AuthenticationPrincipal Jwt jwt)
    {
        return "You reached the test.";
    }
}
