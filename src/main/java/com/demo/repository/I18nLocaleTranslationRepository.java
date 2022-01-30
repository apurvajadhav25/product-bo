package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.demo.model.I18nLocaleTranslation;

@Repository
public interface I18nLocaleTranslationRepository extends JpaRepository<I18nLocaleTranslation, Integer> {
	
	 @Query("SELECT c FROM I18nLocaleTranslation c WHERE c.key= :key and c.localeCode= :localeCode")
	 I18nLocaleTranslation findByKey(@Param("key") String key, @Param("localeCode") String localeCode);
	 
	 @Query("SELECT c FROM I18nLocaleTranslation c WHERE c.localeCode= :localeCode")
	 List<I18nLocaleTranslation> findByLocaleCode(@Param("localeCode") String localeCode);

}
