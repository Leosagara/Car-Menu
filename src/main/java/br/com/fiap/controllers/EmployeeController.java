package br.com.fiap.controllers;

import br.com.fiap.controllers.dto.EmployeeDTO;
import br.com.fiap.model.Employee;
import br.com.fiap.services.EmployeeService;
import br.com.fiap.services.mapper.EmployeeMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(){
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeService.finById(id);
        if (employee == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(employee);
    }

   @GetMapping("/salary/{minSalary}-{maxSalary}")
    public ResponseEntity<List<Employee>> getEmployeeBySalary(@PathVariable("minSalary") Float minSalary,@PathVariable("maxSalary") Float maxSalary){
        List<Employee> employeeList=employeeService.findBySalary(minSalary,maxSalary);
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/salary/average")
    public ResponseEntity<List<Object[]>> getAverageSalary(){
        List<Object[]> averageList = employeeService.findAverageSalary();
        return ResponseEntity.ok(averageList);
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee employee = employeeService.create(employeeMapper.toEntity(employeeDTO));
        return ResponseEntity.ok(employeeMapper.toDTO(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee){
        if(employeeService.finById(id)==null){
            return ResponseEntity.notFound().build();
        }
        employee.setId(id);
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        if(employeeService.finById(id)==null){
            return ResponseEntity.notFound().build();
        }
        employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
