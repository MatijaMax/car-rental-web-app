package controller;
import static spark.Spark.get;
import static spark.Spark.post;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.google.gson.Gson;

import dto.NewFacilityDTO;
import dto.OrderChangeDTO;
import dto.OrderDTO;
import enums.OrderStatus;
import model.CarRentalFacility;
import model.Order;
import model.User;
import services.FacilityService;
import services.OrderService;
import services.UserService;
import services.VehicleService;
import util.Authorization;
import util.GsonSerializer;

public class OrderController {
	private static Gson g = GsonSerializer.makeGson();
	public static OrderService orderService = new OrderService();
	public static UserService userService = new UserService();
	public static VehicleService vehicleService = new VehicleService();
	

	public static void getAll() {
		get("rest/orders/", (req, res) -> {
			res.type("application/json");
			
			//System.out.println("KONACNO JE PRORADILO AJMOOO");
			return g.toJson(orderService.getOrdersArrayList());
		});
	}
	
	
/*	public static void getAllManager() {
		get("rest/orders/manager", (req, res) -> {
			res.type("application/json");
			
			//System.out.println("KONACNO JE PRORADILO AJMOOO");
			return g.toJson(orderService.getOrdersManagerList());
		});
	} */
	
	
	public static void getOrderById() {
		get("rest/order/getById/", (req, res) -> {
			res.type("application/json");
			return g.toJson(orderService.getOrderById(req.queryParams("id")));
		});
	}
	

	
	public static void newOrder() {
		post("rest/order/new/", (req, res) -> {
			res.type("application/json");
			System.out.println("RADI ORDER");
		//	String jwt = req.headers("Authorization");
		//	if(!Authorization.isLoggedIn(UserController.key, jwt))
		//		return "null";
			
			OrderDTO processDto = g.fromJson(req.body(), OrderDTO.class);
			System.out.println("RADI ORDER");
			System.out.println(processDto.getName());
			
			
			LocalDate formated = LocalDate.now();
			

			LocalTime t = LocalTime.now();
			OrderStatus stat = OrderStatus.valueOf(processDto.getStatus());
			System.out.println("RADI ORDER TIME-DATE");
			Order order= new Order(processDto.getVehicles(), processDto.getFacility(), formated, t, processDto.getLeaseDuration(),processDto.getPrice(),processDto.getUsername(),processDto.getName(),processDto.getSurname(),stat);
			System.out.println("RADI ORDER CONSTRUCTOR");
			order = orderService.newOrder(order);
			vehicleService.goAvailable(processDto.getVehicles());
			System.out.println("RADI ORDER CEO");
			System.out.println(processDto.getName());
			System.out.println(processDto.getSurname());
			return order;
		});
	}
		
		  
		
		public static void approvedOrder() {
			post("rest/order/approved/", (req, res) -> {
				res.type("application/json");
				System.out.println("RADI ORDER");
			//	String jwt = req.headers("Authorization");
			//	if(!Authorization.isLoggedIn(UserController.key, jwt))
			//		return "null";
				
				OrderChangeDTO processDto = g.fromJson(req.body(), OrderChangeDTO.class);
				System.out.println("RADI ORDER");
				System.out.println(processDto.getName());
				
				
				LocalDate formated = LocalDate.now();
				

				LocalTime t = LocalTime.now();
				OrderStatus stat = OrderStatus.valueOf(processDto.getStatus());
				System.out.println("RADI ORDER TIME-DATE");
				Order order= new Order(processDto.getVehicles(), processDto.getFacility(), formated, t, processDto.getLeaseDuration(),processDto.getPrice(),processDto.getUsername(),processDto.getName(),processDto.getSurname(),OrderStatus.valueOf("APPROVED"));
				System.out.println("RADI ORDER CONSTRUCTOR");
				orderService.addStatusToOrder(processDto.getId(), "APPROVED");
				
				System.out.println("RADI ORDER CEO");
				System.out.println(processDto.getName());
				System.out.println(processDto.getSurname());
				return order;
			});
		}
			
			public static void retrievedOrder() {
				post("rest/order/retrieved/", (req, res) -> {
					res.type("application/json");
					System.out.println("RADI ORDER");
				//	String jwt = req.headers("Authorization");
				//	if(!Authorization.isLoggedIn(UserController.key, jwt))
				//		return "null";
					
					OrderChangeDTO processDto = g.fromJson(req.body(), OrderChangeDTO.class);
					System.out.println("RADI ORDER");
					System.out.println(processDto.getName());
					
					
					LocalDate formated = LocalDate.now();
					

					LocalTime t = LocalTime.now();
					OrderStatus stat = OrderStatus.valueOf(processDto.getStatus());
					System.out.println("RADI ORDER TIME-DATE");
					Order order= new Order(processDto.getVehicles(), processDto.getFacility(), formated, t, processDto.getLeaseDuration(),processDto.getPrice(),processDto.getUsername(),processDto.getName(),processDto.getSurname(),OrderStatus.valueOf("RETRIEVED"));
					System.out.println("RADI ORDER CONSTRUCTOR");
					
					orderService.addStatusToOrder(processDto.getId(), "RETRIEVED");
					vehicleService.goRented(order.getVehicles());
					System.out.println("RADI ORDER CEO");
					System.out.println(processDto.getName());
					System.out.println(processDto.getSurname());
					return order;
				});
			}
				
				public static void returnedOrder() {
					post("rest/order/returned/", (req, res) -> {
						res.type("application/json");
						System.out.println("RADI ORDER");
					//	String jwt = req.headers("Authorization");
					//	if(!Authorization.isLoggedIn(UserController.key, jwt))
					//		return "null";
						
						OrderChangeDTO processDto = g.fromJson(req.body(), OrderChangeDTO.class);
						System.out.println("RADI ORDER");
						System.out.println(processDto.getName());
						
						
						LocalDate formated = LocalDate.now();
						

						LocalTime t = LocalTime.now();
						OrderStatus stat = OrderStatus.valueOf(processDto.getStatus());
						System.out.println("RADI ORDER TIME-DATE");
						Order order= new Order(processDto.getVehicles(), processDto.getFacility(), formated, t, processDto.getLeaseDuration(),processDto.getPrice(),processDto.getUsername(),processDto.getName(),processDto.getSurname(),OrderStatus.valueOf("RETURNED"));
						System.out.println("RADI ORDER CONSTRUCTOR");
						orderService.addStatusToOrder(processDto.getId(), "RETURNED");
						vehicleService.goAvailable(processDto.getVehicles());
						System.out.println("RADI ORDER CEO");
						System.out.println(processDto.getName());
						System.out.println(processDto.getSurname());
						return order;
					});
				}
					
					public static void rejectedOrder() {
						post("rest/order/rejected/", (req, res) -> {
							res.type("application/json");
							System.out.println("RADI ORDER");
						//	String jwt = req.headers("Authorization");
						//	if(!Authorization.isLoggedIn(UserController.key, jwt))
						//		return "null";
							
							OrderChangeDTO processDto = g.fromJson(req.body(), OrderChangeDTO.class);
							System.out.println("RADI ORDER");
							System.out.println(processDto.getName());
							
							
							LocalDate formated = LocalDate.now();
							

							LocalTime t = LocalTime.now();
							OrderStatus stat = OrderStatus.valueOf(processDto.getStatus());
							System.out.println("RADI ORDER TIME-DATE");
							Order order= new Order(processDto.getVehicles(), processDto.getFacility(), formated, t, processDto.getLeaseDuration(),processDto.getPrice(),processDto.getUsername(),processDto.getName(),processDto.getSurname(),OrderStatus.valueOf("REJECTED"));
							System.out.println("RADI ORDER CONSTRUCTOR");
							orderService.addStatusToOrder(processDto.getId(), "REJECTED");
							
							System.out.println("RADI ORDER CEO");
							System.out.println(processDto.getName());
							System.out.println(processDto.getSurname());
							return order;
						});
					}
						
						public static void canceledOrder() {
							post("rest/order/canceled/", (req, res) -> {
								res.type("application/json");
								System.out.println("RADI ORDER");
							//	String jwt = req.headers("Authorization");
							//	if(!Authorization.isLoggedIn(UserController.key, jwt))
							//		return "null";
								
								OrderChangeDTO processDto = g.fromJson(req.body(), OrderChangeDTO.class);
								System.out.println("RADI ORDER");
								System.out.println(processDto.getName());
								
								
								LocalDate formated = LocalDate.now();
								

								LocalTime t = LocalTime.now();
								OrderStatus stat = OrderStatus.valueOf(processDto.getStatus());
								System.out.println("RADI ORDER TIME-DATE");
								Order order= new Order(processDto.getVehicles(), processDto.getFacility(), formated, t, processDto.getLeaseDuration(),processDto.getPrice(),processDto.getUsername(),processDto.getName(),processDto.getSurname(),OrderStatus.valueOf("CANCELED"));
								System.out.println("RADI ORDER CONSTRUCTOR");
								orderService.addStatusToOrder(processDto.getId(), "CANCELED");
								
								
								userService.susUser(processDto.getUsername());
								
								
								System.out.println("RADI ORDER CEO");
								System.out.println(processDto.getName());
								System.out.println(processDto.getSurname());
								return order;
							});
						}


	}

