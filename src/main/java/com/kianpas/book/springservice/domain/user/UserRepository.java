package com.kianpas.book.springservice.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //처음가입 여부 판단
    Optional<User> findByEmail(String email);
}
