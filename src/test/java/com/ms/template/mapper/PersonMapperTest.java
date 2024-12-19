package com.ms.template.mapper;

import com.ms.template.controller.request.PersonRequestBody;
import com.ms.template.entity.PersonEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        PersonMapperImpl.class
})
class PersonMapperTest {

    private final static String FIRST_NAME = "JOHN";
    private final static String LAST_NAME = "DOE";
    private final static String DOCUMENT = "11122233344";
    private final static String EMAIL = "test@test.com";

    @Autowired
    private PersonMapper mapper;

    @Test
    void shouldConvertPersonRequestBodyToEntity() {
        var requestBody = new PersonRequestBody(FIRST_NAME, LAST_NAME, DOCUMENT, EMAIL);

        var response = mapper.toEntity(requestBody);

        assertNotNull(response);
        assertEquals(FIRST_NAME, response.getFirstName());
        assertEquals(LAST_NAME, response.getLastName());
        assertEquals(DOCUMENT, response.getDocument());
        assertEquals(EMAIL, response.getEmail());

    }

    @Test
    void shouldConvertPersonEntityToResponseBody() {
        var entity = PersonEntity.builder()
                .id(1L)
                .firstName(FIRST_NAME)
                .lastName(LAST_NAME)
                .document(DOCUMENT)
                .email(EMAIL)
                .build();

        var response = mapper.toResponseBody(entity);

        assertNotNull(response);
        assertEquals(FIRST_NAME, response.firstName());
        assertEquals(LAST_NAME, response.lastName());
        assertEquals(DOCUMENT, response.document());
        assertEquals(EMAIL, response.email());
    }
}