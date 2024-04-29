package com.hbtpedro.onlinelibrary.repository;

import com.hbtpedro.onlinelibrary.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}