package AG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.Graph;

import pack.Data_Student;
import pack.Data_Road;
import us.lsi.ag.SeqNormalProblemAG;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.common.Sets2;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.SeqNomalChromosome;
import us.lsi.ag.agchromosomes.ValuesInRangeChromosome;
import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;
import us.lsi.reinas.datos.Reina;

public class Exercise_5 implements SeqNormalProblemAG<List<Ciudad>>{
	
	public static Exercise_5 create(String file,Predicate<Carretera> p) {
		return new Exercise_5(file,p);
	}
	
	private Exercise_5(String file,Predicate<Carretera> p) {
		Data_Road.iniDatas(file,p);
		//Data_Road.toConsole();
	}
	
	@Override
	public ChromosomeType getType() {
		// TODO Auto-generated method stub
		return ChromosomeType.Permutation;
	}

	@Override
	public Integer getIndexNumber() {
		// TODO Auto-generated method stub
		return Data_Road.NV;
	}

	@Override
	public Double fitnessFunction(SeqNomalChromosome cr) {
		// TODO Auto-generated method stub
		
		List<Integer> ls = cr.decode();
		Double goal = 0.0;
		Predicate<Carretera> p = Data_Road.Predicate;
		Graph<Ciudad, Carretera> g = Data_Road.Grafo;
		Integer CInonexistent = 0;
		Boolean check = false;
		Double cost = 0.0;
		
		for(int i=0;i<ls.size();i++) {
			Ciudad c = Data_Road.getCities().get(ls.get(i));
			Ciudad nextCity = null;
			if(i!=ls.size()-1) {
				nextCity = Data_Road.getCities().get(ls.get(i+1));
			}
			
			else {
				nextCity = Data_Road.getCities().get(ls.get(0));
			}
			
			if(!g.containsEdge(c, nextCity)) {
				CInonexistent+=1;
			}
			
			else {
				Carretera ca = g.getEdge(c, nextCity);
				Double km = ca.getKm();
				
				if(p.test(ca)==true) {
					check = true;
				}
				
				cost+=km;
			}
		}
		
		goal-=cost;
		
		if(check==false) {
			goal-=50000;
		}
		
		goal-= CInonexistent*10000;
		
		return goal;
	}

	@Override
	public List<Ciudad> getSolucion(SeqNomalChromosome cr) {
		// TODO Auto-generated method stub
		
		List<Integer> ls = cr.decode();
		List<Ciudad> lc = new ArrayList<Ciudad>();
		
		for(int i=0;i<ls.size();i++) {
			Ciudad c = Data_Road.getCities().get(ls.get(i));
			lc.add(c);
		}
		
		return lc;
	}
	
	public static String presentacion(List<Ciudad> lc) {
		
		Graph<Ciudad, Carretera> g = Data_Road.Graph;
		Boolean isCicle = false;
		Double totalCost = 0.0;
		
		for(int i=0;i<lc.size();i++) {
			
			Carretera ca;
			
			if(i!= lc.size()-1) {
				ca = g.getEdge(lc.get(i), lc.get(i+1));
				totalCost+=ca.getKm();
			}
			
			else {
				
				if(g.containsEdge(lc.get(i), lc.get(0))) {
					ca = g.getEdge(lc.get(i), lc.get(0));
					totalCost+=ca.getKm();
					isCicle = true;
				}
			}
		}
		
		if(isCicle==true) {
			lc.add(lc.get(0));
		}
		
		return "Proposed path: "+lc+"\nTotal cost: "+String.format("%.2f", totalCost)+" kms";
	}
	
	
	public static void main(String[] args) {
		AlgoritmoAG.ELITISM_RATE = 0.3;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 300;
		
		StoppingConditionFactory.NUM_GENERATIONS = 300;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 400000000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.SolutionsNumber;
		
		Predicate<Carretera> p1 = x->x.getKm()>200;
		Predicate<Carretera> p2 = x->x.getKm()<100;
		
		System.out.println("Exercise 5:");
		System.out.println("");
		System.out.println("file 1");
		System.out.println("");
		System.out.println("Predicate Roads over 200 km:");
		SeqNormalProblemAG<List<Ciudad>> ex_5_1_1 = Exercise_5.create("files/input_5_1.txt",p1);
		AlgoritmoAG<SeqNomalChromosome> alg11 = AlgoritmoAG.<SeqNomalChromosome>create(ex_5_1_1);
		alg11.ejecuta();
		List<Ciudad> lc11 = ex_5_1_1.getSolucion(alg11.getBestChromosome());
		System.out.println(presentacion(lc11));
		System.out.println("");
		
		System.out.println("Predicate Roads under 200 km:");
		SeqNormalProblemAG<List<Ciudad>> ex_5_1_2 = Exercise_5.create("files/input_5_1.txt",p2);
		AlgoritmoAG<SeqNomalChromosome> alg12 = AlgoritmoAG.<SeqNomalChromosome>create(ex_5_1_2);
		alg12.ejecuta();
		List<Ciudad> lc12 = ex_5_1_2.getSolucion(alg12.getBestChromosome());
		System.out.println(presentacion(lc12));
		System.out.println("");
		
		System.out.println("file 2");
		System.out.println("");
		System.out.println("Predicate Roads over 200 km:");
		SeqNormalProblemAG<List<Ciudad>> ex_5_2_1 = Exercise_5.create("files/input_5_2.txt",p1);
		AlgoritmoAG<SeqNomalChromosome> alg21 = AlgoritmoAG.<SeqNomalChromosome>create(problema21);
		alg21.ejecuta();
		List<Ciudad> lc21 = ex_5_2_1.getSolucion(alg21.getBestChromosome());
		System.out.println(presentacion(lc21));
		System.out.println("");
		
		System.out.println("Predicate Roads under 200 km:");
		SeqNormalProblemAG<List<Ciudad>> ex_5_2_2 = Exercise_5.create("files/input_5_2.txt",p2);
		AlgoritmoAG<SeqNomalChromosome> alg22 = AlgoritmoAG.<SeqNomalChromosome>create(ex_5_2_2);
		alg22.ejecuta();
		List<Ciudad> lc22 = ex_5_2_2.getSolucion(alg22.getBestChromosome());
		System.out.println(presentacion(lc22));
		System.out.println("");
		
		System.out.println("file 3");
		System.out.println("");
		System.out.println("Predicate Roads over 200 km:");
		SeqNormalProblemAG<List<Ciudad>> ex_5_3_1 = Exercise_5.create("files/input_5_3.txt",p1);
		AlgoritmoAG<SeqNomalChromosome> alg31 = AlgoritmoAG.<SeqNomalChromosome>create(ex_5_3_1);
		alg31.ejecuta();
		List<Ciudad> lc31 = ex_5_3_1.getSolucion(alg31.getBestChromosome());
		System.out.println(presentacion(lc31));
		System.out.println("");
		
		System.out.println("Predicate Roads under 200 km:");
		SeqNormalProblemAG<List<Ciudad>> ex_5_3_2 = Exercise_5.create("files/input_5_3.txt",p2);
		AlgoritmoAG<SeqNomalChromosome> alg32 = AlgoritmoAG.<SeqNomalChromosome>create(ex_5_3_2);
		alg32.ejecuta();
		List<Ciudad> lc32 = ex_5_3_2.getSolucion(alg32.getBestChromosome());
		System.out.println(presentacion(lc32));
		System.out.println("");
	}
}
