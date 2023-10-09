package com.ekomodatech.festivanow.event.services;

import java.util.List;

import com.ekomodatech.festivanow.event.entity.Department;

public interface DepartmentService {
    public Department save(Department department);
    public Department findByIdDepartment(Long id);
    public List<Department> findAllDepartment();
    public void deleteDepartment(Long id);
}
