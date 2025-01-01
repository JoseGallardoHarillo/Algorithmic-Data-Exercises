package pack;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.alg.color.GreedyColoring;

import us.lsi.graphs.SimpleEdge;

public class Exercise_2 {

	public static List<Set<String>> ex_2_a(Graph<String, SimpleEdge<String>> graph){
		
		GreedyColoring<String, SimpleEdge<String>> GC = new GreedyColoring<String, SimpleEdge<String>>(graph);
		
		List<Set<String>> r = GC.getColoring().getColorClasses();
		
		return r;
	}
}
