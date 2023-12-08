package com.springprojects.rest.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public MappingJacksonValue filtering() {
		
		SomeBean someBean = new SomeBean("val1", "val2", "val3");
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);
		
		//Dynamic filtering example - return field 1 and 3
		//annotation on bean has to match FilterProvider
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
	
	@GetMapping("/filtering-list")
	public MappingJacksonValue filteringList() {
		
		List<SomeBean> list = Arrays.asList(new SomeBean("val1", "val2", "val3"),
				new SomeBean("val4", "val5", "val6"));
		
		
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
		
		//Dynamic filtering example - return field 1 and 2
		//annotation on bean has to match FilterProvider
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field2");
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		mappingJacksonValue.setFilters(filters);
		
		return mappingJacksonValue;
	}
	
}
