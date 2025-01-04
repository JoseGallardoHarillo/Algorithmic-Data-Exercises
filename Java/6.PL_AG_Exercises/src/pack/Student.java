package pack;

import java.util.HashMap;
import java.util.Map;

import us.lsi.common.Pair;

public class Student extends Pair<String, Map<Integer, Double>>{
	
	public static Student create(String s) {
		String[] split = s.split(":");
		String student = split[0].trim();
		Map<Integer, Double> dic = new HashMap<Integer, Double>();
		String[] split_2 = split[1].trim().split(",");
		for(int i=0;i<split_2.length;i++) {
			dic.put(i+1, Double.parseDouble(split_2[i]));
		}
		
		
		return new Student(student, dic);
	}
	
	public Student(String student, Map<Integer, Double> dic) {
		super(student,dic);
	}
	
	
	public String getNStudent() {
		return this.first;
	}
	
	public Map<Integer, Double> getGroupList(){
		return this.second;
	}
	
	public Integer getNG(Map<Integer, Double> dic) {
		return dic.size();
	}
}
