package com.example.mapper;

import com.example.dto.UserSignUpRequestDTO;
import com.example.entity.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface StudentSignUpRequestMapper {
    UserEntity sourceToDestination(UserSignUpRequestDTO source);

    UserSignUpRequestDTO destinationToSource(UserEntity destination);
}
