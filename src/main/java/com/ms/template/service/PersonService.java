package com.ms.template.service;

import com.ms.template.controller.request.PersonRequestBody;
import com.ms.template.controller.response.PersonResponseBody;
import org.springframework.data.domain.Page;

public interface PersonService {

    Page<PersonResponseBody> getAll(Integer page, Integer size, String sortDirection, String sortedBy);

    PersonResponseBody savePerson(PersonRequestBody request);

    PersonResponseBody getByEmail(String email);

    void deleteByEmail(String email);
}
