package pack;

import java.util.function.Function;

import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.TreeImpl;

public class Exercise_1 {

	public static Boolean ex_1(BinaryTree<Integer> tree) {
		Boolean r=true;
			
		switch(tree.getType()) {
			case Empty: break;
			case Leaf: break;
			case Binary:
			
				if(tree.getLabel()==tree.getLeft().getLabel()+tree.getRight().getLabel())
					r= ex_1(tree.getLeft()) && ex_1(tree.getRight());
			
				else r=false;	
		}
		
		return r;
	}	
}