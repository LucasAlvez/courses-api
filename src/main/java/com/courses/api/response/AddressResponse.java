package com.courses.api.response;

import com.courses.api.entity.AddressEntity;

public class AddressResponse {
	
	private String address;
	
	private String addressNumber;
	
	private String complement;
	
	private String neighborhood;
	
	private String city;
	
	private String state;
	
	private String country;
	
	private String cep;
	
	public static AddressResponse build(AddressEntity address) {
		AddressResponse response = new AddressResponse();
		if(address != null) {
			response.setAddress(address.getAddress());
			response.setAddressNumber(address.getAddressNumber());
			response.setCep(address.getCep());
			response.setCity(address.getCity());
			response.setComplement(address.getComplement());
			response.setCountry(address.getCountry());
			response.setNeighborhood(address.getNeighborhood());
			response.setState(address.getState());
		}
		return response;
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