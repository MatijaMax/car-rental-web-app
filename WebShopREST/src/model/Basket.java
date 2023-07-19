package model;

import java.util.List;

public class Basket {
	
	private String username;
	private double price;
	private String content;
	public Basket(String username, double price, String content) {
		super();
		this.username = username;
		this.price = price;
		this.content = content;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

}
