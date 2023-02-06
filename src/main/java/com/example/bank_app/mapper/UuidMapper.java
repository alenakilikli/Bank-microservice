package com.example.bank_app.mapper;

import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UuidMapper {

    default String toString(UUID uuid) {
        return uuid.toString();
    }

    default UUID fromString(String uuid) {
        return UUID.fromString(uuid);
    }
}
