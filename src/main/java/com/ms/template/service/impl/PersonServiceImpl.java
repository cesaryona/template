package com.ms.template.service.impl;

import com.ms.template.controller.request.PersonRequestBody;
import com.ms.template.controller.response.PersonResponseBody;
import com.ms.template.exceptions.ApiException;
import com.ms.template.exceptions.handler.PersonExceptionEnum;
import com.ms.template.mapper.PersonMapper;
import com.ms.template.repository.PersonRepository;
import com.ms.template.service.PersonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonMapper mapper;
    private final PersonRepository repository;

    @Override
    public Page<PersonResponseBody> getAll(final Integer page, final Integer size, final String sortDirection, final String sortedBy) {
        Sort.Direction direction = sortDirection.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortedBy));

        return repository.findAll(pageable).map(mapper::toResponseBody);
    }

    @Override
    public PersonResponseBody savePerson(final PersonRequestBody request) {
        try {
            var entity = repository.save(mapper.toEntity(request));
            return mapper.toResponseBody(entity);
        } catch (DataIntegrityViolationException exception) {
            throw new ApiException(PersonExceptionEnum.EMAIL_ALREADY_EXISTS);
        }
    }

    @Override
    public PersonResponseBody getByEmail(final String email) {
        var entity = repository.findByEmail(email)
                .orElseThrow(() -> new ApiException(PersonExceptionEnum.PERSON_NOT_FOUND));
        return mapper.toResponseBody(entity);
    }

    @Override
    @Transactional
    public void deleteByEmail(final String email) {
        repository.deleteByEmail(email);
    }
}
