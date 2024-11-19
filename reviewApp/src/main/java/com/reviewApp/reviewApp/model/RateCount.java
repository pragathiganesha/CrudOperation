package com.reviewApp.reviewApp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RateCount {
  
	public double rate;
    public int count;
	public RateCount(double rate, int count) {
		super();
		this.rate = rate;
		this.count = count;
	}
    
    
}
