package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import us.lsi.tiposrecursivos.Tree;


public class Exercise_3 {
	
	public static <E> List<Boolean> ex_3(Tree<E> tree, Predicate<E> p) {
		
		List<Boolean> l = new ArrayList<Boolean>();
		
		Exercise_3.ex_3_aux(tree, p, l,0,true); 
		
		return l;
	}
	

	
	public static <E> void ex_3_aux(Tree<E> tree, Predicate<E> p, List<Boolean> l,Integer i, Boolean r){
		
		Boolean r0 = r;
		
		switch(tree.getType()) {
		
			case Empty:
				break;
			
			case Leaf:
				break;
		
			case Nary:
				
				List<Tree<E>> lt = tree.getLevel(i);
			
				for(int j=0;j<lt.size();j++) {
				
					Tree<E> a = lt.get(j);
				
					if(!a.isEmpty())
						r = (p.test(a.getLabel()))&&r;
						
				}
			
				l.add(r);
			
				if(i<tree.getHeight())
					ex_3_aux(tree, p, l, i+1, r0);
				
				break;
		}
	}	
}
