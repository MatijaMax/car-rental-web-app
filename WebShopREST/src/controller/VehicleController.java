package controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.google.gson.Gson;

import dto.ContentDTO;
import model.CarRentalFacility;
import model.Vehicle;
import services.VehicleService;
import util.Authorization;
import util.GsonSerializer;

public class VehicleController {
	private static Gson g = GsonSerializer.makeGson();

	private static VehicleService vehicleService = new VehicleService();
	
	public static void addContent() {
		post("rest/facilities/newcontent/", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			
			/*String jwt = req.headers("Authorization");
			if(!Authorization.isLoggedIn(UserController.key, jwt))
				return "null";*/
			
			ContentDTO newContent = g.fromJson(req.body(), ContentDTO.class);
			
			Vehicle vehicle = new Vehicle(newContent.getBrand(), newContent.getModel(), newContent.getPrice(), newContent.getVehicleType(), newContent.getFacilityId(), newContent.getVehicleKind(), newContent.getFuelType(), 
					newContent.getConsumption(), newContent.getNumberOfDoors(), newContent.getCapacity(), newContent.getDescription(), newContent.getPhotoUrl(), newContent.getStatus(), newContent.getDeleted());
			
			vehicle = vehicleService.addContent(vehicle);
			if(vehicle != null)
				FacilityController.facilityService.addContentToFacility(vehicle.getId(), newContent.getFacilityId());
			
			return g.toJson(vehicle);
		});
	}
	
	public static void addContentBasket() {
		post("rest/basket/newcontent/", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			
			/*String jwt = req.headers("Authorization");
			if(!Authorization.isLoggedIn(UserController.key, jwt))
				return "null";*/
			
			ContentDTO newContent = g.fromJson(req.body(), ContentDTO.class);
			
			Vehicle vehicle = new Vehicle(newContent.getBrand(), newContent.getModel(), newContent.getPrice(), newContent.getVehicleType(), newContent.getFacilityId(), newContent.getVehicleKind(), newContent.getFuelType(), 
					newContent.getConsumption(), newContent.getNumberOfDoors(), newContent.getCapacity(), newContent.getDescription(), newContent.getPhotoUrl(), newContent.getStatus(), newContent.getDeleted());
			System.out.println(vehicle.getId());
			System.out.println(vehicle.getId());
			System.out.println(vehicle.getId());
			System.out.println();
			
			if(vehicle != null)
				
				BasketController.basketService.addContentToBasket(newContent.getId());
			return g.toJson(vehicle);
		});
	}
	
	public static void removeContentBasket() {
		post("rest/basket/removecontent/", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			
			/*String jwt = req.headers("Authorization");
			if(!Authorization.isLoggedIn(UserController.key, jwt))
				return "null";*/
			
			ContentDTO newContent = g.fromJson(req.body(), ContentDTO.class);
			
			Vehicle vehicle = new Vehicle(newContent.getBrand(), newContent.getModel(), newContent.getPrice(), newContent.getVehicleType(), newContent.getFacilityId(), newContent.getVehicleKind(), newContent.getFuelType(), 
					newContent.getConsumption(), newContent.getNumberOfDoors(), newContent.getCapacity(), newContent.getDescription(), newContent.getPhotoUrl(), newContent.getStatus(), newContent.getDeleted());
			
			vehicle = vehicleService.addContent(vehicle);
			if(vehicle != null)
				
				BasketController.basketService.removeContentFromBasket(newContent.getId());
			return g.toJson(vehicle);
		});
	}
	public static void removeAllContentBasket() {
		post("rest/basket/removeAllContent/", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			
			/*String jwt = req.headers("Authorization");
			if(!Authorization.isLoggedIn(UserController.key, jwt))
				return "null";*/
			
			ContentDTO newContent = g.fromJson(req.body(), ContentDTO.class);
			
			Vehicle vehicle = new Vehicle(newContent.getBrand(), newContent.getModel(), newContent.getPrice(), newContent.getVehicleType(), newContent.getFacilityId(), newContent.getVehicleKind(), newContent.getFuelType(), 
					newContent.getConsumption(), newContent.getNumberOfDoors(), newContent.getCapacity(), newContent.getDescription(), newContent.getPhotoUrl(), newContent.getStatus(), newContent.getDeleted());
			
			vehicle = vehicleService.addContent(vehicle);
			if(vehicle != null)
				
				BasketController.basketService.removeAllContentFromBasket(newContent.getId());
			return g.toJson(vehicle);
		});
	}
	
	
	public static void getAll() {
		get("rest/vehicles/", (req, res) -> {
			res.type("application/json");
			
			//System.out.println("KONACNO JE PRORADILO AJMOOO");
			return g.toJson(vehicleService.getVehiclesArrayList());
		});
	}
	
	public static void editContent() {
		post("rest/facilities/editcontent/", (req, res) -> {
			res.type("application/json");
			System.out.println(req.body());
			
			String jwt = req.headers("Authorization");
			if(!Authorization.isLoggedIn(UserController.key, jwt))
				return "null";
			
			String vehicleId = req.queryParams("id");
			
			ContentDTO newContent = g.fromJson(req.body(), ContentDTO.class);
			
			Vehicle vehicle = new Vehicle(newContent.getBrand(), newContent.getModel(), newContent.getPrice(), newContent.getVehicleType(), newContent.getFacilityId(), newContent.getVehicleKind(), newContent.getFuelType(), 
					newContent.getConsumption(), newContent.getNumberOfDoors(), newContent.getCapacity(), newContent.getDescription(), newContent.getPhotoUrl(), newContent.getStatus(), newContent.getDeleted());
			vehicle.setId(vehicleId);
			
			vehicle = vehicleService.editContent(vehicle);
			
			return g.toJson(vehicle);
		});
	}
	
	public static void getContent() {
		get("rest/facilities/content/", (req, res) -> {
			res.type("application/json");
			
			String content = req.queryParams("content");
			
			System.out.println(content);
			
			return g.toJson(vehicleService.getAllContent(content));
		});
	}
	
	public static void getContentMAX() {
		get("rest/vehicles/content/", (req, res) -> {
			res.type("application/json");
						
			return g.toJson(vehicleService.getAllContentMAX());
		});
	}
	
	
	
	
	public static void getVehicleById() {
		get("rest/facilities/getTraining/", (req, res) -> {
			res.type("application/json");
			
			String content = req.queryParams("id");
			
			System.out.println(content);
			
			return g.toJson(vehicleService.getVehicleById(content));
		});
	}
	public static void getAllFacilityTypes() {
		get("rest/facilities/types/", (req, res) -> {
			res.type("application/json");
			return g.toJson(vehicleService.getAllTypes());
		});
	}
	
	public static void getAllFuelTypes() {
		get("rest/facilities/ftypes/", (req, res) -> {
			res.type("application/json");
			return g.toJson(vehicleService.getAllFuelTypes());
		});
	}
	
	public static void searchVehicles() {
		get("rest/vehicles/search/", (req, res) -> {
			res.type("application/json");
			String filter = req.queryParams("filter");
			
			ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();
			String[] filters = filter.split(",");
			DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate formatedStart = LocalDate.parse(filters[0], formatDate);
			LocalDate formatedEnd = LocalDate.parse(filters[1], formatDate);
			
			vehicles = vehicleService.getAvailableVehicle(formatedStart, formatedEnd);
			
			return g.toJson(vehicles);
		}); 
	}
	
	
	
	
	
}
