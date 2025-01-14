package pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Solution_Students {
	private Double MeanAfinity; 
	private Map<String, List<String>> Groups;
	
	public Solution_Students(List<Integer> ls) {
		
		MeanAfinity = 0.0;
		Groups = new HashMap<String, List<String>>();		
		
		for(int j=1;j<=Data_Student.getNG();j++) {
			List<String> AperG = new ArrayList<String>(); 
			
			Groups.put("Group "+j+" ", AperG);
		}
		
		for(int i=0;i<ls.size();i++) {
			
			String groupA = ls.get(i).toString();
			String groupWithString = "Group "+groupA+" ";
			String currentStudent = Data_Student.getStudent(i).getNStudent();
			List<String> key = Groups.get(groupWithString);
			
			key.add(currentStudent);
			
			MeanAfinity+=Data_Student.getAfinity(i, ls.get(i));
		}
		
		MeanAfinity = MeanAfinity/ls.size();
	}
	
	public Solution_Students(Double goal, Map<String, Double> variables) {
		
		MeanAfinity = goal/Data_Student.NStudents;
		Groups = new HashMap<String, List<String>>();
		
		for(Entry<String, Double> pair : variables.entrySet()) {
			
			String[] split_X = pair.getKey().split("_");
			Integer i = Integer.parseInt(split_X[1]);
			Integer j = Integer.parseInt(split_X[2]);
			String groupWithString = "Grupo "+j+" ";
			String currentStudent = Data_Student.getStudent(i).getNStudent();
			
			if(pair.getValue()==1) {
				
				if(Groups.containsKey(groupWithString)) {
					List<String> studentsInGroup = Groups.get(groupWithString);
					studentsInGroup.add(currentStudent);
				}
				
				else {
					List<String> studentsInGroup = new ArrayList<String>();
					studentsInGroup.add(currentStudent);
					Groups.put(groupWithString,studentsInGroup);
				}
			}
			
		}
		
	}
	
	public String toString() {
		
		String GroupStrings="Distribution obtained:\n";
		
		for(Entry<String, List<String>> groupSection : Groups.entrySet())
			GroupStrings = GroupStrings+groupSection+"\n";
		
		GroupStrings = GroupStrings+"\nMean afinity: "+MeanAfinity;
		
		return GroupStrings;
	}
}
