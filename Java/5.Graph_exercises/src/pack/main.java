package pack;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.jgrapht.Graph;


import us.lsi.colors.GraphColors;
import us.lsi.common.Files2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;
import us.lsi.graphs.SimpleEdge;

public class main {
	
	public static void main(String[] args) {
		
		//test 1
		
		
		Graph<User,Friendship> graph =  
				GraphsReader.newGraph("files/input_1.txt",
						User::ofFormat, 
						Friendship::ofFormat,
						Graphs2::simpleGraph);
		
		//1a
		
		Set<User> withoutFriends = Exercise_1.ex_1_a_1(graph);
		Set<User> popular = Exercise_1.ex_1_a_2(graph);
		Iterator<User> itP = popular.iterator();
		Integer gradoMax = graph.degreeOf(itP.next());
		
		System.out.println("Exercise 1:");
		System.out.println("");
		System.out.println("a)");
		System.out.println("");
		
		System.out.println("Members with "+0+" friends are: "+ withoutFriends);
		System.out.println("Members with "+gradoMax+" friends are: "+ popular);
		
		Graphs2.toDot(graph, "files/graph_1_a.gv",
				v->v.toString()+"("+graph.degreeOf(v)+" amigos)",e -> e.toString(),
				v-> GraphColors.getColor(withoutFriends.contains(v)?3:(popular.contains(v)?1:8)),e->GraphColors.getColor(8));
		
		//1b
		
		User u1 = User.ofName("Juan");
		User u2 = User.ofName("Ramiro");
		
		List<User> r1b = Exercise_1.ex_1_b(graph, u1, u2);
		System.out.println("");
		System.out.println("b)");
		System.out.println("");
		
		System.out.println("The shortest list between "+u1+" y "+u2+" is: "+r1b);
		
		Graphs2.toDot(graph, "files/graph_1_b.gv",
				v->v.toString()+"("+graph.degreeOf(v)+" friends)",e -> e.toString(),
				
				v-> GraphColors.getColor(r1b.contains(v)?7:3),e->GraphColors.
				getColor(r1b.contains(e.getSource())&&r1b.contains(e.getTarget())?7:3));
		
		//1c
		
		List<Set<User>> r1c = Exercise_1.ex_1_c(graph);
		
		System.out.println("");
		System.out.println("c)");
		System.out.println("");
	
		System.out.println(r1c.size()+" groups exist. The composition is:");
		
		Map<User, Integer> groupDictionary = new HashMap<User, Integer>();
		
		for(int i=0;i<r1c.size();i++) {
			System.out.println("");
			Set<User> group = r1c.get(i);
			
			String color =GraphColors.getColor(i+1).values().toString()
					.substring(1, GraphColors.getColor(i+1).values().toString().length()-1);
			
			if(group.size()<=1)
				System.out.println("Group "+color+" ("+group.size()+" User):");
			
			else
				System.out.println("Group "+color+" ("+group.size()+" Users):");
			
			Iterator<User> itu = group.iterator();
			
			while(itu.hasNext()) {
				
				User u = itu.next();
				
				groupDictionary.put(u, i+1);
			}
			
			System.out.println(group);
		}
		
		Graphs2.toDot(graph, "files/graph_1_c.gv",
				v->v.toString(),e -> e.toString(),
				v-> GraphColors.getColor(groupDictionary.get(v)),e->GraphColors.getColor(groupDictionary.get(e.getSource())));
		
		//1d
		
		Set<User> rd = Exercise_1.ex_1_d(graph);
		
		System.out.println("");
		System.out.println("d)");
		System.out.println("");
		
		System.out.println(rd.size()+" questionnaires will be sent to the following members: "+rd);
		
		Graphs2.toDot(graph, "files/graph_1_d.gv",
				v->v.toString(),e -> e.toString(),
				v-> GraphColors.getColor(rd.contains(v)?7:3),e->GraphColors.getColor(3));
		
		//test 2
		
		Graph<String, SimpleEdge<String>> graph_2 = Graphs2.simpleGraph();
		
		for(String row:Files2.linesFromFile("files/input_2.txt")) {
			
			String line = row.substring(8);
			String[] split = line.split(", ");
			
			for(int i=0;i<split.length;i++) {
				
				String piece = split[i];
				
				if(i!=split.length-1) {
					
					String nextPiece = split[i+1];
					
					if(!graph_2.containsVertex(piece))
						graph_2.addVertex(piece);
					
					if(!graph_2.containsVertex(nextPiece))
						graph_2.addVertex(nextPiece);
					
					graph_2.addEdge(piece, nextPiece,SimpleEdge.of(piece,nextPiece));
				}
				
				else {
					String firstPiece = split[0];
					
					if(!graph_2.containsVertex(piece))
						graph_2.addVertex(piece);
					
					graph_2.addEdge(piece, firstPiece,SimpleEdge.of(piece,firstPiece));
				}
			}
		}
		
		//2a-2b
		
		List<Set<String>> r2a = Exercise_2.ex_2_a(graph_2);
		
		System.out.println("");
		System.out.println("Exercise 2:");
		System.out.println("");
		System.out.println("a)");
		System.out.println("");
		
		System.out.println("Number of time slots needed: "+r2a.size());
		

		Map<String, Integer> groupDictionary_2 = new HashMap<String, Integer>();
		
		for(int i=0;i<r2a.size();i++) {
			
			Set<String> groupSet = r2a.get(i);
		
			System.out.println("Franja nº "+i+": "+groupSet);
			
			Iterator<String> itg= groupSet.iterator();
			
			while(itg.hasNext())
				groupDictionary_2.put(itg.next(), i+1);
		}
		
		Graphs2.toDot(graph_2, "files/graph_2.gv",
				v->v.toString(),e->"",v-> GraphColors.getColor(groupDictionary_2.get(v)),e->GraphColors.getColor(8));

		//test 3
		
		Graph<String, SimpleEdge<String>> graph_3= Graphs2.simpleDirectedWeightedGraph();
		
		for(String row:Files2.linesFromFile("files/input_3.txt")) {
			
			String[] split = row.split(": ");
			String subject = split[0];
			
			graph_3.addVertex(subject);
			
			String neightbourSubjects = split[1];
			String avPolished = neightbourSubjects.substring(1, neightbourSubjects.length()-1);
			String[] splitAV = avPolished.split(",");
			
			for(int i=0;i<splitAV.length;i++) {
				String av = splitAV[i];
				if(!av.isEmpty()) {
					graph_3.addEdge(av, subject, SimpleEdge.of(av, subject));	
				}
			}
		}
		
		//3a
		
		List<String> r3a = Exercise_3.ex_3_a(graph_3);
		
		
		System.out.println("");
		System.out.println("Exercise 3:");
		System.out.println("");
		System.out.println("a)");
		System.out.println("");
		
		System.out.println("One of the possible valid arrangements is as follows:");
		
		for(int i=0;i<r3a.size();i++)
			System.out.println(r3a.get(i));
		
		//3b
		
		List<String> r3b = Exercise_3.ex_3_b(graph_3);
		
		System.out.println("");
		System.out.println("b)");
		System.out.println("");
		
		System.out.println("The subjects that do not require passing another one first are:");
		
		for(int i=0;i<r3b.size();i++)
			System.out.println(r3a.get(i));
		
		Graphs2.toDot(graph_3, "files/graph_3_b.gv",
				v->v.toString(),e->"",v->GraphColors.getColor(r3b.contains(v)?7:8),e->GraphColors.getColor(8));
		
		//3c
		
		Set<String> set_1 = Set.of("Asignatura_01", "Asignatura_02", "Asignatura_03", "Asignatura_04", "Asignatura_05");
		Set<String> set_2 = Set.of("Asignatura_01", "Asignatura_02", "Asignatura_03", "Asignatura_04",
				"Asignatura_05", "Asignatura_06", "Asignatura_07", "Asignatura_08", "Asignatura_11");
		
		Set<String> r3c1 = Exercise_3.ex_3_c(set_1, graph_3);
		Set<String> r3c2 = Exercise_3.ex_3_c(set_2, graph_3);
		
		System.out.println("");
		System.out.println("c)");
		System.out.println("");
		
		System.out.println("Test 1 – The student can take the following subjects:");
		System.out.println(r3c1);
		System.out.println("");
		
		System.out.println("Test 2 – The student can take the following subjects:");
		System.out.println(r3c2);
		
		Graphs2.toDot(graph_3, "files/graph_3_c_1.gv",
				v->v.toString(),e->"",v->GraphColors.getColor(set_1.contains(v)?7:r3c1.contains(v)?2:8),e->GraphColors.getColor(8));
		
		Graphs2.toDot(graph_3, "files/graph_3_c_2.gv",
				v->v.toString(),e->"",v->GraphColors.getColor(set_2.contains(v)?7:r3c2.contains(v)?2:8),e->GraphColors.getColor(8));
	}

}
