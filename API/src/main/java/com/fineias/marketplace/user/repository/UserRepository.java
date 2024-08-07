package com.fineias.marketplace.user.repository;

import com.fineias.marketplace.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    @Query(value = "SELECT * FROM users WHERE users.email = :email", nativeQuery = true)
    User findByEmail(@Param("email") String email);

    boolean existsByEmail(String email);

}
