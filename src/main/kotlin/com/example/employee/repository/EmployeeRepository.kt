package com.example.employee.repository

import com.example.employee.dto.EmployeeDto
import com.example.employee.entity.EmployeeEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : CrudRepository<EmployeeEntity,Int>{
    fun findByNameIgnoreCase(name : String) : List<EmployeeEntity>;
}