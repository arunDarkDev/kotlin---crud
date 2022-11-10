package com.example.employee.service

import com.example.employee.dto.EmployeeDto
import com.example.employee.repository.EmployeeRepository
import com.example.employee.entity.EmployeeEntity
import com.example.employee.repository.DeptRepository
import org.springframework.stereotype.Component
import java.lang.Exception
import java.util.*

@Component
class EmployeeService(val employeeRepository: EmployeeRepository,val deptRepository: DeptRepository){

    fun getAllEmployees(): MutableIterable<EmployeeEntity> {
       return this.employeeRepository.findAll()
    }

    fun getOneEmployee(id: Int): Optional<EmployeeDto> {
        if(this.employeeRepository.findById(id).isEmpty)
        {
            return Optional.empty();
        }
        return this.employeeRepository.findById(id).map {
            EmployeeDto(it.Id,it.name,it.age,it.gender,null);
        }
    }

    fun getByName(name : String): List<EmployeeDto> {
        return this.employeeRepository.findByNameIgnoreCase(name).map {
            EmployeeDto(it.Id,it.name,it.age,it.gender,null);
        };
    }

    fun addEmployee(employeeDto: EmployeeDto) : EmployeeDto  {
        var employeeEntity = employeeDto.let {
            EmployeeEntity(it.Id,it.name,it.age,it.gender,null)
        }
        return this.employeeRepository.save(employeeEntity).let {
            EmployeeDto(it.Id,it.name,it.age,it.gender,null);
        };
    }

    fun deleteEmployee(id: Int) : String{
        var message :String;
        message = try {
            this.employeeRepository.deleteById(id);
            "The record with Id $id is deleted successfully.."
        } catch (e : Exception) {
            e.message.toString();
        }
        return message;
    }

    fun updateEmployee(id: Int, employeeDto: EmployeeDto) : Optional<EmployeeDto> {
        if(this.employeeRepository.findById(id).isPresent){
            var employeeEntity = employeeDto.let{
                EmployeeEntity(it.Id,it.name,it.age,it.gender,null);
            }
            return this.employeeRepository.save(employeeEntity).let {
                Optional.ofNullable(EmployeeDto(it.Id,it.name,it.age,it.gender,null));
            }
        }
        return Optional.empty();
    }

    fun addEmployeeWithDeptId(employeeDto: EmployeeDto, deptId: Int): EmployeeEntity {
        var deptEntity = if(deptId != 0)
        {
            this.deptRepository.findById(deptId).get();
        }
        else
        {
            null;
        }
        var employeeEntity = employeeDto.let {
            EmployeeEntity(it.Id,it.name,it.age,it.gender,deptEntity);
        }
        return this.employeeRepository.save(employeeEntity);
    }
}