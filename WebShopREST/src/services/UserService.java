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
import storage.UserStorage;
import storage.VehicleStorage;

public class UserService {
	private UserStorage users = new UserStorage();
	private OrderStorage orderStorage = new OrderStorage();
	private FacilityStorage facilities = new FacilityStorage();
	
	public Collection<CarRentalFacility> getFacilities() {
		
		return facilities.getAll();
	}

	public Collection<User> getUsers() {
		UserStorage usersX = new UserStorage();
		return usersX.getAll();
	}
	
	public ArrayList<User> getUsersArrayList() {
		UserStorage usersX = new UserStorage();
		return usersX.getArray();
	}
	
	public User getUser(String username, String password) {
		UserStorage usersX = new UserStorage();
		User user = usersX.getUser(username);
		if(user == null)
			return null;
		
		if(user.getPassword().equals(password))
			return user;
		else
			return null;
	}
	
	public User getUser(String username) {
		User user = users.getUser(username);
		if(user == null)
			return null;
		
		return user;
	}
	
	
	public boolean addUser(User user) {
		if(users.getUser(user.getUsername()) == null) {
		
			
			
		UserStorage usersX = new UserStorage();		
		FacilityStorage facilitiesX = new FacilityStorage();
		String idf="NONE";
		boolean isEQ= false;
		for(CarRentalFacility crf:facilitiesX.getAll()) {
			idf=crf.getId();
		}
		
		for(User us:usersX.getAll()) {
			if(us.getFacility().equals(idf)) {
				isEQ=true;
			}
		}		
		if(!isEQ) {
			user.setFacility(idf);
		}		
		return users.addUser(user);
		}else {
			return false;}
		
	}
	
	

	public boolean addFacilityToManager(String username, CarRentalFacility facility) {
		User user = users.getUser(username);
		if(user == null)
			return false;
		
		user.setFacility(facility.getId());
		
		return users.addUser(user);
	}
	
	public boolean blockUser(String username) {
		User user = users.getUser(username);
		if(user == null)
			return false;
		user.setFacility("KONJINO");
		user.setDeleted(true);
		user.deleted=true;
		return users.addUser(user);
	}
	
	public boolean susUser(String username) {
		User user = users.getUser(username);
		if(user == null)
			return false;
		
		int susCount=0;
		
		LocalDate currentDate = LocalDate.now();
		LocalDate thirtyDaysAgo = currentDate.minusDays(30);
		
		for(Order o: orderStorage.getArray()) {
			LocalDate targetDate = o.getDate();
			if(o.getStatus()==OrderStatus.CANCELED  && (!targetDate.isBefore(thirtyDaysAgo) && !targetDate.isAfter(currentDate))) {
				susCount++;
			}
			
		}
		if(susCount>=5) {		
			user.setSus(true);
			user.sus=true;
			System.out.println("SUMNJIV JE");
			System.out.println(susCount);
			System.out.println("IS DELETEDxx");
			System.out.println("IS DELETED:" + user.isDeleted());
			System.out.println(user.isSus());
			System.out.println(user.isSus());
			System.out.println(user.isSus());
			System.out.println("SUMNJIV JE");
		}			
		return users.addUser(user);
	}
	
	
	public ArrayList<User> getAllAvailableManagers() {
		ArrayList<User> managers = new ArrayList<User>();
		UserStorage usersX = new UserStorage();
		
		for(User u: usersX.getAll()) 
			
			
			if(u.getRoleStr().equals("MANAGER") && u.getFacility().equals("null")) {
				managers.add(u);
			}
		
		return managers;
	}
	

	
	public User editUser(String username, String changes) {
		
		for(User user: users.getAll())
			if(user.getUsername().equals(username))
				users.removeUser(user);
		
		String[] changedValues = changes.split(",");
		User user = users.getUser(username);
		
		user.setPassword(changedValues[0]);
		user.setName(changedValues[1]);
		user.setSurname(changedValues[2]);
		user.setGender(changedValues[3]);
		user.setDateOfBirth(LocalDate.parse(changedValues[4]));
		user.setPoints(Integer.parseInt(changedValues[5]));
		user.setCustomerType(changedValues[6]);
//		user.setRole();
		
		users.addUser(user);
		return user;
	}
	
	public String getManagerFacility(String username) {
		return users.getUser(username).getFacility();
	}
	
	public ArrayList<User> getUsersBySearch(String filter, 
			ArrayList<User> users, int i) {
		ArrayList<User> filteredUsers = new ArrayList<User>();
		
		filter = filter.toLowerCase();
		
		switch(i) {
			case 0:
				for(User sf: users)
				{
					String name = sf.getName().toLowerCase();
					if(filter.equals(name))
						filteredUsers.add(sf);
				}
				break;
			case 1:
				for(User sf: users)
				{
					String type = sf.getRoleStr().toLowerCase();
					if(filter.equals(type))
						filteredUsers.add(sf);
				}
				break;
			case 2:
				for(User sf: users)
				{
					String name = sf.getCustomerType().toLowerCase();
					if(filter.equals(name))
						filteredUsers.add(sf);
				}
				break;
			case 3:
				for(User sf: users)
				{
					String name = Integer.toString(sf.getPoints()).toLowerCase();
					if(filter.equals(name))
						filteredUsers.add(sf);
				}
				break;
			case 4:
				for(User sf: users)
				{
					String surname = sf.getSurname().toLowerCase();
					if(filter.equals(surname))
						filteredUsers.add(sf);
				}
				break;
			case 5:
				for(User sf: users)
				{
					String username = sf.getUsername().toLowerCase();
					if(filter.equals(username))
						filteredUsers.add(sf);
				}
				break;
			
		}
		
		return filteredUsers;
	}
	public ArrayList<String> getAllTypes() {
		ArrayList<String> types = new ArrayList<String>();
		types.add("");
		for(User sf: users.getAll()) {
			if(!types.contains(sf.getRoleStr())) {
				types.add(sf.getRoleStr());
			}
		}
		return types;
	}
	


	
}
