package com.ekomodatech.festivanow.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ekomodatech.festivanow.event.entity.Permission;

import com.ekomodatech.festivanow.event.repository.PermissionRepository;

public class PermissionServiceImpl implements PermissionService{

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    @Override
    public Permission findByIdPermission(Long id) {
        return permissionRepository.findById(id).orElse(null); 
    }

    @Override
    public List<Permission> findAllPermissions() {
        return permissionRepository.findAll();
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }
    
}
