package pack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class Exercise_3 {
	
	//Stateless
	
	public static Long ex_3_SL(Integer n) {
		
		Long r;
	
		if(n==1||n==2) r= 1L;
	
		else if(n==0) r= 2L;
	
		else r= (4*ex_3_SL(n-1))+ex_3_SL(n-2)+ex_3_SL(n-3);
		
		return r;
	}
	
	//Stateful
	
	public static Long ex_3_SF(Integer n) {
		
		Map<Integer, Long> mem = new HashMap<Integer, Long>();
		
		return ex_3_SF_aux(n, mem);
	}
	
	public static Long ex_3_SF_aux(Integer n, Map<Integer, Long> mem) {
		
		Long r;
		
		if(mem.containsKey(n)) r = mem.get(n);
		
		else if(n==0) {
			
			r = 2L;
			mem.put(n, r);
		}
		
		else if(n==1) {
			
			r = 1L;
			mem.put(n, r);
		}
		
		else if(n==2) {
			
			r = 1L;
			mem.put(n, r);
		}
		
		else {
			
			r = (4*ex_3_SF_aux(n-1,mem))+ex_3_SF_aux(n-2,mem)+ex_3_SF_aux(n-3,mem);
			mem.put(n, r);
		}
		
		return r;
	}

	//Iterative

	public static Long ex_3_It(Integer n) {
		
		Integer i = 0;
		
		Long v = 2L; //f(i)
		Long u = 1L; //f(i+1)
		Long w = 1L; //f(i+2)
	
	
		while(i<n) {	
			//<w',u',v',i'> = <f(i'+2),f(i'+1),f(i'),i'> = <f(i+3),f(i+2),f(i+1),i+1>=
			//= <(4*f(i+2))+f(i+1)+f(i),w,u,i+1> = <4*w+u+v,w,u,i+1>
			
			Long w0 = w; 
			Long u0 = u;
			
			w= (4*w0)+u0+v;
			u= w0;
			v= u0;
			i = i+1;
		}
		
		return v;
	}

	//File Reading

	public static List<Integer> read_f3(String file){
		
		List<Integer> datas = Files2.streamFromFile(file)
				.filter(x->x.startsWith("n")).map(line->read_lines_3(line))
				.collect(Collectors.toList());
		
		return datas;
}

	public static Integer read_lines_3(String line){
		
		String[] subLine = line.split("n=");
		Integer n = Integer.parseInt(subLine[1]);
		
		return n;
	}
}
