package controller;
import static spark.Spark.get;
import static spark.Spark.post;

import java.util.ArrayList;

import com.google.gson.Gson;

import dto.BasketDTO;
import dto.NewFacilityDTO;
import model.Basket;
import model.CarRentalFacility;
import services.BasketService;
import services.FacilityService;
import util.Authorization;
import util.GsonSerializer;


public class BasketController {
	private static Gson g = GsonSerializer.makeGson();
	public static BasketService basketService = new BasketService();
	
	
	public static void getBasket() {
		get("rest/basket/", (req, res) -> {
			res.type("application/json");
			
			//System.out.println("KONACNO JE PRORADILO AJMOOO");
			return g.toJson(basketService.getBasket());
		});
	}
	
	public static void newBasket() {
		post("rest/basket/new/", (req, res) -> {
			res.type("application/json");
			
			String jwt = req.headers("Authorization");
			//if(!Authorization.isLoggedIn(UserController.key, jwt))
				//return "null";
			System.out.println("KONACNO JE PRORADILO AJMOOO");
			BasketDTO basketDto = g.fromJson(req.body(), BasketDTO.class);
			
			Basket basket = new Basket(basketDto.getUsername(),basketDto.getPrice(), basketDto.getContent());
			System.out.println(basket.getContent());
			System.out.println(basket.getUsername());
			System.out.println(basket.getPrice());
			System.out.println("KONACNO JE PRORADILO AJMOOO");
			basket = basketService.newBasket(basket);
			
			return basket;
		});
	}
	
	
	
	
	
	
	
	
	
	
}
