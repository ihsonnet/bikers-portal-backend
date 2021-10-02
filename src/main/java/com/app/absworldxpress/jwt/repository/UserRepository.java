package com.app.absworldxpress.jwt.repository;



import com.app.absworldxpress.jwt.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT id FROM User user WHERE username = ?1")
    Optional<String> findAuthUsersById(String username);

    List<User> findByCreatedBy(String id);

//    Optional<User> findByGeneratedOTP(int otp);
//
//    void deleteByEmail(String email);


}