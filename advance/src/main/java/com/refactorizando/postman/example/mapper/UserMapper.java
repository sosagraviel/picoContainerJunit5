package com.refactorizando.postman.example.mapper;
import com.refactorizando.postman.example.domain.user.UserApp;
import com.refactorizando.postman.example.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "userName",target = "userName")
    UserDTO userToDTO(UserApp userApp);
    UserApp dtoToUserDTO(UserDTO userDTO);
    List<UserDTO> toUserDTO(List<UserApp> userApps);
}
