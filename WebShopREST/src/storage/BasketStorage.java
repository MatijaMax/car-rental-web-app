package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.StringTokenizer;

import model.Basket;
import model.CarRentalFacility;
import model.User;

public class BasketStorage {
	private Basket userBasket;
	private File file;
	public BasketStorage() {
		this("resources\\data");
	}
	
	public BasketStorage(String path) {
		BufferedReader in = null;
		try {
			file = new File(path + "/basket.txt");
			System.out.println(file.getCanonicalPath());
			in = new BufferedReader(new FileReader(file));
			readTheBasket(in);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch (Exception e){}
			}
		}
	}
	
	private void readTheBasket(BufferedReader in) {
		String line, username = "", content="";
		double price = 0.0;
		
		StringTokenizer st;
		
		try {
			int i = 0;
			while((line = in.readLine()) != null) {
				line = line.trim();
				if(line.equals("") || line.indexOf("#") == 0) 
					continue;
				st = new StringTokenizer(line, ";");
				while(st.hasMoreTokens()) {
					username = st.nextToken().trim();
					price = Double.parseDouble(st.nextToken().trim());
					content = st.nextToken().trim();
				}
				Basket bas = new Basket(username, price, content);
				//allFacilities.put(java.util.UUID.randomUUID().toString(), fac);
				userBasket = bas;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void save() {
		try {
			PrintWriter out = new PrintWriter(new FileWriter(file), true);
			

				String str = makeLine(userBasket);
				out.println(str);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
private String makeLine(Basket basket) {
		
		StringBuilder str = new StringBuilder();

		str.append(basket.getUsername());
		str.append(";");
		str.append(basket.getPrice());
		str.append(";");
		str.append(basket.getContent());
		
		return str.toString();
	}
	
public Basket getBasket() {
	//readTheBasket();
	return userBasket;
}

public Basket addBasket(Basket basket)  {
	if(basket.getContent().equals("")) {
		basket.setContent("0");
	}
	userBasket=basket;
	save();
	return basket;
}

public Basket editBasket(Basket basket) {
	userBasket=basket;
	save();
	return basket;
}

}
