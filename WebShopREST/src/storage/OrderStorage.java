package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

import enums.OrderStatus;
import model.CarRentalFacility;
import model.Order;
import model.User;
import model.Vehicle;

public class OrderStorage {
	private HashMap<String, Order> allContent;
	private ArrayList<Order> allCont;
	private File file;
	
	public OrderStorage() {
		this("resources\\data");
	}
	
	private OrderStorage(String path) {
		this.allContent= new HashMap<String, Order>();
		this.allCont = new ArrayList<Order>();
		
		BufferedReader in = null;
		try {
			file = new File(path + "/orders.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readAllContent(in);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (Exception e){}
			}
		}
        if (in != null) {
            try {
                in.close();
            }
            catch (Exception ex2) {}
        }
	}
	 
	private void readAllContent(BufferedReader in) {
		String line, id = "",vehicles="", facility = "", date = "", time = "", user = "",name="", surname="", status = "";
		int leaseDuration = 0;
		double price = 0.0;
		StringTokenizer st;
		
		try {
			while((line = in.readLine()) != null) {
				line = line.trim();
				if(line.equals("") || line.indexOf("#") == 0) 
					continue;
				st = new StringTokenizer(line, ";");
				while(st.hasMoreTokens()) {
					id = st.nextToken().trim();
					vehicles = st.nextToken().trim();
					facility = st.nextToken().trim();
					date = st.nextToken().trim();
					time = st.nextToken().trim();
					leaseDuration = Integer.parseInt(st.nextToken().trim());
					price = Double.parseDouble(st.nextToken().trim());
					user = st.nextToken().trim();
					name = st.nextToken().trim();
					surname = st.nextToken().trim();
					status = st.nextToken().trim();

					
				}


				
				LocalDate formated = LocalDate.parse(date);

				LocalTime t = LocalTime.parse(time);
				OrderStatus stat = OrderStatus.valueOf(status);
				System.out.println(formated);
				System.out.println(t);
				
				Order o = new Order(id, vehicles,facility,formated,t,leaseDuration,price,user,name, surname, stat);				
				allContent.put(o.getId(), o);
				allCont.add(o);
				
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void save() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(file), true);
			
			for(final Order o: this.allContent.values()) {
				final String str = makeLine(o);
				out.println(str);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String makeLine(final Order order) {
		StringBuilder str = new StringBuilder();
		str.append(order.getId());
		str.append(";");
		str.append(order.getVehicles());
		str.append(";");
		str.append(order.getFacility());
		str.append(";");
		str.append(order.getDate());
		str.append(";");
		str.append(order.getTime());
		str.append(";");
		str.append(order.getLeaseDuration());
		str.append(";");
		str.append(order.getPrice());
		str.append(";");
		str.append(order.getUsername());
		str.append(";");
		str.append(order.getName());
		str.append(";");
		str.append(order.getSurname());
		str.append(";");
		str.append(order.getStatus());
		
		
		
		return str.toString();
	}
	

	public Collection<Order> getAll() {
		return allContent.values();
	}
	
	public HashMap<String, Order> getHashMap() {
		return allContent;
	}
	
	public ArrayList<Order> getArray() {
		return allCont;
	}
	
	public Order getById(String id) {
		return allContent.get(id);
	}
	
	public Order addOrder(Order order) {
		if(order.getVehicles().equals("")) {
			order.setVehicles("0");
		}
		System.out.println("OKEJ DODAVANJE?1");
		allContent.put(order.getId(), order);
		System.out.println("OKEJ DODAVANJE?2");
		allCont.add(order);
		System.out.println("OKEJ DODAVANJE?3");
		save();
		System.out.println("OKEJ DODAVANJE?4");
		return order;
	}
	
	public Order editOrder(Order order) {
		System.out.println("EDIT KRECE");
		allContent.put(order.getId(), order);
		for(final Order sf: allCont) {
			if(sf.getId().equals(order.getId())) {
				allCont.remove(sf);
				allCont.add(order);
				break;
			}
		}
		System.out.println("EDIT KRECE");
		save();
		System.out.println("EDIT KRECE");
		return order;
	}
}
