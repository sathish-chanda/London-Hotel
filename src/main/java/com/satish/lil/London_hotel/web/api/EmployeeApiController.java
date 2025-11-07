package com.satish.lil.London_hotel.web.api;

import com.satish.lil.London_hotel.data.entity.Employee;
import com.satish.lil.London_hotel.data.repository.EmployeeRepository;
import com.satish.lil.London_hotel.web.exception.BadRequestException;
import com.satish.lil.London_hotel.web.exception.NotFoundException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/employees")
public class EmployeeApiController {
    private final EmployeeRepository employeeRepository;

    public EmployeeApiController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    @GetMapping
    public CollectionModel<EntityModel<Employee>> getAllEmployees() {
        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream().map(employee ->
                EntityModel.of(employee,
                        linkTo(methodOn(EmployeeApiController.class).getEmployee(employee.getId())).withSelfRel(),
                        linkTo(methodOn(EmployeeApiController.class).getAllEmployees()).withRel("employees")
                )).collect(Collectors.toList());
        return CollectionModel.of(employees, linkTo(methodOn(EmployeeApiController.class).getAllEmployees()).withSelfRel());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    @GetMapping("/{id}")
    public EntityModel<Employee> getEmployee(@PathVariable("id") long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(employee.isEmpty()) {
            throw new NotFoundException("Room not found with id: " + id);
        }
        return EntityModel.of(employee.get(),
                linkTo(methodOn(EmployeeApiController.class).getEmployee(id)).withSelfRel(),
                linkTo(methodOn(EmployeeApiController.class).getAllEmployees()).withRel("employees"));
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@PathVariable("id") long id, @RequestBody Employee employee) {
        if(id != employee.getId()) {
            throw new BadRequestException("Id on path doesn't match body");
        }
        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeRepository.deleteById(id);
    }
}
