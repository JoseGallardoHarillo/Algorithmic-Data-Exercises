package pack;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.alg.vertexcover.GreedyVCImpl;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;


public class Exercise_1 {
	
	//a
	
	public static Set<User> ex_1_a_1(Graph<User, Friendship> graph){
		
		Set<User> res = new HashSet<User>();
		Set<User> users = graph.vertexSet();
		Iterator<User> iterator= users.iterator();

		while(iterator.hasNext()) {
			
			User u= iterator.next();
			Integer degree = graph.degreeOf(u);
			
			if(degree==0) 
				res.add(u);
		}
		
		return res;
	}
	
	public static Set<User> ex_1_a_2(Graph<User, Friendship> graph){
		
		Set<User> res = new HashSet<User>();
		
		Set<User> users = graph.vertexSet();
		Iterator<User> iterator= users.iterator();
		
		Integer MaxDegree = 0;
		
		while(iterator.hasNext()) {
			User u= iterator.next();
			
			Integer degree = graph.degreeOf(u);
			
			if(degree==MaxDegree)
				res.add(u);
			
			else if(degree>MaxDegree) {
				res.clear();
				res.add(u);
				MaxDegree = degree;
			}
		}
		
		return res;
	}
	
	//b
	
	public static List<User> ex_1_b(Graph<User, Friendship> graph,User u1,User u2){
			
		DijkstraShortestPath<User, Friendship> dijkstra = new DijkstraShortestPath<User, Friendship>(graph);
			
		GraphPath<User, Friendship> CM = dijkstra.getPath(u1, u2);
			
		return CM.getVertexList();
	}
		
	//c
		
	public static List<Set<User>> ex_1_c(Graph<User, Friendship> graph){
			
		ConnectivityInspector<User, Friendship> CI = new ConnectivityInspector<User, Friendship>(graph);
		
		return CI.connectedSets();
	}
	
	//d
		
	public static Set<User> ex_1_d(Graph<User, Friendship> graph){
			
		GreedyVCImpl<User, Friendship> GVC = new GreedyVCImpl<User, Friendship>(graph);
		
		return GVC.getVertexCover();
	}
}
