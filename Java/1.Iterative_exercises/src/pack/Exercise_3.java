package pack;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.geometria.Punto2D;

public class Exercise_3 {
	
		public static Map<Punto2D.Cuadrante,Double> ex_3(List<Punto2D> l){
			
			Map<Punto2D.Cuadrante, Double> dictionary = new HashMap<Punto2D.Cuadrante, Double>();
			int i=0;
			
			while(i<l.size()) {
				
				Punto2D.Cuadrante cuadrante = l.get(i).getCuadrante();
				Double valor = dictionary.get(cuadrante);
				Double v = 0.0;

				if (valor==null){
				
					v=l.get(i).getX();
					dictionary.put(cuadrante, v);
				}

				else if(valor!=null){
					
					v = valor+l.get(i).getX();
					dictionary.put(cuadrante, v);
				}
				
				i++;
			}
			
			return dictionary;
		}
		
		public static List<Punto2D> read_f3 (String f){
			
			return Files2.streamFromFile(f).filter(x->x.startsWith("(")).map(x->read_lines3(x))
					.collect(Collectors.toList());
		}		
		
		public static Punto2D read_lines3(String linea) {
			
			String[] components = linea.substring(1,linea.length()-1).split(",");
			Double x = Double.parseDouble(components[0]);
			Double y = Double.parseDouble(components[1]);
			
			return Punto2D.create(x,y);
		}
		
		

}
