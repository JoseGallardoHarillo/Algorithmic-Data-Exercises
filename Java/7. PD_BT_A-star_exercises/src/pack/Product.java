package pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import us.lsi.common.Pair;

public class Product extends Pair<Double, Set<String>>{
	
	public static Product create(String s) {
		String[] split = s.split(":");
		String noCleanedValue = split[0].trim();
		Double value = Double.parseDouble(noCleanedValue.substring(5, 9));
		String[] split_2 = split[1].trim().split(",");
		Set<String> cf = new HashSet<String>();
		for(int i=0;i<split_2.length;i++) {
			cf.add(split_2[i]);
		}
		
		return new Product(value, cf);
	}
	
	public Product(Double value, Set<String> cf) {
		super(value,cf);
	}
	
	public Double getValue() {
		return this.first;
	}
	
	public List<String> getFunctionalities(){
		return new ArrayList<>(this.second);
	}
	
	public boolean functionalityContained(String f) {
		return this.second.contains(f);
	}
}
