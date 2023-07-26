package br.com.admin.util;

public enum Const {
    AUTHORIZATION_HEADER("Authorization"),
    BEARER("Bearer "),
    TOKEN("token"),
    AUTHENTICATED_USER("user"),
    ID_CANNOT_BE_NULL("ID_CANNOT_BE_NULL"),
    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND");
    
    private final String label;

    private Const(String label) {
        this.label = label;
    }

    public String label() {
        return this.label;
    }
}
