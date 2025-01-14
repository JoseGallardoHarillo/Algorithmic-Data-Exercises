package Tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import graph.Lawyer_Edge;
import graph.Lawyer_Heuristic;
import graph.Lawyer_Vertex;
import graph.Product_Edge;
import graph.Product_Heuristic;
import graph.Product_Vertex;
import graph.Subset_Edge;
import graph.Subset_Heuristic;
import graph.Subset_Vertex;
import pack.Data_Lawyer;
import pack.Data_Product;
import pack.Data_Subset;
import pack.Solution_Lawyers;
import pack.Solution_Products;
import pack.Solution_Subset;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.alg.AStar;
import us.lsi.graphs.alg.DPR;
import us.lsi.graphs.alg.DynamicProgrammingReduction;
import us.lsi.graphs.alg.GraphAlg;
import us.lsi.graphs.alg.DynamicProgramming.PDType;
import us.lsi.graphs.virtual.EGraph;

public class Test4 {
	
public static void main(String[] args) {
		
		Locale.setDefault(new Locale("en", "US"));
		
		for(int idS=0;idS<=4;idS++) {
			
			if(idS!=1) {
				
			Data_Subset.iniDatas("files/input_4_.txt",idS);
			
			System.out.println("");
			System.out.println("Results of the subset"+idS+" :");
			System.out.println("");
			
			//Key Vertex
			
			Subset_Vertex start = Subset_Vertex.initialVertex();
			Predicate<Subset_Vertex> finalVertex = v->Subset_Vertex.goal(v);
			
			//Grafo
			
			EGraph<Subset_Vertex, Subset_Edge> grafo = Graphs2.
					simpleVirtualGraph(start, x->x.getEdgeWeight());
			
			//A*
			
			System.out.println("A*");
			System.out.println("");
			
			AStar<Subset_Vertex, Subset_Edge> Astar = GraphAlg.
					aStarGoal(grafo, finalVertex, Subset_Heuristic::heuristic);
			List<Integer> gpAS = Astar.search().get().getEdgeList().stream().map(x->x.action).
					collect(Collectors.toList());
			Solution_Subset sAS = new Solution_Subset(gpAS);
			System.out.println(sAS.toString());
			
			//PD
			
			System.out.println("");
			System.out.println("PD");
			System.out.println("");
			
			DynamicProgrammingReduction<Subset_Vertex, Subset_Edge> PD = DPR.
					dynamicProgrammingReductionGoal(grafo, finalVertex, Subset_Heuristic::heuristic,
							PDType.Min);
			
			List<Integer> gpPD = PD.search().get().getEdgeList().stream().map(x->x.action).
					collect(Collectors.toList());
			
			Solution_Subset sPD = new Solution_Subset(gpPD);
			System.out.println(sPD.toString());
			}
		}
	}
}
