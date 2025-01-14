package graph;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import pack.Data_Subset;
import us.lsi.common.Arrays2;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class Subset_Vertex extends ActionVirtualVertex<Subset_Vertex,Subset_Edge,Integer>{
	
	public Integer index;
	public Integer[] sumPerSubset = new Integer[3];
	
	//Factories
	
	public Subset_Vertex(Integer index, Integer[] sps) {
		this.index = index;
		this.sumPerSubset = Arrays2.copyArray(sps);
	}
	
	public static Subset_Vertex initialVertex() {
		
		//Total sum of the main subset values
		Integer sum = Data_Subset.Subset.stream().mapToInt(Integer::intValue).sum();
		
		//Igualamos el valor total entre los tres subconjuntos
		Integer [] a = {sum/3,sum/3,sum/3}; 
		Subset_Vertex v =  new Subset_Vertex(0,a);
		return v;
	}
	
	public static boolean goal(Subset_Vertex v) {
		return v.index == Data_Subset.Size_S;
	}
	
	//Override methods
	
	@Override
	public Boolean isValid() {
		return this.index>=0 && this.index<=Data_Subset.Size_S && 
				Arrays.stream(this.sumPerSubset).allMatch(v->v>=0);
	}

	@Override
	public List<Integer> actions() {
		List<Integer> alternatives = new LinkedList<>();
		
		
		//Cuando llegue al último 
		if(this.index==Data_Subset.Size_S) {
			
			Integer[] copy = Arrays2.copyArray(this.sumPerSubset);
			
			//If there are somo case whose sum isn't available
			if(Arrays.stream(copy).filter(v->v>0).toArray().length == 1) {
				
				//We add the alternative where the selected subset after add the right sum number
				for (int i = 0; i < copy.length; i++) {
					if(copy[i]-Data_Subset.getNumber(this.index)==0) {
						alternatives.add(i);
					}
				}
			}
		}
		
		//Otherwise, simply add as alternative each subset whose sum isn't available
		else {
			for (int j = 0; j < sumPerSubset.length; j++) {
				if(sumPerSubset[j]-Data_Subset.getNumber(this.index)>=0)
					alternatives.add(j);
			}
		}
		
		return alternatives;
	}

	@Override
	public Subset_Vertex neighbor(Integer a) {
		Integer[] copy = Arrays2.copyArray(this.sumPerSubset);
		//We subtract the remaining value of the subset to achieve the sum
		
		switch(a) {
			case 0:{
				copy[0] = copy[0]-Data_Subset.getNumber(this.index);
				break;
			}
			case 1:{
				copy[1] = copy[1]-Data_Subset.getNumber(this.index);
				break;
			}
			case 2:{
				copy[2] = copy[2]-Data_Subset.getNumber(this.index);
				break;
			}
			default:{
				throw new IllegalArgumentException("Unexpected value: " + a);
			}
		}
		
		Subset_Vertex v = new Subset_Vertex(this.index+1, copy);
		return v;
	}

	@Override
	public Subset_Edge edge(Integer a) {
		return Subset_Edge.of(this, this.neighbor(a), a);
	}
	
	//Auxiliary methods
	
		public Subset_Vertex copy() {
			Subset_Vertex v = new Subset_Vertex(this.index, this.sumPerSubset);
			return v;
		}
		
		public Integer[] takeOutSps() {
			Integer[] copy = Arrays2.copyArray(sumPerSubset);
			return copy;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((index == null) ? 0 : index.hashCode());
			result = prime * result + Arrays.hashCode(sumPerSubset);
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
			Subset_Vertex other = (Subset_Vertex) obj;
			if (index == null) {
				if (other.index != null)
					return false;
			} else if (!index.equals(other.index))
				return false;
			if (!Arrays.equals(sumPerSubset, other.sumPerSubset))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Subset_Vertex [index=" + index + ", sumPerSubset=" + Arrays.toString(sumPerSubset) + "]";
		}
}
