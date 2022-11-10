package com.example.employee.entity

import org.hibernate.annotations.GeneratorType
import org.springframework.dao.support.DataAccessUtils
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "employee")
data class EmployeeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var Id:Int? = null,
    var name:String,
    var age: Int,
    var gender: String,
    @ManyToOne
    @JoinColumn(name="deptId", referencedColumnName = "id")
    var deptEntity: DeptEntity?
)