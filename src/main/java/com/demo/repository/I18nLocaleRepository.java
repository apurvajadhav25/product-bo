package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.demo.model.I18nLocale;

@Repository
public interface I18nLocaleRepository extends JpaRepository<I18nLocale, Integer> {
	
	@Query("SELECT localeCode FROM I18nLocaleTranslation")
	 List<String> findLocaleCode();

}
