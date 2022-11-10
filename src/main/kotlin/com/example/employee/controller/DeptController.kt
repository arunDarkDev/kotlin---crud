package com.example.employee.controller

import com.example.employee.dto.DeptDto
import com.example.employee.entity.DeptEntity
import com.example.employee.service.DeptService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("dept")
class DeptController(var deptService : DeptService){

    @GetMapping()
    fun check() : String{
        return "Vanakam da Mapla Dept lendhu.."
    }

    @GetMapping("all")
    @ResponseStatus(HttpStatus.OK)
    fun getAllRecords() : MutableIterable<DeptEntity> {
        return this.deptService.getAllRecords();
    }

    @PostMapping()
    fun postRecord(@RequestBody deptEntity : DeptEntity): DeptEntity{
        return this.deptService.postRecord(deptEntity);
    }

    @PutMapping("{id}")
    fun updateRecord(@RequestBody deptEntity : DeptEntity,@PathVariable id:Int): ResponseEntity<Any> {
        return this.deptService.updateRecord(deptEntity,id);
    }

    @DeleteMapping("{id}")
    fun deleteRecord(@PathVariable id:Int): ResponseEntity<Any> {
        return this.deptService.deleteRecord(id);
    }
}