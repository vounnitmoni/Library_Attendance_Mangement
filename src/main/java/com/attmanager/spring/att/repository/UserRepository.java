package com.attmanager.spring.att.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attmanager.spring.att.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Optional<User> findById(Long id);

  @Query(value = "select password from attmanger.users where username = :username", nativeQuery = true)
  String userPassword(@Param("username") String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
