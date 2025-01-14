package Tests;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import graph.Product_Edge;
import graph.Product_Heuristic;
import graph.Product_Vertex;
import graph.Student_Edge;
import graph.Student_Heuristic;
import graph.Student_Vertex;
import pack.Data_Student;
import pack.Data_Student;
import pack.Data_Product;
import pack.Solution_Students;
import pack.Solution_Products;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.DPR;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.GraphAlg;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;

public class Test1 {
	
	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("en", "US"));
		
		for(int idFile=1;idFile<=3;idFile++) {
			
			Data_Student.iniDatas("files/input_1_"+idFile+".txt");
			System.out.println("");
			System.out.println("Results of the test "+idFile+":");
			System.out.println("");
			
			//Vertex Key
			
			Student_Vertex start = Student_Vertex.initialVertex();
			Predicate<Student_Vertex> finalVertex = v->Student_Vertex.goal(v);
			
			//Graph
			
			EGraph<Student_Vertex, Student_Edge> graph = Graphs2.
					simpleVirtualGraph(start, x->1/x.getEdgeWeight());
			
			
			//A*
			
			System.out.println("A*");
			System.out.println("");
			
			
			AStar<Student_Vertex, Student_Edge> Astar = GraphAlg.aStarGoal(graph, finalVertex, 
					Student_Heuristic::heuristic);
			List<Integer> gpAS = Astar.search().get().getEdgeList().stream().
					map(x->x.action).collect(Collectors.toList());
			Solution_Students sAS = new Solution_Students(gpAS);
			
			System.out.println(sAS.toString());
			
			//PD
			
			graph = Graphs2.simpleVirtualGraph(start, x->x.getEdgeWeight());
			
			System.out.println("");
			System.out.println("PD");
			System.out.println("");
			
			DynamicProgrammingReduction<Student_Vertex, Student_Edge> PD = DPR.
					dynamicProgrammingReductionGoal(graph, finalVertex, 
							Student_Heuristic::heuristic, PDType.Max);
			List<Integer> gpPD = PD.search().get().getEdgeList().stream().map(x->x.action).
					collect(Collectors.toList());
			Solution_Students sPD = new Solution_Students(gpPD);
			System.out.println(sPD.toString());
		}
	}
}
