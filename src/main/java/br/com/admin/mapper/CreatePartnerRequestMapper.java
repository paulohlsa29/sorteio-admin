package br.com.admin.mapper;

import br.com.admin.controller.partner.dto.CreatePartnerRequest;
import br.com.admin.model.Partner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreatePartnerRequestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "level", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "children", ignore = true)
    @Mapping(target = "active", ignore = true)
    Partner toEntity(CreatePartnerRequest partnerDto);
}
