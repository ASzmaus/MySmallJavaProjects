package zad1;

import java.time.LocalDate;

public class Offer {
	private String country;
	private LocalDate dateFrom;
	private LocalDate dateTo;
	private String place;
	private String price;
	private String currency;

	
	
	public Offer(String country, LocalDate dateFrom, LocalDate dateTo, String place, String price,String currency) {
		this.country = country;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.place = place;
		this.price = price;
		this.currency = currency;
	}
	public Offer() {
		// TODO Auto-generated constructor stub
	}

	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public LocalDate getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(LocalDate dateFrom) {
		this.dateFrom = dateFrom;
	}
	public LocalDate getDateTo() {
		return dateTo;
	}
	public void setDateTo(LocalDate dateTo) {
		this.dateTo = dateTo;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	
	@Override
	public String toString() {

		return " " + this.getCountry()+ " " + this.getDateFrom() +" "  + this.getDateTo() + " "+ this.getPlace() + " " + this.getPrice()+ " " + this.getCurrency();	
	}
}
