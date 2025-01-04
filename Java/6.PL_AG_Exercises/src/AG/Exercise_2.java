package AG;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import pack.Data_Lawyer;
import pack.Data_Student;
import pack.Solution_Lawyers;
import pack.Solution_Students;

import java.util.Set;

import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ValuesInRangeChromosome;

public class Exercise_2 implements ValuesInRangeProblemAG<Integer, Solution_Lawyers>{

	public static Exercise_2 create(String file) {
		return new Exercise_2(file);
	}
	
	private Exercise_2(String file) {
		Data_Lawyer.iniDatas(file);
		//Data_Lawyer.toconsole();
	}
	
	@Override
	public ChromosomeType getType() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Integer getCellsNumber() {
		// TODO Auto-generated method stub
		return Data_Lawyer.NC;
	}

	@Override
	public Integer getMax(Integer i) {
		// TODO Auto-generated method stub
		return 	Data_Lawyer.NLawyer;
	}

	@Override
	public Integer getMin(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		
		List<Integer> ls = cr.decode();
		Double goal = 0.0;
		Map<Integer, List<Integer>> casesPerLawyers = new HashMap<Integer, List<Integer>>(); //example: lawyer 0=[1,4,5] cases
		Integer spentHours = 0;
		Integer totalHoursParallel = 0;
		Integer totalHours= 0;
		Set<Integer> selected = new HashSet<Integer>();
		Integer repeated = 0;
		
		for(int i=0;i<ls.size();i++) {
			List<Integer> cases = new ArrayList<>();
			Integer lawyer = ls.get(i);
			Integer hour =Data_Lawyer.getHour(lawyer, i+1);
			goal-=hour*500;
			
			if(casesPerLawyers.containsKey(lawyer)) {
				casesPerLawyers.get(lawyer).add(i+1);
			}
			
			else {
				cases.add(i+1);
				casesPerLawyers.put(lawyer, cases);
			}	
		}
		
		for(Entry<Integer,List<Integer>> ca: casesPerLawyers.entrySet()) {
			List<Integer> lv = ca.getValue();
			List<Integer> lh = new ArrayList<Integer>();
			
			for(int i=0;i<lv.size();i++) {
				Integer c = lv.get(i);
				Integer h = Data_Lawyer.getHour(ca.getKey(), c);
				
				if(selected.contains(c)) {
					repeated+=1; 
				}
				
				else {
					selected.add(c);
				}
				lh.add(h);
			}
			
			spentHours = lh.stream().mapToInt(Integer::intValue).sum();
			if(totalHoursParallel<spentHours) {
				totalHoursParallel = spentHours;
			}
			totalHours += spentHours; 
		}
		
		goal-=totalHoursParallel*500+totalHours*500;
		goal-=repeated*100000;
		
		if(casesPerLawyers.size()!=Data_Lawyer.NLawyer) {
			goal-=(Data_Lawyer.NLawyer-casesPerLawyers.size())*1000000;
		}
		return goal;
	}

	@Override
	public Solution_Lawyers getSolucion(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		return new Solution_Lawyers(cr.decode());
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
		
		System.out.println("Exercise 2:");
		System.out.println("");
		System.out.println("file 1");
		ValuesInRangeProblemAG<Integer,Solution_Lawyers> ex_2_1 = Exercise_2.create("files/input_2_1.txt");
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> alg1 = AlgoritmoAG.create(ex_2_1);
		alg1.ejecuta();
		System.out.println(ex_2_1.getSolucion(alg1.getBestChromosome()).toString());
		
		System.out.println("");
		System.out.println("file 2");
		ValuesInRangeProblemAG<Integer,Solution_Lawyers> ex_2_2 = Exercise_2.create("files/input_2_2.txt");
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> alg2 = AlgoritmoAG.create(ex_2_2);
		alg2.ejecuta();
		System.out.println(ex_2_2.getSolucion(alg2.getBestChromosome()).toString());
		
		System.out.println("");
		System.out.println("file 3");
		ValuesInRangeProblemAG<Integer,Solution_Lawyers> ex_2_3 = Exercise_2.create("files/input_2_3.txt");
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> alg3 = AlgoritmoAG.create(ex_2_3);
		alg3.ejecuta();
		System.out.println(ex_2_3.getSolucion(alg3.getBestChromosome()).toString());
	}
}
