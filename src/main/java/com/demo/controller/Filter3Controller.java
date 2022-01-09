package com.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.model.Filter1;
import com.demo.model.Filter3;
import com.demo.repository.Filter3Repository;

@RestController
public class Filter3Controller {
	@Autowired
	Filter3Repository filter3Repository;
	
	@GetMapping("/filter3")
	public  Iterable<Filter3> getAllFilter3(){
		return  filter3Repository.findAll();
	}
	
	@GetMapping("/enableFilter3")
	public  Iterable<Filter3> getEnableFilter3(){
		return  filter3Repository.findFilters();
	}
	
	@PostMapping("/filter3")
	public Filter3 createFilter3(@RequestBody Filter3 filter3) {
		  
		/*
		 * Filter3 savedFilter = null; for(Filter3 filter: filter3List) { savedFilter =
		 * filter3Repository.save(filter); }
		 */
		    return filter3Repository.save(filter3);
	    }
	
	@DeleteMapping("/filter3/{id}")
	public String deleteFilter3(@PathVariable(value = "id") Integer id) {
	     Filter3 filter3 = filter3Repository.findById(id).get();
	     filter3Repository.delete(filter3);
	     return "Product with id  "+id+" deleted successfully";
	    }
	
	@GetMapping("/filter3/{id}")
	public Filter3 getFilter3ById(@PathVariable(value = "id") Integer filter3Id) {
		Filter3 filter3 = filter3Repository.findById(filter3Id).get();
		return filter3;	
	}
	
	@PutMapping("/filter3/{id}")
    public ResponseEntity<Filter3> updateFilter3(@PathVariable(value = "id") Integer filter3Id,
                                                 @RequestBody Filter3 filter3Details) {
        Filter3 filter3 = filter3Repository.findById(filter3Id).get();

        filter3.setName(filter3Details.getName());
        filter3.setIsEnable(filter3Details.getIsEnable());
        
        final Filter3 updatedFilter3 = filter3Repository.save(filter3);
        return ResponseEntity.ok(updatedFilter3);
    }
}
