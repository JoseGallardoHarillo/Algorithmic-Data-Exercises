package pack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;

public class Data_Subset {
	
	public static Set<Integer> Subset;
	public static Integer Size_S;
	
	public static void iniDatas(String file, Integer ln) {
		
		Subset = new HashSet<Integer>();
		List<Set<Integer>> lsi = new ArrayList<Set<Integer>>();
		
		for(String line: Files2.linesFromFile(file)) {
			Set<Integer> ls = new HashSet<Integer>();
			String[] split = line.split(", ");
			
			for(int i=0;i<split.length;i++) {
				ls.add(Integer.parseInt(split[i]));
			}
			lsi.add(ls);
		}
		
		Subset = lsi.get(ln);
		
		Size_S = Subset.size();
	}
	
	public static Integer getNumber(Integer index) {
		List<Integer> li = new ArrayList<Integer>(Subset);
		return li.get(index);
	}

}
