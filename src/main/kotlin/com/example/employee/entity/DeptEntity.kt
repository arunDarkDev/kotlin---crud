package com.example.employee.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "dept")
data class DeptEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "deptSequence")
    @SequenceGenerator(name ="deptSequence", sequenceName = "deptSequence", allocationSize = 1,initialValue = 100)
    var id:Int? = null,
    var name : String
)