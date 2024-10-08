package com.gelsa.simacel.controllers;

import com.gelsa.simacel.models.Operator;
import com.gelsa.simacel.repositories.OperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/operators")
public class OperatorController {

    @Autowired
    private OperatorRepository operatorRepository;

    // Get All operators
    @GetMapping(path="/all")
    public ResponseEntity<Iterable<Operator>> getAllOperators() {
        Iterable<Operator> operators = operatorRepository.findAll();
        return ResponseEntity.ok(operators);
    }

    // Get operator by id
    @GetMapping("/{id}")
    public ResponseEntity<Operator> getOperatorById(@PathVariable Long id) {
        Optional<Operator> operator = operatorRepository.findById(id);
        return operator.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add new operator
    @PostMapping("/add")
    public ResponseEntity<Operator> addOperator(@RequestBody Operator operator) {
        Operator savedOperator = operatorRepository.save(operator);
        return ResponseEntity.ok(savedOperator);
    }

    // Update operator
    @PutMapping("/update/{id}")
    public ResponseEntity<Operator> updateOperator(@PathVariable Long id, @RequestBody Operator updatedOperator) {
        return operatorRepository.findById(id)
                .map(operator -> {
                    operator.setName(updatedOperator.getName());
                    Operator savedOperator = operatorRepository.save(operator);
                    return ResponseEntity.ok(savedOperator);
                })
                .orElseGet(() -> {
                    updatedOperator.setId(id);
                    Operator savedOperator = operatorRepository.save(updatedOperator);
                    return ResponseEntity.ok(savedOperator);
                });
    }

    // Delete operator
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOperator(@PathVariable Long id) {
        if (operatorRepository.existsById(id)) {
            operatorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
