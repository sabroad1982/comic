package com.scottbroadbent.comics.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="deals")
public class Deal {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min=1, max=500, message="Must Be Less Than 500")
	private String Deal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collector_id")
	private Collector collector;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="share_id")
	private Share share;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="saws",
			joinColumns=@JoinColumn(name="deal_id"),
			inverseJoinColumns= @JoinColumn(name="collector_id")
			)
	


	private List<Collector> sawers;
	
	public Deal() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDeal() {
		return Deal;
	}

	public void setDeal(String deal) {
		Deal = deal;
	}
	
	public List<Collector> getSawers() {
		return sawers;
	}

	public void setSawers(List<Collector> sawers) {
		this.sawers = sawers;
	}

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}
	
	
}
