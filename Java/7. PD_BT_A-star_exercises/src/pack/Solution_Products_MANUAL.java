package pack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Solution_Products_MANUAL {

	private List<String> lfd;
	private Map<String, List<String>> ProductsWithFunctionalities;
	private List<String> ObtainedFunctionalities;
	public Double TotalValue;
	
	public static Solution_Products_MANUAL create(List<Integer> l) {
		
		return new Solution_Products_MANUAL(l);
	}
	
	public Solution_Products_MANUAL(List<Integer> l) {
		ProductsWithFunctionalities = new LinkedHashMap<String, List<String>>();
		Set<String> cfc = new HashSet<String>();
		TotalValue = 0.0;
		lfd = Data_Product.getWishedFuncionalities();
		
		for(int i=0;i<l.size();i++) {
			
			Integer ob = l.get(i);
			
				ProductsWithFunctionalities.put("PO"+ob+" ("+Data_Product.getProduct(ob-1).getValue().
						toString()+" euros)",
						Data_Product.getProduct(ob).getFunctionalities());
				
				TotalValue+=Data_Product.getProduct(ob).getValue();
				cfc.addAll(Data_Product.getProduct(ob).getFunctionalities());
			
		}
		
		ObtainedFunctionalities = new ArrayList<String>(cfc);
	}
	
	public String toString() {
		
		String groupString="";
		
		groupString = "Functionalities to cover: "+lfd+"\nComposition of the selected batch:\n";
		
		for(Entry<String, List<String>> seccionProducto : ProductsWithFunctionalities.entrySet())
			groupString = groupString+seccionProducto+"\n";
		
		groupString = groupString+"Functionalities of the selection: "+ObtainedFunctionalities
				+"\nTotal price of the selected batch: "+String.format("%.2f", TotalValue);
		
		return groupString;
	}
}
