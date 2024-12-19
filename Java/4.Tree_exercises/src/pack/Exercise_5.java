package pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.Tree;

public class Exercise_5 {
	
	public static <E> HashMap<Integer, Set<Tree<E>>> ex_5(Tree<E> tree) {
	
		HashMap<Integer, Set<Tree<E>>> dictionary = new HashMap<Integer, Set<Tree<E>>>();
		
		ej5aux(tree, dictionary);
		
		return dictionary;
	}

	public static <E> void ej5aux(Tree<E> tree, HashMap<Integer, Set<Tree<E>>> dictionary) {
		
		switch(tree.getType()) {
			
			case Empty:	
				
				if(dictionary.containsKey(0)) {
					
					Set<Tree<E>> s = dictionary.get(0);
					s.add(tree);
					dictionary.put(0, s);
				}
				
				else {
					
					Set<Tree<E>> s = new HashSet<Tree<E>>();
					s.add(tree);
					dictionary.put(0, s);
				}
				
				break;
				
			case Leaf:
				
				if(dictionary.containsKey(0)) {
					
					Set<Tree<E>> s = dictionary.get(0);
					s.add(tree);
					dictionary.put(0, s);
				}
				
				else {
					
					Set<Tree<E>> s = new HashSet<Tree<E>>();
					s.add(tree);
					dictionary.put(0, s);
				}
				
				break;
		
			case Nary:
				
				Integer nh = tree.getNumOfChildren();
			
				if(dictionary.containsKey(nh)) {
					
					Set<Tree<E>> s = dictionary.get(nh);
					s.add(tree);
					dictionary.put(nh, s);
				}
				
				else {
					
					Set<Tree<E>> s = new HashSet<Tree<E>>();
					s.add(tree);
					dictionary.put(nh, s);
					}
			
				for(int j=0;j<nh;j++) {
					
					Tree<E> child = tree.getChild(j);
					ej5aux(child, dictionary);
				}
				
				break;
		}		
	}
}
