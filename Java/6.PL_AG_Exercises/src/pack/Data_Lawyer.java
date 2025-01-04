package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import us.lsi.common.Files2;

public class Data_Lawyer {
	
	public static Integer NLawyer, NC;
	private static List<Lawyer> lawyers;
	
	public static void iniDatas(String file) {
		
		lawyers = new ArrayList<>();
		
		for(String line: Files2.linesFromFile(file)) {
			
			Lawyer l = Lawyer.create(line);
			
			lawyers.add(l);
		}
		
		NLawyer = lawyers.size();
		NC = lawyers.get(0).getNC(lawyers.get(0).getCaseList());
	}
	
	public static List<Lawyer> getLawyers(){
		return lawyers;
	}
	
	public static Lawyer getLawyer(String name) {
		
		Lawyer r = null;
		
		for(int i=0;i<lawyers.size();i++) {
		
			Lawyer l = lawyers.get(i);
			
			if(l.getLawyer().equals(name))
				r = l;
		}
		
		return r;
	}
	
	public static Lawyer getLawyer(Integer index) {
		return lawyers.get(index);
	}
	
	public static Integer getHour(Integer index, Integer c) {
		return lawyers.get(index).getCaseList().get(c);
	}
	
	public static Map<Integer,Integer> getCases(Integer index){
		return getLawyer(index).getCaseList();
	}
	
	public static void toConsole() {
		System.out.println("Bufete: " + lawyers);
	}	
}