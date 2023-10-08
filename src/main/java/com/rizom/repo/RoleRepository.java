package com.rizom.repo;

import com.rizom.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

     @Query("SELECT r FROM Role r WHERE r.id NOT IN (SELECT ur.id FROM User u JOIN u.roles ur WHERE u.id = :userId)")
     List<Role> findRolesNotOwnedByUser(Long userId);
}