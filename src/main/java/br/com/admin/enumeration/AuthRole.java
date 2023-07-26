package br.com.admin.enumeration;

public enum AuthRole {
    CUSTOMER("CUSTOMER"),
    AFFILIATE("AFFILIATE"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private String key;

    private AuthRole(String key) {
        this.key = key;
    }

    public String key() {
        return this.key;
    }
}
