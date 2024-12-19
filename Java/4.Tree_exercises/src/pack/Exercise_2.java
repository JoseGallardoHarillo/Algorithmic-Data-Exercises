package pack;

import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;
import us.lsi.tiposrecursivos.BinaryTree;

public class Exercise_2 {
	
	public static Boolean ex_2(BinaryTree<String> tree, List<String> l) {
		return ex_2_aux(tree, l, 0, false);
	}
	
	public static Boolean ex_2_aux(BinaryTree<String> tree, List<String> l, Integer i, Boolean r) {
		
		if(i>=l.size()-1) i=0;
		
		String character = l.get(i);
		String nextChara = l.get(i+1);
		
		if(r!=true) {
			switch(tree.getType()) {
				
				case Empty: break;
				case Leaf: 
			
					if(tree.getLabel().equals(l.get(l.size()-1))) r=true;
					
					else r=false;
			
					break;
				
				case Binary:
			
					if(tree.getLabel().equals(character)) {
						
						if(tree.getLeft().getLabel().equals(nextChara)) 
							r= ex_2_aux(tree.getLeft(),l,i+1,false);
						
						
						if(tree.getRight().getLabel().equals(nextChara)) 
						r= ex_2_aux(tree.getRight(),l,i+1,false);
				
					}
			
				else r=false;
			
				break;
			}
		}
		return r;
	}
}
