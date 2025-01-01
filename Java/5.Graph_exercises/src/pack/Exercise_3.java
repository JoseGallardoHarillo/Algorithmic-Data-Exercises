package pack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.TopologicalOrderIterator;

import us.lsi.graphs.SimpleEdge;

public class Exercise_3 {
	
	public static List<String> ex_3_a(Graph<String, SimpleEdge<String>> graph){
		
		List<String> ls = new ArrayList<String>();
		
		TopologicalOrderIterator<String, SimpleEdge<String>> TOI = new TopologicalOrderIterator<String, SimpleEdge<String>>(graph);
		
		TOI.forEachRemaining(v->ls.add(v));
		
		return ls;
	}
	
	public static List<String> ex_3_b(Graph<String, SimpleEdge<String>> graph){
		
		List<String> ls = new ArrayList<String>();
		
		TopologicalOrderIterator<String, SimpleEdge<String>> TOI = new TopologicalOrderIterator<String, SimpleEdge<String>>(graph);
		
		while(TOI.hasNext()) {
			String v = TOI.next();
			
			if(graph.inDegreeOf(v)==0)
				ls.add(v);
		}
		
		return ls;
	}
	
	public static Set<String> ex_3_c(Set<String> set,Graph<String, SimpleEdge<String>> graph){
		
		
		Set<String> candidates = new HashSet<String>();
		
		Set<String> r = new HashSet<String>();
		
		Iterator<String> it = set.iterator();
		
		while(it.hasNext()) {
			
			String subject = it.next();
			
			Set<SimpleEdge<String>> ca = graph.outgoingEdgesOf(subject);
			
			Iterator<SimpleEdge<String>> sit = ca.iterator();
			
			while(sit.hasNext()) {
				
				SimpleEdge<String> a = sit.next();
				String neightbor = a.getTarget();
				
				candidates.add(neightbor);
			}
		}
		
		Iterator<String> it2 = candidates.iterator();
		
		while(it2.hasNext()) {
			
			String c = it2.next();
			
			if(!set.contains(c)) {
				Set<SimpleEdge<String>> necessary = graph.incomingEdgesOf(c);
				Integer Nnecessary = graph.inDegreeOf(c);
				Iterator<SimpleEdge<String>> sit2 = necessary.iterator();
				
				Integer counter = 0;
				while(sit2.hasNext()) {
					
					SimpleEdge<String> a = sit2.next();
					String key = a.getSource();
					
					if(set.contains(key))
						counter++;
				}
				
				if(counter==Nnecessary)
					r.add(c);
			}	
		}
		
		return r;
	}
}