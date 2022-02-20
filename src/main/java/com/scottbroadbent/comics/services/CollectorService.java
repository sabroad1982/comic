package com.scottbroadbent.comics.services;



import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scottbroadbent.comics.models.Collector;
import com.scottbroadbent.comics.repositories.CollectorRepository;


@Service

public class CollectorService {
	
	@Autowired
	private CollectorRepository collectorRepository;
	
    public Collector registerCollector(Collector collector) {
        String hashed = BCrypt.hashpw(collector.getPassword(), BCrypt.gensalt());
        collector.setPassword(hashed);
        return collectorRepository.save(collector);
    }
    
    
    // find user by email
    public Collector findByEmail(String email) {
        return collectorRepository.findByEmail(email);
    }
    
    
    // find user by id
    public Collector findCollectorById(Long id) {
    	return collectorRepository.findById(id).orElse(null);

    }
    
    
    // authenticate user
    public boolean authenticateCollector(String email, String password) {
    	Collector collector = collectorRepository.findByEmail(email);
        if(collector == null) {
            return false;
        } else {
            if(BCrypt.checkpw(password, collector.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
	public Collector getOneCollector(Long id) {
		return collectorRepository.findById(id).orElse(null);
	}
    
	
    //update user
	public Collector updateCollector(Long id, String firstName, String lastName, String email, String state, String fav, String password, String passwordConfirmation) {
		Collector collector = getOneCollector(id);
		collector.setFirstName(firstName);
		collector.setLastName(lastName);
		collector.setEmail(email);
		collector.setState(state);
		collector.setFav(fav);
		collector.setPassword(password);
		collector.setPasswordConfirmation(passwordConfirmation);
		return collectorRepository.save(collector);
	}
}
