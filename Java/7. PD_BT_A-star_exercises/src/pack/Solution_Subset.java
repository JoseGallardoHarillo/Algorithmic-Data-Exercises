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
	
	public Solution_Subset(List<Integer> l) {
		
		DicSet = new HashMap<String,List<Integer>>();
		GoalSum = 0.0;
		InputSet = Data_Subset.Subset;
		
		for(int i=0;i<l.size();i++) {
			Integer sc = l.get(i)+1;
			Integer n = Data_Subset.getNumber(i);
			List<Integer> numbersPerSubset = new ArrayList<>();
			String stringSubset = "Elements of the subset "+sc+": ";
			
			if(DicSet.containsKey(stringSubset)) {
				DicSet.get(stringSubset).add(n);
			}
			
			else {
				numbersPerSubset.add(n);
				DicSet.put(stringSubset, numbersPerSubset);
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
