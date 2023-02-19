package com.example.bank_app.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test class for UuidMapper")
class UuidMapperTest {

    private final UuidMapper mapper = new UuidMapperImpl();

    @DisplayName("Test converting from UUID to String")
    @Test
    public void convertToString() {
        String uuidString = "01234567-8901-abcd-abcd-1234567890ab";
        UUID uuid = UUID.fromString(uuidString);

        String actualUuidSting = mapper.toString(uuid);

        assertEquals(uuidString, actualUuidSting);
    }

    @DisplayName("Test converting String to UUID")
    @Test
    public void convertFromString() {
        String uuidString = "01234567-8901-abcd-abcd-1234567890ab";
        UUID uuid = UUID.fromString(uuidString);

        UUID actualUuid = mapper.fromString(uuidString);

        assertEquals(uuid, actualUuid);
    }
}