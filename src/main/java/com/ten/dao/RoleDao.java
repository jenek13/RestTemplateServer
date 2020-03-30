package com.ten.dao;

import com.ten.model.Role;

import java.util.List;

public interface RoleDao {

    Role getRoleByName(String roleName);
    void save(Role role );

    Role getById(Long id);

    List<Role> getAll();

    void update(Role group);

    void deleteById(long id);



}
