package br.com.admin.service;

import br.com.admin.external.auth.domain.AuthUser;
import br.com.admin.external.auth.domain.CreateUserRequestBody;
import br.com.admin.external.auth.domain.SigninResponse;
import br.com.admin.external.auth.domain.TokenUser;
import br.com.admin.external.auth.router.AuthRouter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static br.com.admin.external.auth.router.AuthRouter.*;

@Service
public class AuthClientService {
    private final WebClient authWebClient;

    public AuthClientService(WebClient authWebClient) {
        this.authWebClient = authWebClient;
    }

    public Mono<SigninResponse> signin(String authorization) {
        return authWebClient.get()
                .uri(SIGNIN_URI.uri())
                .header(HttpHeaders.AUTHORIZATION, authorization)
                .retrieve()
                .bodyToMono(SigninResponse.class);
    }

    public Mono<SigninResponse> decodeToken(String token) {
        return authWebClient.get()
                .uri(DECODE_TOKEN_URI.uri())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .retrieve()
                .bodyToMono(SigninResponse.class);
    }

    public Mono<AuthUser> getAuthenticatedUserInfo(String token) {
        return authWebClient.get()
                .uri(GET_CURRENT_USER_URI.uri())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .retrieve()
                .bodyToMono(AuthUser.class);
    }

    public Mono<AuthUser> create(String token, CreateUserRequestBody user, AuthRouter route) {
        return authWebClient.post()
                .uri(route.uri())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(token))
                .body(Mono.just(user), CreateUserRequestBody.class)
                .retrieve()
                .bodyToMono(AuthUser.class);
    }
}
