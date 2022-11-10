package com.example.employee.service

import com.example.employee.dto.DeptDto
import com.example.employee.entity.DeptEntity
import com.example.employee.entity.EmployeeEntity
import com.example.employee.repository.DeptRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeptService(var deptRepository: DeptRepository){
    fun getAllRecords(): MutableIterable<DeptEntity> {
        return this.deptRepository.findAll();
    }

    fun postRecord(deptEntity : DeptEntity): DeptEntity {
       return this.deptRepository.save(deptEntity)
    }

    fun updateRecord(deptEntity: DeptEntity, id: Int): ResponseEntity<Any> {
        if(this.deptRepository.findById(id).isPresent){
            return ResponseEntity(this.deptRepository.save(
                DeptEntity(deptEntity.id,deptEntity.name)
            ),HttpStatus.OK);
        }
        return ResponseEntity("Not found",HttpStatus.NOT_FOUND);
    }

    fun deleteRecord(id: Int): ResponseEntity<Any> {
        if(this.deptRepository.findById(id).isPresent){
            this.deptRepository.deleteById(id);
            return ResponseEntity("The record with $id is deleted Successfully",HttpStatus.OK);
        }
        return ResponseEntity("Not found",HttpStatus.NOT_FOUND);
    }

}