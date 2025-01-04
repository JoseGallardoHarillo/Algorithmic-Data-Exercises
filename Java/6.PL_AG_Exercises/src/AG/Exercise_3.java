package AG;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pack.Data_Product;
import pack.Product;
import pack.Solution_Students;
import pack.Solution_Products;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ValuesInRangeChromosome;

public class Exercise_3 implements ValuesInRangeProblemAG<Integer, Solution_Products>{
	
	public static Exercise_3 create(String file) {
		return new Exercise_3(file);
	}
	
	private Exercise_3(String file) {
		Data_Product.iniDatas(file);
		//Data_Product.toConsole();
	}
	
	@Override
	public ChromosomeType getType() {
		// TODO Auto-generated method stub
		return ChromosomeType.Binary;
	}

	@Override
	public Integer getCellsNumber() {
		// TODO Auto-generated method stub
		return Data_Product.getNP();
	}

	@Override
	public Integer getMax(Integer i) {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public Integer getMin(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		List<Integer> lcr = cr.decode();
		
		Double goal = 0.0;
		
		Set<String> selection = new HashSet<String>();
		List<String> WF = Data_Product.getWishedFuncionalities();
		
		for(int i=0;i<lcr.size();i++) {
			
			if(lcr.get(i)==1) {
				Product p = Data_Product.getProduct(i);
				goal-= p.getValue();
				selection.addAll(p.getFunctionalities());
			}
		}
		
		for(int i=0;i<WF.size();i++) {
			
			String fd = WF.get(i);
			
			if(!selection.contains(fd)) {
				goal-=10000;
			}
		}
		
		
		return goal;
	}

	@Override
	public Solution_Products getSolucion(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		return new Solution_Products(cr.decode());
	}
	
	public static void main(String[] args) {
		AlgoritmoAG.ELITISM_RATE = 0.3;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 150;
		
		StoppingConditionFactory.NUM_GENERATIONS = 150;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 0;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.SolutionsNumber;
		
		System.out.println("Exercise 3:");
		System.out.println("");
		System.out.println("file 1");
		ValuesInRangeProblemAG<Integer,Solution_Products> ex_3_1 = Exercise_3.create("files/input_3_1.txt");
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> alg1 = AlgoritmoAG.create(ex_3_1);
		alg1.ejecuta();
		System.out.println(ex_3_1.getSolucion(alg1.getBestChromosome()).toString());
		
		System.out.println("");
		System.out.println("file 2");
		ValuesInRangeProblemAG<Integer,Solution_Products> ex_3_2 = Exercise_3.create("files/input_3_2.txt");
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> alg2 = AlgoritmoAG.create(ex_3_2);
		alg2.ejecuta();
		System.out.println(ex_3_2.getSolucion(alg2.getBestChromosome()).toString());
		
		System.out.println("");
		System.out.println("file 3");
		ValuesInRangeProblemAG<Integer,Solution_Products> ex_3_3 = Exercise_3.create("files/input_3_3.txt");
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> alg3 = AlgoritmoAG.create(ex_3_3);
		alg3.ejecuta();
		System.out.println(ex_3_3.getSolucion(alg3.getBestChromosome()).toString());
	}
}
