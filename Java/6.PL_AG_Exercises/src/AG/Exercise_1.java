package AG;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import pack.Data_Student;
import pack.Solution_Students;
import us.lsi.ag.ValuesInRangeProblemAG;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.ag.agchromosomes.ValuesInRangeChromosome;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.ag.agstopping.StoppingConditionFactory.StoppingConditionType;

public class Exercise_1 implements ValuesInRangeProblemAG<Integer, Solution_Students>{
	
	public static Exercise_1 create(String file) {
		return new Exercise_1(file);
	}
	
	private Exercise_1(String file) {
		Data_Student.iniDatas(file);
		//Data_Student.toconsole();
	}
	
	
	@Override
	public ChromosomeType getType() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Integer getCellsNumber() {
		// TODO Auto-generated method stub
		return Data_Student.getNStudents();
	}

	@Override
	public Integer getMax(Integer i) {
		// TODO Auto-generated method stub
		return Data_Student.getNG()+1;
	}

	@Override
	public Integer getMin(Integer i) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Double fitnessFunction(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		
		List<Integer> ls = cr.decode();
		Double objetivo = 0.0;
		Integer studentsWithoutInterest = 0;
	
		Map<Integer, Integer> studentsInGroups = new HashMap<Integer, Integer>(); //example:group 1=3 students
		Integer studentsPerClass = Data_Student.getNStudents()/Data_Student.getNG(); //students mean
		
		
		for(int i=0;i<ls.size();i++) {
			Integer group = ls.get(i);
			Double afinity = Data_Student.getAfinity(i, group);
			
			objetivo += afinity*500;
			
			if(afinity==0) {
				
				studentsWithoutInterest+=1;
			}
			
			if(studentsInGroups.containsKey(group)) {
				studentsInGroups.put(group, studentsInGroups.get(group)+1);
			}
			
			else {
				studentsInGroups.put(group, 1);
			}
		}
		
		objetivo-=studentsWithoutInterest*1000000; //Penalty if afinity=0
		
		if(studentsInGroups.size()!=Data_Student.NG) {
			objetivo-=(Data_Student.NG-studentsInGroups.size())*1000000; //Penalty per empty group
		}
		
		else {
			for(int j=1;j<=studentsInGroups.size();j++) {
				
					if(studentsInGroups.get(j)<=studentsPerClass) {
						objetivo -= (studentsPerClass-studentsInGroups.get(j))*500; //Penalty depending on the distance with the mean.
					}
					
					else {
						objetivo += (studentsPerClass-studentsInGroups.get(j))*500; //bonification  depending on the distance with the mean.
					}
			}
		}
		
		return objetivo;
	}

	@Override
	public Solution_Students getSolucion(ValuesInRangeChromosome<Integer> cr) {
		// TODO Auto-generated method stub
		return new Solution_Students(cr.decode());
	}
	
	public static void main(String[] args) {
		AlgoritmoAG.ELITISM_RATE = 0.3;
		AlgoritmoAG.CROSSOVER_RATE = 0.8;
		AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.POPULATION_SIZE = 300;
		
		StoppingConditionFactory.NUM_GENERATIONS = 300;
		StoppingConditionFactory.SOLUTIONS_NUMBER_MIN = 1;
		StoppingConditionFactory.FITNESS_MIN = 50000;
		StoppingConditionFactory.stoppingConditionType = StoppingConditionType.SolutionsNumber;
		
		System.out.println("Exercise 1:");
		System.out.println("");
		System.out.println("file 1");
		ValuesInRangeProblemAG<Integer,Solution_Students> ex_1_1 = Exercise_1.create("files/input_1_1.txt");
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> alg1 = AlgoritmoAG.create(ex_1_1);
		alg1.ejecuta();
		System.out.println(ex_1_1.getSolucion(alg1.getBestChromosome()).toString());
		
		System.out.println("");
		System.out.println("file 2");
		ValuesInRangeProblemAG<Integer,Solution_Students> ex_1_2 = Exercise_1.create("files/input_1_2.txt");
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> alg2 = AlgoritmoAG.create(ex_1_2);
		alg2.ejecuta();
		System.out.println(ex_1_2.getSolucion(alg2.getBestChromosome()).toString());
		
		System.out.println("");
		System.out.println("file 3");
		ValuesInRangeProblemAG<Integer,Solution_Students> ex_1_3 = Exercise_1.create("files/input_1_3.txt");
		AlgoritmoAG<ValuesInRangeChromosome<Integer>> alg3 = AlgoritmoAG.create(ex_1_3);
		alg3.ejecuta();
		System.out.println(ex_1_3.getSolucion(alg3.getBestChromosome()).toString());
	}
}
