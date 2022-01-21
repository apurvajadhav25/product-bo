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
import org.springframework.web.bind.annotation.RestController;
import com.demo.model.I18nLocale;
import com.demo.repository.I18nLocaleRepository;

@RestController
@CrossOrigin
public class I18nLocaleController {
	
	@Autowired
	I18nLocaleRepository localeRepository;
	
	@GetMapping("/locale")
	public  Iterable<I18nLocale> getAllLocale(){
		return  localeRepository.findAll();
	}
	
	@PostMapping("/locale")
	public I18nLocale createLocale(@RequestBody I18nLocale locale) {
		return localeRepository.save(locale);
	    }
	
	@DeleteMapping("/locale/{id}")
	public String deleteLocale(@PathVariable(value = "id") Integer id) {
	     I18nLocale locale = localeRepository.findById(id).get();
	     localeRepository.delete(locale);
	     return "Product with id  "+id+" deleted successfully";
	    }
	
	@GetMapping("/locale/{id}")
	public I18nLocale getLocaleById(@PathVariable(value = "id") Integer id) {
		I18nLocale locale = localeRepository.findById(id).get();
		return locale;	
	}
	
	@PutMapping("/locale/{id}")
    public ResponseEntity<I18nLocale> updateLocale(@PathVariable(value = "id") Integer id,
                                                 @RequestBody I18nLocale locale) {
        I18nLocale localeDetails = localeRepository.findById(id).get();

        localeDetails.setCode(locale.getCode());
        localeDetails.setLanguage(locale.getLanguage());
        
        final I18nLocale updatedLocale = localeRepository.save(localeDetails);
        return ResponseEntity.ok(updatedLocale);
    }

}
