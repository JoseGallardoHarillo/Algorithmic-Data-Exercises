package pack;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Solution_Lawyers {
	
	private Map<String,List<Integer>> Assignments;
	private Integer HoursSpent;
	private Integer TotalHours;
	public Integer TotalHoursParallel;
	private Double Mean;
	
	public Solution_Lawyers(List<Integer> ls) {
		
		Assignments = new HashMap<String,List<Integer>>();
		
		for(int i=0;i<ls.size();i++) {
			List<Integer> selectedCases = new ArrayList<>();
			Integer lawyer = ls.get(i);
			String lawyerName = Data_Lawyer.getLawyer(lawyer).getLawyer();
			
			if(Assignments.containsKey(lawyerName)) {
				Assignments.get(lawyerName).add(i+1);
			}
			
			else {
				selectedCases.add(i+1);
				Assignments.put(lawyerName, selectedCases);
			}
		}
	}
	
	
	public Solution_Lawyers(Map<String,Double> variables) {
		
		Assignments = new HashMap<String,List<Integer>>();
		
		for(Entry<String,Double> pair:variables.entrySet()) {
			
			if(pair.getValue()>0.0) {
				List<Integer> selectedCases = new ArrayList<>();
				String[] split_X = pair.getKey().split("_");
				
				if(split_X[0].equals("x")) {
					Integer i = Integer.parseInt(split_X[1]);
					Integer j = Integer.parseInt(split_X[2]);
					String lawyerName = Data_Lawyer.getLawyer(i).getLawyer();
					if(Assignments.containsKey(lawyerName)) {
						Assignments.get(lawyerName).add(j);
					}
					
					else {
					selectedCases.add(j);
					Assignments.put(lawyerName, selectedCases);
					}
				}
			}
		}
	}
	
	
	public String toString() {
		
		TotalHours = 0;
		TotalHoursParallel = 0;
		String str = "";
		for(Entry<String,List<Integer>> completedCases: Assignments.entrySet()) {
			Lawyer ab = Data_Lawyer.getLawyer(completedCases.getKey());
			List<String> casesName = new ArrayList<>();
			List<Integer> cases = new ArrayList<>();
			
			for(Integer Case:completedCases.getValue()) {
				cases.add(ab.getHora(Case));
				String CaseWithString = "Case " + Case;
				casesName.add(CaseWithString);
			}
			HoursSpent = cases.stream().mapToInt(Integer::intValue).sum();
			
			if(TotalHoursParallel<HoursSpent) {
				TotalHoursParallel = HoursSpent;
			}
			
			Mean = Double.valueOf(HoursSpent)/completedCases.getValue().size();
			TotalHours += HoursSpent;
			str = str+completedCases.getKey() +"\nSpent hours: "+HoursSpent+"\nStudied cases: "
			+ casesName +"\nMean (hours/case): "+String.format("%.2f", Mean)
			+"\n· -- - -- · -- - -- · -- - -- · -- - -- · -- - -- · -- - -- · -- - -- · -- -\n";
		}
		str = str+"\nThe study of all the cases has resulted in a total of"+ TotalHours
				+ " working hours for the buffete, which, by working in parallel, has been able to be completed in"
				+ TotalHoursParallel +" hours.";
		
		return str;
	}
}