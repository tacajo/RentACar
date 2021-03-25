package com.agent.converter;

import com.agent.dto.UserDTO;
import com.agent.model.User;
import org.springframework.core.convert.converter.Converter;

public class UserDTOToUserConverter implements Converter<UserDTO, User> {
    @Override
    public User convert(UserDTO userDTO) {
        return new User().builder()
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .build();

    }
}
