package com.gelsa.simacel.repositories;

import com.gelsa.simacel.models.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Optional<Seller> findByDocumentNumber(String documentNumber);
}
