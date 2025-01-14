package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;

import us.lsi.common.Files2;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class Data_Road {
	
	public static Integer NA;
	public static Integer NV;
	public static List<Ciudad> Cities;
	public static List<Carretera> Roads;
	public static Predicate<Carretera> Predicate;
	public static Graph<Ciudad, Carretera> Graph;
	
	public static void iniDatas(String file, Predicate<Carretera> p) {
		
		Predicate = p;
		
		Graph<Ciudad, Carretera> graph = GraphsReader.newGraph(file, Ciudad::ofFormat, 
				Carretera::ofFormat, Graphs2::simpleWeightedGraph,
				Carretera::getKm);
		Graph = graph;
		
		Set<Ciudad> sc = graph.vertexSet();
		
		Cities = new ArrayList<Ciudad>(sc);
		
		Set<Carretera> sca = graph.edgeSet();
		
		Roads = new ArrayList<Carretera>(sca);
		NA = Roads.size();
		NV = Cities.size();
		
	}

	public static Integer getNA() {
		return NA;
	}

	public static Integer getNV() {
		return NV;
	}

	public static List<Ciudad> getCities() {
		return Cities;
	}

	public static List<Carretera> getRoads() {
		return Roads;
	}
	
	public static void toConsole() {
		System.out.println("cities: "+Cities);
		System.out.println("roads: "+Roads);
	}
	
	
	
}
