package model;

import java.time.LocalDate;
import java.util.ArrayList;

enum Gender {
	MALE("Male"),
	FEMALE("Female");
	  
	private final String gender;
	
	private Gender(String gender) {
		this.gender = gender;
	}
	
	public String getGender() {
		return gender;
	}	  
}
enum Role {
	ADMIN("Admin"),
	MANAGER("Manager"),
	CUSTOMER("Customer");
	
	private final String role;
	
	private Role(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}
}

public class User {
	public boolean deleted;
	public boolean sus;
	private String username;
	private String password;
	private String name;
	private String surname;
	private Gender gender;
	private LocalDate dateOfBirth;
	private Role role;
		
//
	//All rented cars object customer
	private ArrayList<Order> orderedCars;
	
	//Basket object for customer
	private Basket basket;
	
	//Rent a car object for manager
	private String facility;
	
	//points if customer
	private int points;
	
	//type of customer
	private String customerType;
//
	
	
	public User(String username, String password, String name, String surname, String gender, LocalDate dateOfBirth, String role) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		
		switch(gender) {
		case "MALE":
			this.gender = Gender.MALE;
			break;
		case "FEMALE":
			this.gender = Gender.FEMALE;
			break;
		}
		
		this.dateOfBirth = dateOfBirth;
		
		switch(role) {
		case "ADMIN":
			this.role = Role.ADMIN;
			break;
		case "MANAGER":
			this.role = Role.MANAGER;
			break;
		case "CUSTOMER":
			this.role = Role.CUSTOMER;
			break;
		}
	}
	
	public User(String username, String password, String name, String surname, String gender, LocalDate dateOfBirth, 
			String role, String customerType, String deleted, String sus, int points) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		
		this.points=points;
		switch(gender) {
		case "MALE":
			this.gender = Gender.MALE;
			break;
		case "FEMALE":
			this.gender = Gender.FEMALE;
			break;
		}
		
		this.dateOfBirth = dateOfBirth;
		
		switch(role) {
		case "ADMIN":
			this.role = Role.ADMIN;
			break;
		case "MANAGER":
			this.role = Role.MANAGER;
			break;
		case "CUSTOMER":
			this.role = Role.CUSTOMER;
			break;
		}
		
		this.customerType = customerType;
		try {
		    this.deleted = Boolean.parseBoolean(deleted);
		    this.sus = Boolean.parseBoolean(sus);
		} catch (NumberFormatException e) {
		    this.deleted = false;
		}
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public void setGender(String gender) {
		switch(gender) {
		case "MALE":
			this.gender = Gender.MALE;
			break;
		case "FEMALE":
			this.gender = Gender.FEMALE;
			break;
		}
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	public boolean isSus() {
		return sus;
	}

	public void setSus(boolean sus) {
		this.sus = sus;
	}
	
	public String getRoleStr() {
		switch(this.role) {
		case ADMIN:
			return "ADMIN";
		case MANAGER:
			return "MANAGER";
		case CUSTOMER:
			return "CUSTOMER";

		default:
			return "";		}
	} 
	
	public boolean isManager() {
		if(this.role==Role.MANAGER) {
			return true;
		}
		else return false;
	}

	public String getGenderStr() {
		switch(this.gender) {
		case MALE:
			return "MALE";
		case FEMALE:
			return "FEMALE";
		default:
			return "";
		}
		
	}

	public ArrayList<Order> getOrderedCars() {
		return orderedCars;
	}

	public void setOrderedCars(ArrayList<Order> orderedCars) {
		this.orderedCars = orderedCars;
	}

	public Basket getBasket() {
		return basket;
	}

	public void setBasket(Basket basket) {
		this.basket = basket;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String rentalFacility) {
		this.facility = rentalFacility;
	}
}
