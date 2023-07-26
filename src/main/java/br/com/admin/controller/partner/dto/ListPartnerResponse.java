package br.com.admin.controller.partner.dto;

import br.com.admin.model.Partner;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListPartnerResponse {

    private Long id;
    private String cnpj;
    private String tradeName;
    private int level;
    private Boolean active;
    private Parent parent;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createdAt;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Parent {
        private Long id;
        private String tradeName;
        private int level;
    }

    public static ListPartnerResponse fromPartner(Partner p) {
        ListPartnerResponse resp = ListPartnerResponse.builder()
                .id(p.getId())
                .cnpj(p.getCnpj())
                .tradeName(p.getTradeName())
                .level(p.getLevel())
                .active(Boolean.TRUE)
                .createdAt(p.getCreatedAt())
                .build();

        if (p.getParent() != null) {
            var parent = p.getParent();
            resp.setParent(Parent.builder()
                    .id(parent.getId())
                    .tradeName(parent.getTradeName())
                    .level(parent.getLevel())
                    .build());
        }
        return resp;
    }
}
