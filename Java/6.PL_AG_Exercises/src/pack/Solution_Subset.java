package pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.math3.analysis.function.Min;

public class Solution_Subset {
	private Set<Integer> InputSet;
	private Double GoalSum;
	private Integer MinorElementsSet;
	private HashMap<String, List<Integer>> DicSet;
	
	public Solution_Subset(Double goal, Map<String,Double> variables) {
		
		DicSet = new HashMap<String,List<Integer>>();
		GoalSum = goal;
		InputSet = Data_Subset.Subset;
		
		for(Entry<String,Double> pair:variables.entrySet()) {
			
			if(pair.getValue()==1) {
				
				List<Integer> numberPerSet = new ArrayList<>();
				String[] split_X = pair.getKey().split("_");
				Integer i = Integer.parseInt(split_X[1]);
				Integer n = Data_Subset.getNumber(i);
				Integer j = Integer.parseInt(split_X[2]);
				String setString = "Set elements"+(j+1)+": ";
				
				if(DicSet.containsKey(setString)) {
					DicSet.get(setString).add(n);
				}
				
				else {
					numberPerSet.add(n);
					DicSet.put(setString, numberPerSet);
				}
			}
		}
		MinorElementsSet = DicSet.get("Set elements 1: ").size()<DicSet.get("Set elements 2: ").size()?
				DicSet.get("Set elements 1: ").size():(DicSet.get("Set elements 2: ").size()<
						DicSet.get("Set elements 3: ").size())?DicSet.get("Set elements 2: ").size():
							DicSet.get("Set elements 3: ").size();
	}
	
	
	public String toString() {
		
		String str = "Input set: "+InputSet+"\nGoal sum: "+GoalSum
				+"\no~~~~業~~~o~~~~業~~~o~~~~業~~~o~~~~業~~~o~~~~業~~~o~~~~業~~~o~~~~業~~~o\nThe minor set has "
				+MinorElementsSet+" elements.\n";
		
		for(Entry<String,List<Integer>> numbersPerSet:DicSet.entrySet()) 
			str = str+numbersPerSet.getKey()+numbersPerSet.getValue()+"\n";
			
		str = str+"o~~~~業~~~o~~~~業~~~o~~~~業~~~o~~~~業~~~o~~~~業~~~o~~~~業~~~o~~~~業~~~o";
		
		return str;
		
	}

}
