package com.ilyastuit.model.user.entity.user.converter;

import com.ilyastuit.model.user.entity.user.Email;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class EmailConverter implements AttributeConverter<Email, String> {

    @Override
    public String convertToDatabaseColumn(Email attribute) {
        return attribute.getEmailValue();
    }

    @Override
    public Email convertToEntityAttribute(String dbData) {
        return new Email(dbData);
    }

}
