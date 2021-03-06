package com.app.bikersportal.jwt.repository;


import com.app.bikersportal.jwt.model.Role;
import com.app.bikersportal.jwt.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName role);
}