package com.rinattzak.userservice.repository;

import com.rinattzak.userservice.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Long> {
    Optional<Contact> findByUserId(Long userId);
}
