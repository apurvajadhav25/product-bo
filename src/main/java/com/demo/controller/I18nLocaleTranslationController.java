package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.demo.model.I18nLocale;
import com.demo.model.I18nLocaleTranslation;
import com.demo.model.UserDetail;
import com.demo.repository.I18nLocaleRepository;
import com.demo.repository.I18nLocaleTranslationRepository;

@RestController
@CrossOrigin
public class I18nLocaleTranslationController {
	
	@Autowired
	I18nLocaleTranslationRepository localeTranslationRepository;
	
	@GetMapping("/localeTranslation")
	public  Iterable<I18nLocaleTranslation> getAllLocale(){
		return  localeTranslationRepository.findAll();
	}
	
	@PostMapping("/localeTranslation")
	public I18nLocaleTranslation createLocale(@RequestBody I18nLocaleTranslation locale) {
		return localeTranslationRepository.save(locale);
	    }
	
	@DeleteMapping("/localeTranslation/{id}")
	public String deleteLocale(@PathVariable(value = "id") Integer id) {
		I18nLocaleTranslation locale = localeTranslationRepository.findById(id).get();
	     localeTranslationRepository.delete(locale);
	     return "Product with id  "+id+" deleted successfully";
	    }
	
	@GetMapping("/localeTranslation/{id}")
	public I18nLocaleTranslation getLocaleById(@PathVariable(value = "id") Integer id) {
		I18nLocaleTranslation locale = localeTranslationRepository.findById(id).get();
		return locale;	
	}
	
	@PutMapping("/localeTranslation/{id}")
    public ResponseEntity<I18nLocaleTranslation> updateLocale(@PathVariable(value = "id") Integer id,
                                                 @RequestBody I18nLocaleTranslation locale) {
		I18nLocaleTranslation localeDetails = localeTranslationRepository.findById(id).get();

        localeDetails.setKey(locale.getKey());
        localeDetails.setLocaleCode(locale.getLocaleCode());
        localeDetails.setTranslation(locale.getTranslation());
        
        final I18nLocaleTranslation updatedLocale = localeTranslationRepository.save(localeDetails);
        return ResponseEntity.ok(updatedLocale);
    }
	
	@GetMapping("/localeTranslationByKey")
	public I18nLocaleTranslation updateCartIds(@RequestParam(name = "key", required = false) String key,
			                                   @RequestParam(name = "localeCode", required = false) String localeCode){
		return localeTranslationRepository.findByKey(key, localeCode);
		
	}

}
