package pack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import us.lsi.common.Files2;
import us.lsi.common.Streams2;
import us.lsi.math.Math2;
import us.lsi.tiposrecursivos.BinaryTree;
import us.lsi.tiposrecursivos.Tree;

public class main {
	
	
	
	//File Reading 3, 4 and 5
	
		public static List<Tree<Integer>> read_f_3_4_5(String file) {
			
			List<String> ls = Files2.streamFromFile(file).collect(Collectors.toList());			

			List<Tree<Integer>> lt = new ArrayList<Tree<Integer>>();
				
			for(String row : ls) {
			
				Tree<String> tstr= Tree.parse(row);
				Tree<Integer> ti5 = parseTree(tstr, x->Integer.valueOf(x));
				
				lt.add(ti5);	
			}
			
			return lt;
		}
		
	//For testing 3
		
		public static <E> void forTesting_3(List<Tree<E>> lt,Predicate<E> p) {
			for(int i=0;i<lt.size();i++) {
				
				Tree<E> ti = lt.get(i);
				List<Boolean> r3 = Exercise_3.ex_3(ti, p);
				
				System.out.println("Input tree: "+ ti);
				System.out.println("Does it satisfy the predicate: " + r3);
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			}
		}
		
	//Tree<E>->Tree<R>
		
		public static <E,R> Tree<R> parseTree(Tree<E> a, Function<E, R> f){
			Tree<R> r = null;
			Tree<R> hr = null;
			
			switch (a.getType()) {
				case Empty: r = Tree.empty(); 
				break;
				
			case Leaf: r = Tree.leaf(f.apply(a.getLabel()));
				break;
			
			case Nary:
			
				List<Tree<E>> hA = a.getChildren();
				R lavel = f.apply(a.getLabel());
				List<Tree<R>> nhA = new ArrayList<Tree<R>>();
				int i = 0;
				
				while(i<hA.size()) {
				
					hr = parseTree(hA.get(i), f);
					nhA.add(hr);
					i++;
				}
				
				r = Tree.nary(lavel, nhA);
				
				break;
			}
			
			return r;
		}
	
	
	public static void main(String[] args) {
		
		//Test 1
		
		System.out.println("Exercise 1");
		System.out.println("");
		
		List<String> ls1 = Files2.streamFromFile("files/input_1.txt").collect(Collectors.toList());
		
		for(String row : ls1) {
			
			BinaryTree<Integer> bti = BinaryTree.parse(row, x->Integer.parseInt(x));
			Boolean r1 = Exercise_1.ex_1(bti); 
			
			System.out.println("Input tree: "+ bti);
			System.out.println("Does it satisfy the predicate of the statement? " + r1);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		}
		
		//Test 2
		
		System.out.println("");
		System.out.println("Exercise 2");
		System.out.println("");
		
		List<String> ls2 = Files2.streamFromFile("files/input_2.txt").collect(Collectors.toList());
		
		for(int i=0;i<ls2.size();i++) {
			String piece = ls2.get(i);
			String[] chunks = piece.split("#");
			
			BinaryTree<String> tree = BinaryTree.parse(chunks[0]);
			
			String sublist = chunks[1].substring(1, ((chunks[1].length())-1));
			String[] splittedSublist = sublist.split(",");
			List<String> l = new ArrayList<String>();
			
			for(int j=0;j<splittedSublist.length;j++) {
				l.add(splittedSublist[j]);
			}
			
			Boolean r2 = Exercise_2.ex_2(tree, l);
			
			System.out.println("Input tree: "+ tree);
			System.out.println("Does a path exist from the root for " + l+"? "+r2);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
		
		//Test 3
		
		System.out.println("Exercise 3");
		System.out.println("");
				
		List<Tree<Integer>> lt3 = read_f_3_4_5("files/input_3.txt");

		Predicate<Integer> p = x->Math2.esPar(x);
		Predicate<Integer> p2 = x->Math2.esPrimo(x);
		
		System.out.println("============================= Predicate \"is-par\" =============================");
		System.out.println("");
		
		forTesting_3(lt3, p);
		
		System.out.println("============================= Predicate \"is-prime\" =============================");
		System.out.println("");
		
		forTesting_3(lt3, p2);
			
		//Test 4
			
		System.out.println("Exercise 4");
		System.out.println("");
					
		List<Tree<Integer>> lt4 = read_f_3_4_5("files/input_4.txt");
			
		for(int j=0;j<lt4.size();j++) {
			
			Tree<Integer> ti4 = lt4.get(j);
			
			HashMap<Integer, List<Integer>> r4 = Exercise_4.ex_4(ti4);
			
			System.out.println("Input tree: "+ ti4);
			System.out.println("Output Map: " + r4);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
			
		//Test 5
				
		System.out.println("Exercise 5");
		System.out.println("");
					
		List<Tree<Integer>> lt5 = read_f_3_4_5("files/input_5.txt");
			
		for(int j=0;j<lt5.size();j++) {
	
			Tree<Integer> ti5 = lt5.get(j);
			
			HashMap<Integer, Set<Tree<Integer>>> r5 = Exercise_5.ex_5(ti5); 
			
			System.out.println("Input tree: "+ ti5);
			System.out.println("Output Map: " + r5);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}
}
