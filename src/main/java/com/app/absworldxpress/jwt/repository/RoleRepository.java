package com.app.absworldxpress.jwt.repository;


import com.app.absworldxpress.jwt.model.Role;
import com.app.absworldxpress.jwt.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    // Optional<Role> findByName(RoleName roleName);
    Optional<Role> findByName(RoleName role);
}