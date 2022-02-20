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
@Table(name="shares")
public class Share {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	
	@Size(min=5,max=1000, message="*Sell/Buy/Trade Comments should be more than 10 characters long and less than 999 characters*")
	private String Share;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collector_id")
	private Collector collector;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="seens",
			joinColumns=@JoinColumn(name="share_id"),
			inverseJoinColumns= @JoinColumn(name="collector_id")		
			)
	
	
	
	private List<Collector> seens;
	
	
	public Share() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getShare() {
		return Share;
	}

	public void setShare(String share) {
		Share = share;
	}

	public Collector getCollector() {
		return collector;
	}

	public void setCollector(Collector collector) {
		this.collector = collector;
	}

	public List<Collector> getSeens() {
		return seens;
	}

	public void setSeens(List<Collector> seens) {
		this.seens = seens;
	}	

	
	
	
}
