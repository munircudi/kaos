package com.rizom.service;

import com.rizom.model.Role;
import com.rizom.model.User;
import com.rizom.repo.RoleRepository;
import com.rizom.repo.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleService(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    public void save(Role role) {

        roleRepository.save(role);
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        roleRepository.deleteById(id);
    }

    public void assignRole(Long userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        Role role = roleRepository.findById(roleId).orElse(null);
        assert user != null;
        Set<Role> userRoles = user.getRoles();
        userRoles.add(role);
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    public void unAssignRole(Long userId, Integer roleId) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        Set<Role> userRoles = user.getRoles();
        userRoles.removeIf(x -> Objects.equals(x.getId(), roleId));
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    public Set<Role> getUserRoles(User user) {
        return user.getRoles();
    }

    public List<Role> getUserNotRoles(User user) {
        return roleRepository.findRolesNotOwnedByUser(user.getId());
    }


}