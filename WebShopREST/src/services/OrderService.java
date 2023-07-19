package services;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import enums.OrderStatus;
import model.CarRentalFacility;
import model.Order;
import model.User;
import model.Vehicle;
import storage.FacilityStorage;
import storage.OrderStorage;
import storage.VehicleStorage;

public class OrderService {
	private OrderStorage orders = new OrderStorage();
	private VehicleService vehicleService = new VehicleService();
	private VehicleStorage vehicles = new VehicleStorage();
	
	public Collection<Order> getOrders() {
		OrderStorage ordersX = new OrderStorage();
		return ordersX.getAll();
	}
	public Collection<Vehicle> getVehicles() {
		VehicleStorage vehiclesX = new VehicleStorage();
		return vehiclesX.getAll();
	}
	
	public ArrayList<Order> getOrdersArrayList() {
		OrderStorage ordersX = new OrderStorage();
		return ordersX.getArray();
	}
	
	public Order getOrderById(String id) {
		OrderStorage ordersX = new OrderStorage();
		return ordersX.getById(id);
	}
	

	
	
/*	public ArrayList<Order> getOrdersManagerList() {
		OrderStorage ordersX = new OrderStorage();
		ArrayList<Order> orders = ordersX.getArray();
		ArrayList<Vehicle> vehicles = vehicleService.getAllContent("11");
		
		
		
		
		ArrayList<Order> filteredOrders= new ArrayList<Order>();
		
		
		
		return orders;
	} */
	
	
	
	public Order newOrder(Order order) {
		OrderStorage ordersX = new OrderStorage();
		int id = 0;
		System.out.println("NOVI ORDER");
		for(Order ord: ordersX.getArray()) {
			System.out.println("NOVI ORDER2");
			System.out.println(ord.getUsername());
			if(Integer.parseInt(ord.getId()) > id) {
				id++;
			}
		}
		System.out.println("NOVI ORDER3");
		order.setId(Integer.toString(++id));
		
		System.out.println(id);
		System.out.println("DODAVANJE");
		return orders.addOrder(order);
	}
	
	
	public void addStatusToOrder(String orderId, String status) {
		OrderStorage ordersX = new OrderStorage();
		System.out.println(orderId);
		System.out.println(status);
		Order order = ordersX.getById(orderId);
		System.out.println(orderId);
		System.out.println(status);
		OrderStatus stat = OrderStatus.valueOf(status);
		order.setStatus(stat);
		ordersX.editOrder(order);
		System.out.println(orderId);
		System.out.println(status);
		
	}
	
	
	
	
	public void addContentToOrder(String vehicleId, String orderId) {
		Order order = orders.getById(orderId);
		
		if(order.getVehicles().equals("nothing")) {
			order.setVehicles(orderId);
		} else {
			StringBuilder str = new StringBuilder();
			str.append(order.getVehicles());
			str.append(",");
			str.append(vehicleId);
			
			order.setVehicles(str.toString());
		}
		
		orders.editOrder(order);
		
	}
	

}
