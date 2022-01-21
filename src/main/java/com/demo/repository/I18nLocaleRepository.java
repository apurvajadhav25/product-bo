package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.model.I18nLocale;

@Repository
public interface I18nLocaleRepository extends JpaRepository<I18nLocale, Integer> {

}
