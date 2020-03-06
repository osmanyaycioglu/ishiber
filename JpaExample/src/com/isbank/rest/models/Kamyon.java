package com.isbank.rest.models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("kamyon")
public class Kamyon extends Arac{
	private int yukKapasitesi;

	public int getYukKapasitesi() {
		return yukKapasitesi;
	}

	public void setYukKapasitesi(int yukKapasitesi) {
		this.yukKapasitesi = yukKapasitesi;
	}
	
}
