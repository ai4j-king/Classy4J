package com.classy4j.config;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.hibernate.type.descriptor.converter.spi.BasicValueConverter;
import java.util.UUID;

@Converter
public class UUIDAttributeConverter implements AttributeConverter<UUID, UUID> {
    @Override
    public UUID convertToDatabaseColumn(UUID uuid) {
        return uuid;
    }

    @Override
    public UUID convertToEntityAttribute(UUID dbData) {
//        if (dbData instanceof UUID) {
//            return (UUID) dbData;
//        }
//        if (dbData instanceof String) {
//            return UUID.fromString((String) dbData);
//        }
        return dbData;
    }
}
