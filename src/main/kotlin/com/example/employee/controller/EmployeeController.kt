package com.example.employee.controller


import com.example.employee.dto.EmployeeDto
import com.example.employee.entity.EmployeeEntity
import com.example.employee.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("employee")
class EmployeeController(val employeeService: EmployeeService) {

    @GetMapping()
    fun getGreet() : String{
        return "Vanakam da mpla Controller lendhu.."
    }

    @GetMapping("getAll")
    fun getAllEmployees(): MutableIterable<EmployeeEntity> {
        return this.employeeService.getAllEmployees()
    }

    @GetMapping("get/id/{id_no}")
    fun getOneEmployee(@PathVariable("id_no") id : Int): ResponseEntity<Any>{
        var employee = this.employeeService.getOneEmployee(id);
        if(employee.isPresent){
            return ResponseEntity(employee.get(),HttpStatus.OK);
        }
        return ResponseEntity("The given id is invalid..",HttpStatus.OK);
    }

    @PostMapping("post")
    fun addEmployee(@RequestBody employeeDto: EmployeeDto) :ResponseEntity<Any>{
        if(employeeDto.Id is Int || employeeDto.Id == null) {
            return ResponseEntity(this.employeeService.addEmployee(employeeDto), HttpStatus.OK)
        }
        return ResponseEntity("Given data is invalid",HttpStatus.BAD_REQUEST)
    }

    @GetMapping("get/name/{name}")
    fun getByName(@PathVariable name : String): List<EmployeeDto> {
       return this.employeeService.getByName(name);
    }

    @DeleteMapping("delete/{id}")
    fun deleteEmployee(@PathVariable("id") Id : Int) : ResponseEntity<String> {
        var message = this.employeeService.deleteEmployee(Id);
        if(message == "The record with Id $Id is deleted successfully..")
        {
            return ResponseEntity(message,HttpStatus.OK);
        }
        return ResponseEntity(message,HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("delete")
    fun deleteByPassNull() :ResponseEntity<String> {
        return ResponseEntity("Id must be specified..",HttpStatus.BAD_REQUEST);
    }

    @PutMapping("update/{id}")
    fun updateEmployee(@PathVariable id : Int ,@RequestBody employeeDto: EmployeeDto) : ResponseEntity<Any>{
       var opt = this.employeeService.updateEmployee(id,employeeDto);
        if(opt.isPresent){
            return ResponseEntity(opt.get(),HttpStatus.OK);
        }
        return ResponseEntity("Specified id is not present in the database, Not able to update",HttpStatus.BAD_REQUEST);
    }

    @PostMapping("postWithDeptId")
    fun addEmployeeWithDeptid(@RequestBody employeeDto: EmployeeDto,@RequestParam(required = false) deptId : Int = 0): EmployeeEntity {
       return this.employeeService.addEmployeeWithDeptId(employeeDto,deptId);
    }
}