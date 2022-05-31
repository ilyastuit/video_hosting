package com.ilyastuit.infrastructure.model.user.entity.user.converter;

import com.ilyastuit.model.user.entity.user.UserStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UserStatusConverter implements AttributeConverter<UserStatus, String> {

    @Override
    public String convertToDatabaseColumn(UserStatus attribute) {
        return attribute.name();
    }

    @Override
    public UserStatus convertToEntityAttribute(String dbData) {
        return UserStatus.valueOf(dbData);
    }
}
