package main;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFiles;

import java.io.File;

import controller.BasketController;
import controller.CommentsController;
import controller.FacilityController;
import controller.OrderController;
import controller.UserController;
import controller.VehicleController;

public class RentalAppMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		port(8080);
		staticFiles.externalLocation(new File("./static").getCanonicalPath());
		
		UserController.getUsers();
		UserController.Register();
		UserController.Login();
		UserController.EditUserInfo();
		UserController.getUserData();
		UserController.getAllManagers();
		UserController.getManagerFacilityId();
		UserController.searchUsers();
		UserController.getAllUserTypes();
		
		BasketController.getBasket();
		BasketController.newBasket();
		VehicleController.addContentBasket();
		VehicleController.removeContentBasket();
		VehicleController.removeAllContentBasket();
		
		
		FacilityController.getAll();
		FacilityController.searchFacilities();
		FacilityController.getFacilityById();
		FacilityController.newFacility();
		FacilityController.newBlock();
		VehicleController.searchVehicles();
		
		VehicleController.getAllFacilityTypes();
		VehicleController.addContent();
		VehicleController.getContent();
		VehicleController.getContentMAX();
		VehicleController.editContent();
		VehicleController.getVehicleById();
		VehicleController.getAllFuelTypes();
		VehicleController.getAll();
		
		
		OrderController.getAll();
		//OrderController.getAllManager();
		OrderController.getOrderById();
		OrderController.newOrder();
		
		//order states
		OrderController.approvedOrder();
		OrderController.canceledOrder();
		OrderController.rejectedOrder();
		OrderController.retrievedOrder();
		OrderController.returnedOrder();
		
		CommentsController.getCommentsByFacility();
		CommentsController.addComment();
		CommentsController.changeCommentStatus();
		System.out.println("STARTOVANO");
	}

}