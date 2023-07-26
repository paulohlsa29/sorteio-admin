package br.com.admin.controller.partner.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreatePartnerRequest {

    // user
    private String fullName;
    private String cep;
    private String number;
    private String addressLine;
    private String email;
    private String cpf;
    private String phone;

    // partner
    private Long parentId;
    private String cnpj;
    private String tradeName;
    private BigDecimal salesCommission;
    private BigDecimal clickCommission;
}
