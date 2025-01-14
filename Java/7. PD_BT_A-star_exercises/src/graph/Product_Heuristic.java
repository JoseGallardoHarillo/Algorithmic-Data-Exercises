package graph;

import java.util.function.Predicate;
import java.util.stream.IntStream;

import pack.Data_Product;
import pack.Product;

public class Product_Heuristic {
	
	public static Double heuristic(Product_Vertex v, Predicate<Product_Vertex> goal, Product_Vertex v2) {
		return heuristic(v,Product_Vertex.NP);
	}
	
	public static Double heuristic(Product_Vertex v, int lastIndex) {
		
		if(v.remainingFuncionalities.size()==0) {
			return 0.;
		}
		
		else {
			Double minorValue = Double.MAX_VALUE;
			
			for(Integer item = v.index; item<lastIndex; item++) {
				Double value = Data_Product.getValue(item);
				if(value<minorValue) {
					minorValue = value;
				}
			}
			
			return minorValue;
		}
		
	}
	
}
