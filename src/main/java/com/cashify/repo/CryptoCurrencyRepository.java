package com.cashify.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cashify.model.CryptoCurrency;

public interface CryptoCurrencyRepository extends JpaRepository<CryptoCurrency, Long> {

}
