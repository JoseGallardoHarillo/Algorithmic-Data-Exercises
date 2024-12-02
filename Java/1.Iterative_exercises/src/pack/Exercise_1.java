package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.math.Math2;

public class Exercise_1 {
	
	public static List<Integer> ex_1(List<List<Integer>> ll){
		
		List<Integer> l = new ArrayList<Integer>();
		int i=0;
		
		while(i<ll.size()) {
			
			List<Integer> sl=ll.get(i);
			
			for(int j=0;j<sl.size();j++) {
				
				if(Math2.esPrimo(sl.get(j))==true) l.add(sl.get(j));
			}
			
			i++;		
		}
		
		return l;
	}
	
	public static List<List<Integer>> read_f(String file){
		
		List<List<Integer>> datas = Files2.streamFromFile(file).map(line->read_lines(line)).collect(Collectors.toList());
		
		return datas;
	}
				
	public static List<Integer> read_lines(String line){
				List<Integer> r = new ArrayList<>();
				if(line.length()>0) {
					for(String n: line.split(", ")) {
						r.add(Integer.parseInt(n));
					}
				}
						
				return r;
			}
}
