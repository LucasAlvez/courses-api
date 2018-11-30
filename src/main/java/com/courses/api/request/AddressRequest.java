package com.courses.api.request;

import com.courses.api.entity.AddressEntity;

public class AddressRequest {

	private String address;

	private String addressNumber;

	private String complement;

	private String neighborhood;

	private String city;

	private String state;

	private String country;

	private String cep;

	public static AddressEntity build(AddressRequest address) {
		AddressEntity entity = new AddressEntity();
		entity.setAddress(address.getAddress());
		entity.setAddressNumber(address.getAddressNumber());
		entity.setCep(address.getCep());
		entity.setCity(address.getCity());
		entity.setComplement(address.getComplement());
		entity.setCountry(address.getCountry());
		entity.setNeighborhood(address.getNeighborhood());
		entity.setState(address.getState());
		return entity;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
