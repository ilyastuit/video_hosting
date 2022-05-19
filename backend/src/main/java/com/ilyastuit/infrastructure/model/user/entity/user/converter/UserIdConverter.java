package com.ilyastuit.infrastructure.model.user.entity.user.converter;

import com.ilyastuit.model.user.entity.user.UserId;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.UUID;

@Converter
public class UserIdConverter implements AttributeConverter<UserId, String> {

    @Override
    public String convertToDatabaseColumn(UserId attribute) {
        return attribute.getId().toString();
    }

    @Override
    public UserId convertToEntityAttribute(String dbData) {
        return new UserId(UUID.fromString(dbData));
    }

}
