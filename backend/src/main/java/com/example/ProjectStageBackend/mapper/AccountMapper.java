package com.example.ProjectStageBackend.mapper;

import com.example.ProjectStageBackend.model.AccountModel;
import com.example.ProjectStageBackend.resource.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {
    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mapping(target = "password", source = "password")
    @Mapping(target = "id", source = "id")
    AccountModel dtoToModel(AccountDTO accountDTO);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "id", source = "id")
    AccountDTO modelToDto(AccountModel accountModel);
}
