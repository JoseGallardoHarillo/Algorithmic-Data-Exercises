package pack;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import us.lsi.common.Pair;

public class Lawyer extends Pair<String,Map<Integer,Integer>> {
	
	public static Lawyer create(String line) {
		
		String[] split = line.split(":");
		String lawyer = split[0].trim();
		
		Map<Integer,Integer> dic = new HashMap<Integer, Integer>();
		String[] split_2 = split[1].trim().split(",");
		
		for(int i=0;i<split_2.length;i++)
			dic.put(i+1, Integer.parseInt(split_2[i]));
			
		return new Lawyer(lawyer,dic);		
	}
	
	public Lawyer(String a,Map<Integer,Integer> h) {
		super(a,h);
	}
	
	public String getLawyer() {
		return this.first;
	}
	
	public Map<Integer,Integer> getCaseList(){
		return this.second;
	}
	
	public Integer getNC(Map<Integer, Integer> dic) {
		return dic.size();
	}
	
	public Integer getHora(Integer Case){
		return this.getCaseList().get(Case);
	}

}
