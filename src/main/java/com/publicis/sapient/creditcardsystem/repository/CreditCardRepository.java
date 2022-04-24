package com.publicis.sapient.creditcardsystem.repository;

import com.publicis.sapient.creditcardsystem.repository.entity.CreditCardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This repository is responsible
 * for interaction with database
 * and to process on Credit Card table
 */
@Repository
public interface CreditCardRepository extends CrudRepository<CreditCardEntity, Integer> {
}
