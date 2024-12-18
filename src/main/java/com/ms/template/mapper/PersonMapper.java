package com.ms.template.mapper;

import com.ms.template.controller.request.PersonRequestBody;
import com.ms.template.controller.response.PersonResponseBody;
import com.ms.template.entity.PersonEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        builder = @Builder(disableBuilder = true)
)
public interface PersonMapper {

    PersonEntity toEntity(PersonRequestBody requestBody);

    PersonResponseBody toResponseBody(PersonEntity entity);
}
