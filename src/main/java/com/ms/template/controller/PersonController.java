package com.ms.template.controller;

import com.ms.template.controller.request.PersonRequestBody;
import com.ms.template.controller.response.PersonResponseBody;
import com.ms.template.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/persons")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService service;

    @GetMapping
    public Page<PersonResponseBody> findAll(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "10") Integer size,
                                            @RequestParam(defaultValue = "DESC") String sortDirection,
                                            @RequestParam(defaultValue = "createdAt") String sortedBy) {

        return service.getAll(page, size, sortDirection, sortedBy);
    }

    @PostMapping
    public PersonResponseBody save(@RequestBody PersonRequestBody request) {
        return service.savePerson(request);
    }

    @GetMapping("/by-email")
    public PersonResponseBody getByEmail(@RequestParam(name = "email", required = true) String email) {
        return service.getByEmail(email);
    }

    @DeleteMapping
    public void delete(@RequestParam(name = "email", required = true) String email) {
        service.deleteByEmail(email);
    }
}
