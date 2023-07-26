package br.com.admin.mapper;

import br.com.admin.controller.partner.dto.CreatePartnerRequest;
import br.com.admin.external.auth.domain.CreateUserRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface CreateUserRequestMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "passwordConfirmation", ignore = true)
    @Mapping(target = "news", ignore = true)
    @Mapping(target = "policy", ignore = true)
    CreateUserRequestBody toEntity(CreatePartnerRequest partnerDto);

}
