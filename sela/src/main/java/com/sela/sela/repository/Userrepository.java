package com.sela.sela.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sela.sela.model.User;

@Repository
public interface Userrepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}

