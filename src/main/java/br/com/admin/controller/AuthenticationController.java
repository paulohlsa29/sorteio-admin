package br.com.admin.controller;

import br.com.admin.service.AuthClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthClientService authClient;

    @GetMapping("/admin/signin")
    public ResponseEntity<?> authenticate(@RequestHeader("Authorization") String authorization) {
        return ResponseEntity.ok().body(authClient.signin(authorization).block());
    }
}
