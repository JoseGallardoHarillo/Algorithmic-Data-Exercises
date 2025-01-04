package pack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;

public class Data_Product {
	
	public static Integer NP,NFD;
	private static List<String> WishedFunctionalities; 
	private static List<Product> Products;
	
	public static void iniDatas(String file) {
		
		Products = new ArrayList<Product>();
		Set<String> fd = new HashSet<String>();
		Integer counter= 0;
		
		for(String line:Files2.linesFromFile(file)) {
			if(counter==0) {
				String[] split = line.split(":");
				String[] split_2 = split[1].trim().split(",");
				for(int i=0;i<split_2.length;i++) {
					fd.add(split_2[i]);
				}
			}
			
			else {
				Product p = Product.create(line);
				Products.add(p);
			}
			counter++;
		}
		
		WishedFunctionalities = new ArrayList<String>(fd);
		NFD = WishedFunctionalities.size();
		NP = Products.size();
	}
	
	public static List<Product> getProducts(){
		return Products;
	}
	
	public static List<String> getWishedFuncionalities(){
		return WishedFunctionalities;
	}
	
	public static Integer getNP() {
		return NP;
	}
	
	public static Integer getNFD() {
		return NFD;
	}
	
	public static Product getProduct(Integer index){
		return Products.get(index);
	}
	
	public static Double getValue(Integer index) {
		return getProduct(index).getValue();
	}
	
	public static String getFD(Integer index) {
		return WishedFunctionalities.get(index);
	}
	
	public static void toConsole() {
		System.out.println("Wished functionalities: "+WishedFunctionalities);
		System.out.println("Productos: "+ Products);
	}
}
