package pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class Solution_Products {
	
	private List<String> lfd;
	private Map<String, List<String>> ProductsWithFunctionalities;
	private List<String> ObtainedFunctionalities;
	private Double TotalValue;
	
	public Solution_Products(List<Integer> ls) {
		ProductsWithFunctionalities = new HashMap<String, List<String>>();
		
		Set<String> cfc = new HashSet<String>();
		
		TotalValue = 0.0;
		
		for(int i=0;i<ls.size();i++) {
			
			if(ls.get(i)==1) {
				
				ProductsWithFunctionalities.put("PO"+(i+1)+" ("+Data_Product.getProduct(i).getValue().toString()+" euros)",
						Data_Product.getProduct(i).getFunctionalities());
				
				TotalValue+=Data_Product.getProduct(i).getValue();
				
				cfc.addAll(Data_Product.getProduct(i).getFunctionalities());
			}
		}
		
		ObtainedFunctionalities = new ArrayList<String>(cfc);
		lfd = Data_Product.getWishedFuncionalities();
	}
	
	public Solution_Products(Double goal, Map<String, Double> variables) {
		
		TotalValue = goal;
		ProductsWithFunctionalities = new HashMap<String, List<String>>();
		
		Set<String> cfc = new HashSet<String>();
		
		for(Entry<String, Double> pair : variables.entrySet()) {
		
			if(pair.getValue()>0) {
				Integer i = Integer.parseInt(pair.getKey().substring(2));
				ProductsWithFunctionalities.put("PO"+(i+1)+" ("+Data_Product.getProduct(i).getValue().toString()+" euros)", 
						Data_Product.getProduct(i).getFunctionalities());
				cfc.addAll(Data_Product.getProduct(i).getFunctionalities());
			}	
		}
		
		ObtainedFunctionalities = new ArrayList<String>(cfc);
		lfd = Data_Product.getWishedFuncionalities();
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
