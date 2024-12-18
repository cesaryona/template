package com.ms.template.repository;

import com.ms.template.entity.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    Optional<PersonEntity> findByEmail(String email);

    void deleteByEmail(String email);
}
