package com.example.employee.repository

import com.example.employee.entity.DeptEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DeptRepository :CrudRepository<DeptEntity,Int> {
}