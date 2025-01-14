package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.GraphPath;

import pack.Student;
import pack.Data_Student;
import pack.Data_Student;
import pack.Data_Product;
import pack.Solution_Students;
import pack.Solution_Products;

import java.util.Map.Entry;

import us.lsi.common.Arrays2;
import us.lsi.common.Sets2;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class Student_Vertex extends ActionVirtualVertex<Student_Vertex, Student_Edge, Integer>{
	
	public Integer index;
	private Integer NS = Data_Student.NStudents;
	private Integer NG = Data_Student.NG;
	public Integer[] studentsPerGroup = new Integer[NG];
	private Integer meanStudents = NS/NG;
	
	public Student_Vertex(Integer index, Integer[] stpg){
		// TODO Auto-generated constructor stub
		this.index = index;
		this.studentsPerGroup = Arrays2.copyArray(stpg);
	}
	
	public static Student_Vertex initialVertex() {
		Integer [] stpg = new Integer[Data_Student.NG];
		Integer mean = Data_Student.NStudents/Data_Student.NG;
		for (int i = 0; i < stpg.length; i++) {
			stpg[i] = mean;
		}
		Student_Vertex v = new Student_Vertex(0, stpg);
		
		return v;
	}
	
	public static Boolean goal(Student_Vertex v) {
		return v.index==v.NS;
	}
	
	//Override methods
	
	@Override
	public Boolean isValid() {

		return this.index>=0 && this.index<=this.NS;
	}

	@Override
	public List<Integer> actions() {
		List<Integer> alternatives = new LinkedList<>();
		
		if(this.index==this.NS-1) {
			
			//We insert the alumn into the group which isn't full yet
			for (int i = 0; i < this.studentsPerGroup.length; i++) {
				if(this.studentsPerGroup[i]-1==0 && Data_Student.getAfinity(this.index, i)>0) {
					alternatives.add(i);
				}
			}
		}
		
		//Otherwise, respecting the index range, we add the alternatives which don't contain afinity 0 or involve to add a student into a full group
		
		else if(this.index>=0 && this.index<this.NS) {
			
			for (int i = 0; i < this.studentsPerGroup.length; i++) {
				if(this.studentsPerGroup[i]>0 && Data_Student.getAfinity(this.index, i)>0) {
					alternatives.add(i);
				}
			}
		}
		
		return alternatives;
	}

	@Override
	public Student_Vertex neighbor(Integer a) {
		Integer[] copy = Arrays2.copyArray(this.studentsPerGroup);
		//We reduce the available number of students into the selected group
		copy[a] = copy[a] - 1;
		Student_Vertex New = new Student_Vertex(this.index+1, copy);
		return New;
	}

	@Override
	public Student_Edge edge(Integer a) {
		return Student_Edge.of(this, this.neighbor(a),a);
	}
		
		//Auxiliary methods
		
	public Student_Vertex copy() {
		Student_Vertex v = new Student_Vertex(this.index, this.studentsPerGroup);
		return v;
		}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + Arrays.hashCode(studentsPerGroup);
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
		Student_Vertex other = (Student_Vertex) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (!Arrays.equals(studentsPerGroup, other.studentsPerGroup))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Student_Vertex [index=" + index + ", NS=" + NS + ", NG=" + NG + ", studentsPerGroup="
				+ Arrays.toString(studentsPerGroup) + ", meanStudents=" + meanStudents + "]";
	}


		
		

}