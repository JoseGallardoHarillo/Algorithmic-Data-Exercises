package Manual;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import graph.Subset_Vertex;
import pack.Data_Subset;
import pack.Solution_Subset;
import us.lsi.common.Lists2;

public class Subset_PD_Manual {
	
	//Initial Vertex
	
	public static Subset_Vertex start;
	
	//Best Solution Value
	
	public static Double MinValue = Double.MAX_VALUE;
	
	//For each vertex it is stored the best solution value with it can reach
	
	public static List<Integer> pd(Double mv){

		Subset_PD_Manual.MinValue = mv;
		Map<Subset_Vertex, Spm> mem = new HashMap<>();
		Subset_Vertex v = Subset_Vertex.initialVertex();
		pd(v, mv, mem);
		
		return edges(mem, v);
	}
	public static Spm pd(Subset_Vertex v, Double storedValue, Map<Subset_Vertex, Spm> mem) {
		
		//Edge beginning
		
		Spm r;
		
		//Recogo los datos
		
		Boolean vFinal = v.index==Data_Subset.Size_S;
		Boolean validSolution = Arrays.stream(v.takeOutSps()).allMatch(x->x==0);
		
		//check if it is in memory
		
		if(mem.containsKey(v)) {
			r = mem.get(v);
			
		}else if(vFinal && validSolution) {
			r = Spm.empty();
			mem.put(v, r);
			
			if(storedValue < MinValue)
				MinValue = storedValue;
			
		}
		
		else {
			List<Spm> soluciones = Lists2.empty();
			
			//We'll see the arternatives
			
			for(Integer action: v.actions()) {
				
				Subset_Vertex neightbour = v.neighbor(action);
				
				if(action==0) 
					storedValue = storedValue + 1.0;
				
				Spm am = pd(neightbour, storedValue, mem);
				if(am != null) {
					Spm amp = Spm.of(action, am.weight);
					soluciones.add(amp);
				}
			}
			r = soluciones.stream().min(Comparator.naturalOrder()).orElse(null);
			
			if(r!=null) {
				mem.put(v, r);
			}
			
		}
		return r;
	}
	
	public static List<Integer> edges(Map<Subset_Vertex, Spm> mem, 
			Subset_Vertex v){
		
		List<Integer> edges = Lists2.empty();
		Spm ComparingEdge = mem.get(v);
		while(ComparingEdge != null && ComparingEdge.a != null) {
			Subset_Vertex old = v;
			v = old.neighbor(ComparingEdge.a);
			edges.add(ComparingEdge.a);
			ComparingEdge = mem.get(v);
		}
		return edges;
	}
	
	
	static class Spm implements Comparable<Spm>{
		Integer a = null;
		Integer weight = null;
		
		public static Spm of(Integer a, Integer weight) {
			return new Spm(a, weight);
		}
		
		public static Spm of(Integer weight) {
			return new Spm(null, weight);
		}
		
		public static Spm empty() {
			return new Spm(null, 0);
		}
		
		public Spm(Integer a, Integer weight) {
			super();
			this.a = a;
			this.weight = weight;
		}
		
		public Integer a() {
			return a;
		}
		
		public Integer weight() {
			return weight;
		}

		@Override
		public int compareTo(Spm sp) {
			return this.weight.compareTo(sp.weight);
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((a == null) ? 0 : a.hashCode());
			result = prime * result + ((weight == null) ? 0 : weight.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Spm other = (Spm) obj;
			if (a == null) {
				if (other.a != null)
					return false;
			} else if (!a.equals(other.a))
				return false;
			if (weight == null) {
				if (other.weight != null)
					return false;
			} else if (!weight.equals(other.weight))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Spm [a=" + a + ", weight=" + weight + "]";
		}
	}
	
	public static void main(String[] args) {
		Locale.setDefault(new Locale("en", "US"));
		
		for(int idS=0;idS<=4;idS++) {
			
			if(idS!=1) {
			
				Data_Subset.iniDatas("files/input_4.txt",idS);
		
				System.out.println("");
				System.out.println("Results of the subset "+idS+" :");
				System.out.println("");
		
				List<Integer> edges = Subset_PD_Manual.pd(0.0);
		
				Solution_Subset sPDM = new Solution_Subset(edges);
				
				System.out.println(sPDM.toString());
			}
		}
	}
}
