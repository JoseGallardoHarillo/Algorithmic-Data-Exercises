package graph;

	import java.util.Arrays;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.stream.IntStream;

import pack.Lawyer;
import pack.Student;
import pack.Data_Lawyer;
import pack.Data_Lawyer;
import us.lsi.common.Arrays2;
	import us.lsi.common.Lists2;
	import us.lsi.graphs.virtual.ActionVirtualVertex;
	import java.util.Arrays;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.LinkedList;
	import java.util.List;
	import java.util.stream.IntStream;

import us.lsi.common.Arrays2;
	import us.lsi.common.Lists2;
	import us.lsi.graphs.virtual.ActionVirtualVertex;

	public class Lawyer_Vertex extends ActionVirtualVertex<Lawyer_Vertex,Lawyer_Edge,Integer>{
		
		public Integer index;
		private Integer NLawyer = Data_Lawyer.NLawyer;
		public Integer[] hoursPerLawyer = new Integer[NLawyer];
		private Integer hMin;
		private Integer hMax;
		private Integer lwMin;
		private Integer lwMax;
		
		//Factorias
		
		public Lawyer_Vertex(Integer indice, Integer[] hpl) {
			this.index = indice;
			this.hoursPerLawyer = Arrays2.copyArray(hpl);
			this.lwMin = IntStream.range(0, NLawyer).boxed().
					min(Comparator.comparing(x->this.hoursPerLawyer[x])).get();
			this.hMin = this.hoursPerLawyer[lwMin];
			this.lwMax = IntStream.range(0, NLawyer).boxed().
					max(Comparator.comparing(x->this.hoursPerLawyer[x])).get();
			this.hMax = this.hoursPerLawyer[lwMax]; 
		}
		
		public static Lawyer_Vertex initialVertex() {
			Integer[] hpa = new Integer[Data_Lawyer.NLawyer];
			
			for (int i = 0; i < hpa.length; i++)
				hpa[i] = 0;
			
			Lawyer_Vertex v = new Lawyer_Vertex(0,hpa);
			
			return v;
		}
		
		
		public static boolean goal(Lawyer_Vertex v) {	
			return v.index == Data_Lawyer.NC;
		}

		//Metodos override
		
		@Override
		public Boolean isValid() {
			return this.index>=0 && this.index<=Data_Lawyer.NC;
		}

		@Override
		public List<Integer> actions() {
			
			List<Integer> alternatives = new LinkedList<>();
			 
			if(this.index==Data_Lawyer.NC-1) {
				Integer[] copy = Arrays2.copyArray(this.hoursPerLawyer);
				
				//We add on each lawyer's position the missing hour, 
				//putting on each alternative's situation

				for (int i = 0; i < this.hoursPerLawyer.length; i++)
					copy[i] = this.hoursPerLawyer[i] + Data_Lawyer.getHour(i, this.index); 
				
				//We keep the lawyer with the lowest number of hours
				for (int i = 0; i < Data_Lawyer.NLawyer; i++) {
					if(Collections.min(Arrays.asList(copy))==copy[i])
						alternatives.add(i);
				}
			}
			
			//Otherwise, we add all the possible alternatives
			//AS LONG AS the index range is respected
			
			else if(this.index>=0 && this.index<Data_Lawyer.NC)
				alternatives = Lists2.rangeList(0, Data_Lawyer.NLawyer);
			
			
			return alternatives;
		}

		@Override
		public Lawyer_Vertex neighbor(Integer a) {
			Integer[] copy = Arrays2.copyArray(this.hoursPerLawyer);
			
			copy[a] += Data_Lawyer.getHour(a, this.index);
			
			return new Lawyer_Vertex(this.index+1, copy);
		}

		@Override
		public Lawyer_Edge edge(Integer a) {
			return Lawyer_Edge.of(this, this.neighbor(a),a);
		}
		
		//Auxiliary methods
		
		public Lawyer_Vertex copy() {
			Lawyer_Vertex v = new Lawyer_Vertex(this.index, this.hoursPerLawyer);
			return v;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + Arrays.hashCode(hoursPerLawyer);
			result = prime * result + ((index == null) ? 0 : index.hashCode());
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
			Lawyer_Vertex other = (Lawyer_Vertex) obj;
			if (!Arrays.equals(hoursPerLawyer, other.hoursPerLawyer))
				return false;
			if (index == null) {
				if (other.index != null)
					return false;
			} else if (!index.equals(other.index))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Lawyer_Vertex [index=" + index + ", ca=" + Arrays.toString(hoursPerLawyer) + "]";
		}
		
		public Double getcMax() {
			return hMax*1.0;
		}
		public Double getcMin() {
			 return hMin*1.0;
		}
		 
		public Double getaMax() {
			return lwMax*1.0;
		}
		public Double getaMin() {
			return lwMin*1.0;
		}

	}