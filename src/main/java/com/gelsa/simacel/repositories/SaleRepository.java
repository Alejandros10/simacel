package com.gelsa.simacel.repositories;

import com.gelsa.simacel.models.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Long> {
}
