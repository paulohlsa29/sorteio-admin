package br.com.admin.external.auth.router;

public enum AuthRouter {
    SIGNIN_URI("/signin"),
    DECODE_TOKEN_URI("/decode"),
    GET_CURRENT_USER_URI("/api/user/current-user"),
    CREATE_AFFILIATE_URI("/api/affiliate/signup");
    private String uri;
    private AuthRouter(String uri) {
        this.uri = uri;
    }
    public String uri() {
        return this.uri;
    }
}
