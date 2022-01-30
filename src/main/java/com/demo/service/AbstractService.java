package com.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.I18nLocaleTranslation;
import com.demo.repository.I18nLocaleRepository;
import com.demo.repository.I18nLocaleTranslationRepository;

@Service
public class AbstractService {
	
	@Autowired
	I18nLocaleRepository localeRepository;
	
	@Autowired
	I18nLocaleTranslationRepository localeTranslationRepository;
	
	public List<I18nLocaleTranslation> getTranslatedValue(String locale) {
		//return null;
		return localeTranslationRepository.findByLocaleCode(locale);
	}

	public  List<String> getLocale() {
		return localeRepository.findLocaleCode();
		//return null;
	}
	
	

}
