package com.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.I18nLocaleTranslation;
import com.demo.repository.I18nLocaleRepository;
import com.demo.repository.I18nLocaleTranslationRepository;

@RestController
public class HomeController {
	
	@Autowired
    I18nLocaleRepository localeRepository;
		
	@Autowired
	I18nLocaleTranslationRepository localeTranslationRepository;
	
	Map<String, Map> translations = new HashMap();
	
	public Map<String, Map> getTranslation() {
		List<String> locales = getLocale();
		 
		if(locales!=null) {
			for(String locale: locales) {
				translations.put(locale, getTranslationMap(locale));
			}
		}
		return translations;
	}
	
	private Map getTranslationMap(String locale) {
		Map<String, String> translationMap = new HashMap<>();
		List<I18nLocaleTranslation> translations = getTranslatedValue(locale);
		if(translations!=null) {
			for(I18nLocaleTranslation translation: translations) {
				translationMap.put(translation.getKey() ,translation.getTranslation());
			}
		}
		return translationMap;
	}
	
	private List<I18nLocaleTranslation> getTranslatedValue(String locale) {
		return localeTranslationRepository.findByLocaleCode(locale);
	}

	private List<String> getLocale() {
		return localeRepository.findLocaleCode();
	}
}
