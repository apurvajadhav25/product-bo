package com.demo.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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

import com.demo.JwelleryApplicationBackOfficeApplication;
import com.demo.model.Filter1;
import com.demo.model.Product;
import com.demo.repository.Filter1Repository;

@RestController
@CrossOrigin
public class Filter1Controller {
	@Autowired
	Filter1Repository filter1Repository;
	
	JwelleryApplicationBackOfficeApplication obj = new JwelleryApplicationBackOfficeApplication();
	Map map = obj.getTransValue();
	
	@GetMapping("/filter1")
	public  Iterable<Filter1> getAllFilter1(){
		return  filter1Repository.findAll();
	}
	
	@GetMapping("/enableFilter1")
	public  Iterable<Filter1> getEnableFilter1(@RequestParam(name="language", required=false) String language){
		Map m1 = (Map) map.get(language);
		Collection<Filter1> filter1 = filter1Repository.findFilters();
		for(Filter1 filter: filter1) {
			String nameValue = (String) m1.get(filter.getName());
			filter.setName(nameValue);
		}
		return filter1;
	}
	
	@PostMapping("/filter1")
	public Filter1 createFilter1(@RequestBody Filter1 filter1) {
		  
		/*
		 * Filter1 savedFilter = null; for(Filter1 filter: filter1List) { savedFilter =
		 * filter1Repository.save(filter); } return savedFilter;
		 */
		return filter1Repository.save(filter1);
	    }
	
	@DeleteMapping("/filter1/{id}")
	public String deleteFilter1(@PathVariable(value = "id") Integer id) {
	     Filter1 filter1 = filter1Repository.findById(id).get();
	     filter1Repository.delete(filter1);
	     return "Product with id  "+id+" deleted successfully";
	    }
	
	@GetMapping("/filter1/{id}")
	public Filter1 getFilter1ById(@PathVariable(value = "id") Integer filter1Id) {
		Filter1 filter1 = filter1Repository.findById(filter1Id).get();
		return filter1;	
	}
	
	@PutMapping("/filter1/{id}")
    public ResponseEntity<Filter1> updateFilter1(@PathVariable(value = "id") Integer filter1Id,
                                                 @RequestBody Filter1 filter1Details) {
        Filter1 filter1 = filter1Repository.findById(filter1Id).get();

        filter1.setName(filter1Details.getName());
        filter1.setIsEnable(filter1Details.getIsEnable());
        
        final Filter1 updatedFilter1 = filter1Repository.save(filter1);
        return ResponseEntity.ok(updatedFilter1);
    }
}
