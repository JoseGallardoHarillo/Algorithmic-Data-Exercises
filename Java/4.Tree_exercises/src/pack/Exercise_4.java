package pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

import us.lsi.tiposrecursivos.Tree;

public class Exercise_4 {
	
	public static <E> HashMap<Integer, List<E>> ex_4(Tree<E> tree) {
		
		HashMap<Integer, List<E>> dictionary = new HashMap<Integer, List<E>>();
		
		for(int k=0;k<=tree.getHeight();k++) {
		
			List<E> lk = new ArrayList<E>();
			dictionary.put(k, lk);
		}
		
		ex_4_aux(tree,dictionary,0);
		
		return dictionary;
	}
		
	public static <E> void ex_4_aux(Tree<E> tree, HashMap<Integer, List<E>> dictionary, Integer i) {
		
		switch(tree.getType()) {
			
			case Empty:
				break;
			case Leaf:
				break;
			case Nary:
			
				List<Tree<E>> lt = tree.getLevel(i);
			
				for(int j=0;j<lt.size();j++) {
					
					Tree<E> a = lt.get(j);
					
					if(a.getNumOfChildren()%2==0&&a.getNumOfChildren()!=0) {
						
						List<E> l = dictionary.get(i);
						l.add(a.getLabel());
						dictionary.put(i, l);
					}
				}
				
				if(i<tree.getHeight()) 
					
					ex_4_aux(tree, dictionary, i+1);
				
				break;
		}
	}
}