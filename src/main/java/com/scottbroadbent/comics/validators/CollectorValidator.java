package com.scottbroadbent.comics.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.scottbroadbent.comics.models.Collector;
import com.scottbroadbent.comics.repositories.CollectorRepository;



@Component

public class CollectorValidator implements Validator{
	
	@Autowired
	private CollectorRepository collectorRepository;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Collector.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		Collector collector= (Collector) target;
		
		if(!collector.getPasswordConfirmation().equals(collector.getPassword())) {
			errors.rejectValue("passwordConfirmation", "Match","Password doesn't Match");
		}
		if(this.collectorRepository.findByEmail(collector.getEmail()) !=null) {
			errors.rejectValue("email", "Unique","Email address already exist");
		}
	}
	

}