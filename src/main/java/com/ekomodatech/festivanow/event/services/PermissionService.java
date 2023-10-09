package com.ekomodatech.festivanow.event.services;

import java.util.List;


import com.ekomodatech.festivanow.event.entity.Permission;

public interface PermissionService {
    public Permission save(Permission permission);
    public Permission findByIdPermission(Long id);
    public List<Permission> findAllPermissions();
    public void deletePermission(Long id);
}
