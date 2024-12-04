package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class Exercise_4 {
	
	
	//Stateless
	
	public static Integer ex_4_SL(Integer a, Integer b) {
		
		Integer r;
		
		if(a<2&&b<2) r = a+b*b;
		
		else if(a<2||b<2) r = a*a+b;
		
		else r = ex_4_SL(a/2, b-1)+ex_4_SL(a/3, b-2)+ex_4_SL(a-2, b/4);
		
		return r;
	}
	
	//Stateful
	
	public static Integer ex_4_SF(Integer a, Integer b) {
	
		Integer[][] mem = new Integer[a+1][b+1];
		
		return ex_4_SF_aux( a, b, mem);
	}
		
	public static Integer ex_4_SF_aux(Integer a, Integer b, Integer[][] mem) {
		
		if(a<2&&b<2) mem[a][b]= a+b*b;
		
		else if(a<2||b<2) mem[a][b] = a*a+b;
		
		else mem[a][b] = ex_4_SF_aux(a/2, b-1,mem)+ex_4_SF_aux(a/3, b-2,mem)+ex_4_SF_aux(a-2, b/4,mem);
		
		return mem[a][b];
	}
	
	//Iterative
	
	public static Integer ex_4_It(Integer a, Integer b) {
		
		Integer[][] mem = new Integer[a+b][b+1];
		
		for(int a0=0;a0<=a;a0++) {
			
			for(int b0=0;b0<=b;b0++) {
				
				if(a0<2&&b0<2) mem[a0][b0]= a0+b0*b0;
				
				else if(a0<2||b0<2) mem[a0][b0] = a0*a0+b0;
				
				else mem[a0][b0] = ex_4_SF_aux(a0/2, b0-1,mem)+ex_4_SF_aux(a0/3, b0-2,mem)+ex_4_SF_aux(a0-2, b0/4,mem);
			}
		}

		return mem[a][b];
		
		
	}
	
	
	
	//File Reading
	
	public static List<List<Integer>> read_f4(String file){
		
		List<List<Integer>> datas = Files2.streamFromFile(file).map(line->read_lines_4(line))
				.collect(Collectors.toList());
		
		return datas;
	}

	public static List<Integer> read_lines_4(String linea){
		
		List<Integer> r = new ArrayList<>();
		
		if(linea.length()>0) for(String n: linea.split(",")) r.add(Integer.parseInt(n));
				
		return r;
	}

}
