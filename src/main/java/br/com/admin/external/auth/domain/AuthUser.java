package br.com.admin.external.auth.domain;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUser {

    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private Boolean verified;
    private String role;
    private AddressInfo addressInfo;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class AddressInfo {
        private Long id;
        private String number;
        private String addressLine;
        private Address address;

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        private class Address {
            private String cep;
            private String street;
            private String district;
            private String city;
            private String state;
        }
    }

}
