package dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import enums.OrderStatus;
import model.CarRentalFacility;
import model.User;
import model.Vehicle;

public class OrderDTO {
	private String vehicles;
	private String facility;
	private String date;
	private String time;
	private int leaseDuration;
	private double price;
	private String username;
	private String name;
	private String surname;
	private String status;
	public OrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getVehicles() {
		return vehicles;
	}
	public void setVehicles(String vehicles) {
		this.vehicles = vehicles;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getLeaseDuration() {
		return leaseDuration;
	}
	public void setLeaseDuration(int leaseDuration) {
		this.leaseDuration = leaseDuration;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OrderDTO(String vehicles, String facility, String date, String time, int leaseDuration,
			double price, String username, String name, String surname, String status) {
		super();
		this.vehicles = vehicles;
		this.facility = facility;
		this.date = date;
		this.time = time;
		this.leaseDuration = leaseDuration;
		this.price = price;
		this.username = username;
		this.name = name;
		this.surname = surname;
		this.status = status;
	}
	

	
}
