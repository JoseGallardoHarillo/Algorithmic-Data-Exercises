package pack;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.math.Math2;

public class Exercise_2 {
	
		public static String ex_2(Integer limit) {
			
			int i = 2;
			String message = "";
			
			while(i <= limit) {
			
				if(Math2.esPrimo(i)==true) {
				
					Integer primeSquare = i*i; 
					String primoStr = primeSquare.toString().concat("\n");
					message = message.concat(primoStr);
				}
				
				i++;
			}
			
			return message;
		}
		
		public static List<Integer> read_f2(String file){
			
			List<Integer> datas = Files2.streamFromFile(file)
					.filter(x->x.startsWith("L")).map(line->read_lines2(line))
					.collect(Collectors.toList());
			
			return datas;
		}
	
		public static Integer read_lines2(String linea){
			
			String[] subLine = linea.split(": ");
			Integer n = Integer.parseInt(subLine[1]);
			
			return n;
		}

}
