package com.gelsa.simacel.controllers;

import com.gelsa.simacel.models.Sale;
import com.gelsa.simacel.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/sales")
@CrossOrigin(origins = "*")
public class SaleController {

    @Autowired
    private SaleRepository saleRepository;

    // Get all sales
    @GetMapping("/all")
    public ResponseEntity<Iterable<Sale>> getAllSales() {
        Iterable<Sale> sales = saleRepository.findAllByOrderBySaleDateDesc();
        return ResponseEntity.ok(sales);
    }

    // Get sale by id
    @GetMapping("/{id}")
    public ResponseEntity<Sale> getSaleById(@PathVariable Long id) {
        Optional<Sale> sale = saleRepository.findById(id);
        return sale.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add sale
    @PostMapping("/add")
    public ResponseEntity<Sale> addSale(@RequestBody Sale sale) {
        Sale savedSale = saleRepository.save(sale);
        return ResponseEntity.status(201).body(savedSale);
    }

    // Update sale
    @PutMapping("/update/{id}")
    public ResponseEntity<Sale> updateSale(@PathVariable Long id, @RequestBody Sale updatedSale) {
        return saleRepository.findById(id)
                .map(sale -> {
                    sale.setPrice(updatedSale.getPrice());
                    sale.setSaleDate(updatedSale.getSaleDate());
                    sale.setPhoneNumber(updatedSale.getPhoneNumber());
                    sale.setSeller(updatedSale.getSeller());
                    sale.setOperator(updatedSale.getOperator());
                    Sale savedSale = saleRepository.save(sale);
                    return ResponseEntity.ok(savedSale);
                })
                .orElseGet(() -> {
                    updatedSale.setId(id);
                    Sale savedSale = saleRepository.save(updatedSale);
                    return ResponseEntity.ok(savedSale);
                });
    }

    // Delete sale
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {
        if (saleRepository.existsById(id)) {
            saleRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
