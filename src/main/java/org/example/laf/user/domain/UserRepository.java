package org.example.laf.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;


interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUuid(UUID uuid);
}