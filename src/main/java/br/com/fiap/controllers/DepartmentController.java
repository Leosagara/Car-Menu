package br.com.fiap.controllers;

import br.com.fiap.controllers.dto.DepartmentDTO;
import br.com.fiap.controllers.dto.EmployeeDTO;
import br.com.fiap.model.Department;
import br.com.fiap.model.Employee;
import br.com.fiap.services.DepartmentService;
import br.com.fiap.services.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Long id){
        Department department = departmentService.finById(id);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Department> getDepartmentByName(@PathVariable String name){
        Department department = departmentService.findByName(name);
        if (department == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(department);
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO departmentDTO){
        Department department = departmentService.create(departmentMapper.toEntity(departmentDTO));
        return ResponseEntity.ok(departmentMapper.toDTO(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable Long id,@RequestBody Department department){
        if(departmentService.finById(id)==null){
            return ResponseEntity.notFound().build();
        }
        department.setId(id);
        return ResponseEntity.ok(departmentService.create(department));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id){
        if(departmentService.finById(id)==null){
            return ResponseEntity.notFound().build();
        }
        departmentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
