package com.example.employee.service

import com.example.employee.controller.EmployeeController
import com.example.employee.dto.EmployeeDto
import com.example.employee.repository.EmployeeRepository
import com.example.employee.entity.EmployeeEntity
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

@Component
class EmployeeService(val employeeRepository: EmployeeRepository){

    fun getAllEmployees(): MutableIterable<EmployeeEntity> {
       return this.employeeRepository.findAll()
    }

    fun getOneEmployee(id: Int): Optional<EmployeeDto> {
        if(this.employeeRepository.findById(id).isEmpty)
        {
            return Optional.empty();
        }
        return this.employeeRepository.findById(id).map {
            EmployeeDto(it.Id,it.name);
        }
    }

    fun getByName(name : String): List<EmployeeDto> {
        return this.employeeRepository.findByNameIgnoreCase(name).map {
            EmployeeDto(it.Id,it.name);
        };
    }

    fun addEmployee(employeeDto: EmployeeDto) : EmployeeDto  {
        var employeeEntity = employeeDto.let {
            EmployeeEntity(it.Id,it.name)
        }
        return this.employeeRepository.save(employeeEntity).let {
            EmployeeDto(it.Id,it.name);
        };
    }

    fun deleteEmployee(id: Int) : String{
        var message :String;
        try {
            this.employeeRepository.deleteById(id);
            message = "The record with Id $id is deleted successfully.."
        }
        catch (e : Exception)
        {
            message = e.message.toString();
        }
        return message;
    }

    fun updateEmployee(id: Int, employeeDto: EmployeeDto) : Optional<EmployeeDto> {
        if(this.employeeRepository.findById(id).isPresent){
            var employeeEntity = employeeDto.let{
                EmployeeEntity(it.Id,it.name);
            }
            return this.employeeRepository.save(employeeEntity).let {
                Optional.ofNullable(EmployeeDto(it.Id,it.name));
            }
        }
        return Optional.empty();
    }
}