package com.example.employee.entity

import org.hibernate.annotations.GeneratorType
import javax.persistence.*

@Entity
@Table(name = "employee")
data class EmployeeEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var Id:Int? = null,
    var name:String
)