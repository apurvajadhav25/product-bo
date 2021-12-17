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
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Filter1;
import com.demo.model.Filter2;
import com.demo.repository.Filter2Repository;

@RestController
@CrossOrigin
public class Filter2Controller {
	@Autowired
	Filter2Repository filter2Repository;
	
	@GetMapping("/filter2")
	public  Iterable<Filter2> getAllFilter2(){
		return  filter2Repository.findAll();
	}
	
	@GetMapping("/enableFilter2")
	public  Iterable<Filter2> getEnableFilter2(){
		return  filter2Repository.findFilters();
	}
	
	@PostMapping("/filter2")
	public Filter2 createFilter2(@RequestBody Filter2 filter2) {
		  
		/*
		 * Filter2 savedFilter = null; for(Filter2 filter: filter2List) { savedFilter =
		 * filter2Repository.save(filter); } return savedFilter;
		 */
		
		return filter2Repository.save(filter2);
	    }
	
	@DeleteMapping("/filter2/{id}")
	public String deleteFilter2(@PathVariable(value = "id") Integer id) {
	     Filter2 filter2 = filter2Repository.findById(id).get();
	     filter2Repository.delete(filter2);
	     return "Product with id  "+id+" deleted successfully";
	    }
	
	@GetMapping("/filter2/{id}")
	public Filter2 getFilter2ById(@PathVariable(value = "id") Integer filter2Id) {
		Filter2 filter2 = filter2Repository.findById(filter2Id).get();
		return filter2;	
	}
	
	@PutMapping("/filter2/{id}")
    public ResponseEntity<Filter2> updateFilter2(@PathVariable(value = "id") Integer filter2Id,
                                                 @RequestBody Filter2 filter2Details) {
        Filter2 filter2 = filter2Repository.findById(filter2Id).get();

        filter2.setName(filter2Details.getName());
        filter2.setIsEnable(filter2Details.getIsEnable());
        
        final Filter2 updatedFilter2 = filter2Repository.save(filter2);
        return ResponseEntity.ok(updatedFilter2);
    }
}
