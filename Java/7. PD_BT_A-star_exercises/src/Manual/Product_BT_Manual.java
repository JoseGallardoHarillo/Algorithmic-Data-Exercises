package Manual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.jgrapht.GraphPath;

import graph.Product_Edge;
import graph.Product_Vertex;
import pack.Data_Product;
import pack.Solution_Products;
import pack.Solution_Products_MANUAL;
import us.lsi.common.Sets2;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class Product_BT_Manual {
	
	public static Product_BT_Vertex state;
	public static Set<Solution_Products_MANUAL> solutions = new HashSet<Solution_Products_MANUAL>();
	public static Double maxValue;
	
	public static void btm(Solution_Products_MANUAL v) {
		
		state = Product_BT_Vertex.initialVertex();
		solutions = new HashSet<Solution_Products_MANUAL>();
		
		if(v!=null) {
			solutions.add(v);
			maxValue = v.TotalValue;
		}
		
		else {
			maxValue = Double.MAX_VALUE;
		}
		btm();
	}
	
	public static void btm() {
		
		if(state.index >= Data_Product.NP) {
			
			if(Product_BT_Vertex.goal(state)) {
				
				Double statePrice = Product_BT_Vertex.selectionValue(state.selectedProducts);
				
				if(statePrice<maxValue) {
					maxValue = statePrice;
					solutions.add(Solution_Products_MANUAL.create(state.selectedProducts));	
				}
			}
		}
		
		else {
			
			List<Integer> alternatives = state.actions();
			
			
			for(Integer a : alternatives) {
				
				Double limit = state.Limit(a);
				
				if(limit<=maxValue) {
					
					state.forward(a);
					btm();
					state.back(a);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("en", "US"));
		
		for(int idFile=1;idFile<=3;idFile++) {
			
			Data_Product.iniDatas("files/input_3_"+idFile+".txt");
			System.out.println("");
			System.out.println("Test results "+idFile+":");
			System.out.println("");
			
			Solution_Products_MANUAL bestSolution = null;
			Product_BT_Manual.btm(bestSolution);
			
			for(Solution_Products_MANUAL s: Product_BT_Manual.solutions) {
				
				if(bestSolution==null || s.TotalValue<= bestSolution.TotalValue) {
					bestSolution= s;
				}
			}
			
			System.out.println(bestSolution);	
		}	
	}
}
