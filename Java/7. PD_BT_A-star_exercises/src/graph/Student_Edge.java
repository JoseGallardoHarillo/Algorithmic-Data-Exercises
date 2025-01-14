package graph;

import pack.Data_Student;
import pack.Data_Product;
import us.lsi.graphs.virtual.ActionSimpleEdge;

public class Student_Edge extends ActionSimpleEdge<Student_Vertex, Integer>{
	
	protected Student_Edge(Student_Vertex c1, Student_Vertex c2, Integer action) {
		super(c1, c2, action);
		this.weight = Data_Student.getAfinity(c1.index, action);
	}
	
	public static Student_Edge of(Student_Vertex v, Student_Vertex v2, Integer action) {
		
		final Student_Edge edge = new Student_Edge(v, v2, action);
		return edge;
	}
	
}
