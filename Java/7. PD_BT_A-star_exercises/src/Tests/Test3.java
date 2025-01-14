package Tests;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.jgrapht.Graphs;
import org.jgrapht.alg.flow.DinicMFImpl;

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
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.GraphAlg;
import us.lsi.graphs.virtual.EGraph;

public class Test3 {
	
	public static void main(String[] args) {
		
		Locale.setDefault(new Locale("en", "US"));
		
		for(int idFile =1;idFile<=3;idFile++) {
			
			Data_Product.iniDatas("files/input_3_"+idFile+".txt");
			System.out.println("");
			System.out.println("Results of the test "+idFile+":");
			System.out.println("");
			
			//Key Vertex
			
			Product_Vertex start = Product_Vertex.initialVertex();
			Predicate<Product_Vertex> finalVertex = v->Product_Vertex.goal(v);
			
			//Graph
			
			EGraph<Product_Vertex, Product_Edge> graph = Graphs2.
					simpleVirtualGraph(start, x->x.getEdgeWeight());
			
			//A*
			
			System.out.println("A*");
			System.out.println("");
			
			
			AStar<Product_Vertex, Product_Edge> Astar = GraphAlg.aStarGoal(graph, finalVertex, 
					Product_Heuristic::heuristic);
			
			List<Integer> gpAS = Astar.search().get().getEdgeList().stream().map(x->x.alternative).
					collect(Collectors.toList());
			
			Solution_Products sAS =new Solution_Products(gpAS);
			System.out.println(sAS.toString());
			
			//PD
			
			System.out.println("");
			System.out.println("PD");
			System.out.println("");
			
			DynamicProgrammingReduction<Product_Vertex, Product_Edge> PD = DPR.
					dynamicProgrammingReductionGoal(graph, finalVertex, Product_Heuristic::heuristic, 
							PDType.Min);
			List<Integer> gpPD = PD.search().get().getEdgeList().stream().map(x->x.alternative).
					collect(Collectors.toList());
			
			Solution_Products sPD = new Solution_Products(gpPD);
			
			System.out.println(sPD.toString());
		}
	}

}
