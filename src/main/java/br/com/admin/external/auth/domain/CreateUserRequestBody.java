package br.com.admin.external.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestBody {
    private String fullName;
    private String cpf;
    private String email;
    private String cep;
    private String number;
    private String addressLine;
    private String phone;
    private String password;
    private String passwordConfirmation;
    private Boolean news;
    private Boolean policy;
}
