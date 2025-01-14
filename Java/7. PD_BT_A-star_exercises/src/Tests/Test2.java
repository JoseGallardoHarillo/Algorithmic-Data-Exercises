package Tests;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import graph.Lawyer_Edge;
import graph.Lawyer_Heuristic;
import graph.Lawyer_Vertex;
import graph.Product_Edge;
import graph.Product_Heuristic;
import graph.Product_Vertex;
import pack.Data_Lawyer;
import pack.Data_Product;
import pack.Solution_Lawyers;
import pack.Solution_Products;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.DPR;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.GraphAlg;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;

public class Test2 {
	
	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("en", "US"));
		
		for (int idFile = 1; idFile <= 2; idFile++) {
			
			Data_Lawyer.iniDatas("files/input_2_"+idFile+".txt");
			System.out.println("");
			System.out.println("Results of the test "+idFile+":");
			System.out.println("");
			
			//Key Vertex
			
			Lawyer_Vertex start = Lawyer_Vertex.initialVertex();
			Predicate<Lawyer_Vertex> finalVertex = v->Lawyer_Vertex.goal(v);
			
			//Grafo
			
			EGraph<Lawyer_Vertex, Lawyer_Edge> graph = Graphs2.
					simpleVirtualGraphLast(start, Lawyer_Vertex::getcMax);
			
			//A*
			
			System.out.println("A*");
			System.out.println("");
			
			
			AStar<Lawyer_Vertex, Lawyer_Edge> Astar = GraphAlg.
					aStarGoal(graph, finalVertex, Lawyer_Heuristic::heuristic);
			List<Integer> gpAS = Astar.search().get().getEdgeList().stream().
					map(x->x.action).collect(Collectors.toList());
			Solution_Lawyers sAS = new Solution_Lawyers(gpAS);
			System.out.println(sAS.toString());
			
			//PD
			
			System.out.println("");
			System.out.println("PD");
			System.out.println("");
			
			DynamicProgrammingReduction<Lawyer_Vertex, Lawyer_Edge> PD = DPR.
					dynamicProgrammingReductionGoal(graph, finalVertex, 
							Lawyer_Heuristic::heuristic, PDType.Min);
			
			List<Integer> gpPD = PD.search().get().getEdgeList().stream().
					map(x->x.action).collect(Collectors.toList());
			
			Solution_Lawyers sPD = new Solution_Lawyers(gpPD);
			
			System.out.println(sPD.toString());
		}
	}

}