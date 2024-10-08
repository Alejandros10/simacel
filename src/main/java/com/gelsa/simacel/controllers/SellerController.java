package com.gelsa.simacel.controllers;

import com.gelsa.simacel.models.Seller;
import com.gelsa.simacel.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;

    // Get all sellers
    @GetMapping("/all")
    public Iterable<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    // Get seller by id
    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) {
        Optional<Seller> seller = sellerRepository.findById(id);
        return seller.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add seller
    @PostMapping("/add")
    public ResponseEntity<Seller> addSeller(@RequestBody Seller seller) {
        Seller savedSeller = sellerRepository.save(seller);
        return ResponseEntity.ok(savedSeller);
    }

    // Update seller
    @PutMapping("/update/{id}")
    public ResponseEntity<Seller> updateSeller(@PathVariable Long id, @RequestBody Seller updatedSeller) {
        return sellerRepository.findById(id)
                .map(seller -> {
                    seller.setName(updatedSeller.getName());
                    seller.setDocumentNumber(updatedSeller.getDocumentNumber());
                    Seller savedSeller = sellerRepository.save(seller);
                    return ResponseEntity.ok(savedSeller);
                })
                .orElseGet(() -> {
                    updatedSeller.setId(id);
                    Seller savedSeller = sellerRepository.save(updatedSeller);
                    return ResponseEntity.ok(savedSeller);
                });
    }

    // Delete seller
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) {
        if (sellerRepository.existsById(id)) {
            sellerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get seller by Document Number
    @GetMapping("/document/{documentNumber}")
    public ResponseEntity<Seller> getSellerByDocumentNumber(@PathVariable String documentNumber) {
        Optional<Seller> seller = sellerRepository.findByDocumentNumber(documentNumber);
        return seller.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
