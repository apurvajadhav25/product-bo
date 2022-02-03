package com.demo.controller;

import java.util.List;

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

import com.demo.model.Configuration;
import com.demo.model.Translation;
import com.demo.repository.ConfigurationRepository;

@RestController
@CrossOrigin
public class ConfigurationController {
	
	@Autowired
	ConfigurationRepository configurationRepository;
	
	@GetMapping("/configuration")
	public  Iterable<Configuration> getAllFilter1(){
		return  configurationRepository.findAll();
	}

	@PostMapping("/configuration")
	public Configuration createConfiguration(@RequestBody Configuration configuration) {
		  
		/*
		 * Filter1 savedFilter = null; for(Filter1 filter: filter1List) { savedFilter =
		 * filter1Repository.save(filter); } return savedFilter;
		 */
		return configurationRepository.save(configuration);
	    }
	
	@GetMapping("/configurationByKey")
	public Iterable<Configuration> getAllProducts(@RequestParam(name="key") String key, @RequestParam(name="language") String language){
		System.out.println(language);
		return configurationRepository.findByKey(key, language);
		
	}
	
	@DeleteMapping("/configuration/{id}")
	public String deleteConfiguration(@PathVariable(value = "id") Integer id) {
	     Configuration configuration = configurationRepository.findById(id).get();
	     configurationRepository.delete(configuration);
	     return "Product with id  "+id+" deleted successfully";
	    }
	
	@GetMapping("/configuration/{id}")
	public Configuration getConfigurationById(@PathVariable(value = "id") Integer id) {
		Configuration configuration = configurationRepository.findById(id).get();
		return configuration;	
	}
	
	@PutMapping("/configuration/{id}")
    public ResponseEntity<Configuration> updateConfiguration(@PathVariable(value = "id") Integer id,
                                                 @RequestBody Configuration configurationDetails) {
        Configuration configuration = configurationRepository.findById(id).get();

        configuration.setKey(configurationDetails.getKey());
        configuration.setLanguage(configurationDetails.getLanguage());
        configuration.setValue(configurationDetails.getValue());

        
        final Configuration updatedConfiguration = configurationRepository.save(configuration);
        return ResponseEntity.ok(updatedConfiguration);
    }

}
