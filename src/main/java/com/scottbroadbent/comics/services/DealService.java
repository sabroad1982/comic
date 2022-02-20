package com.scottbroadbent.comics.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scottbroadbent.comics.models.Collector;
import com.scottbroadbent.comics.models.Deal;

import com.scottbroadbent.comics.repositories.DealRepository;

@Service

public class DealService {
	@Autowired
	private DealRepository dealRepository;
	
	public List<Deal> allDeals(){
		return dealRepository.findAll();
	}
	
	public Deal getOneDeal(Long id) {
		return dealRepository.findById(id).orElse(null);
	}
	public Deal createDeal(Deal deal) {
		System.out.println("Adding a Offer to the Deal Wall");
		return dealRepository.save(deal);
	}
	
	public void deleteDeal(Long id) {
		dealRepository.deleteById(id);
	}
	
	
	public Deal updateDeal(Long id, String name) {
		Deal deal = getOneDeal(id);
		deal.setDeal(name);
		return dealRepository.save(deal);
	}
	
	public void seenDeal(Deal deal, Collector collector) {
		List<Collector> deals = deal.getSawers();
		deals.add(collector);
		dealRepository.save(deal);
	}
	
	public void unSeenDeal(Deal deal, Collector collector){
		List<Collector> deals = deal.getSawers();
		deals.remove(collector);
		dealRepository.save(deal);
	}
	
	public Deal findDealById(Long id) {
		return dealRepository.findById(id).orElse(null);
	}

}
