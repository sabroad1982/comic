package com.scottbroadbent.comics.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scottbroadbent.comics.models.Collector;
import com.scottbroadbent.comics.models.Deal;
import com.scottbroadbent.comics.models.Share;
import com.scottbroadbent.comics.repositories.ShareRepository;



@Service

public class ShareService {
	
	@Autowired
	private ShareRepository shareRepository;
	
	public List<Share> allShares(){
		return shareRepository.findAll();
	}
	
	public Share getOneShare(Long id) {
		return shareRepository.findById(id).orElse(null);
	}
	
	public Share findShareById(Long id) {
		return shareRepository.findById(id).orElse(null);
	}
	
	public Share createShare(Share share) {
		System.out.println("Adding a Share to the Comic Wall");
		return shareRepository.save(share);
	}
	
	public void deleteShare(Long id) {
		shareRepository.deleteById(id);
	}
	public Share updateShare(Long id, String name) {
		Share share = getOneShare(id);
		share.setShare(name);
		return shareRepository.save(share);
	}
	
	public void seenShare(Share share, Collector collector) {
		List<Collector> seens = share.getSeens();
		seens.add(collector);
		shareRepository.save(share);
	}
	
	public void unSeenShare(Share share, Collector collector){
		List<Collector> seens = share.getSeens();
		seens.remove(collector);
		shareRepository.save(share);
	}
	
	
	

}
