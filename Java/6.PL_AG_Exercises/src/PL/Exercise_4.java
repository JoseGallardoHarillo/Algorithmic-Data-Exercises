package PL;

import java.io.IOException;
import java.util.Locale;

import pack.Data_Student;
import pack.Data_Subset;
import pack.Solution_Students;
import pack.Solution_Subset;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Exercise_4 {
	
	public static Integer geSize_S() {
		return Data_Subset.Size_S;
	}
	
	public static Integer getNumber(Integer i) {
		return Data_Subset.getNumber(i);
	}
	
	
public static void main(String[] args) throws IOException{
		
		Locale.setDefault(new Locale("en","US"));
		
		System.out.println("Exercise 4:");
		System.out.println("");
		
		Data_Subset.iniDatas("files/input_4.txt",0);
		AuxGrammar.generate(Exercise_4.class,"models/ex_4.lsi", "files/ex_4_1.lp");
		GurobiSolution gs = GurobiLp.gurobi("files/ex_4_1.lp");
		System.out.println("line 1");
		System.out.println(new Solution_Subset(gs.objVal, gs.values));
		
		System.out.println("");
		Data_Subset.iniDatas("files/input_4.txt",1);
		AuxGrammar.generate(Exercise_4.class,"models/ex_4.lsi", "files/ex_4_2.lp");
		GurobiSolution gs2 = GurobiLp.gurobi("files/ex_4_2.lp");
		System.out.println("linea 2");
		System.out.println(new Solution_Subset(gs2.objVal, gs2.values));
		
		System.out.println("");
		Data_Subset.iniDatas("files/input_4.txt",2);
		AuxGrammar.generate(Exercise_4.class,"models/ex_4.lsi", "files/ex_4_3.lp");
		GurobiSolution gs3 = GurobiLp.gurobi("files/ex_4_3.lp");
		System.out.println("line 3");
		System.out.println(new Solution_Subset(gs3.objVal, gs3.values));
		
		System.out.println("");
		Data_Subset.iniDatas("files/input_4.txt",3);
		AuxGrammar.generate(Exercise_4.class,"models/ex_4.lsi", "files/ex_4_4.lp");
		GurobiSolution gs4 = GurobiLp.gurobi("files/ex_4_4.lp");
		System.out.println("line 4");
		System.out.println(new Solution_Subset(gs4.objVal, gs4.values));
		
		System.out.println("");
		Data_Subset.iniDatas("files/input_4.txt",4);
		AuxGrammar.generate(Exercise_4.class,"models/ex_4.lsi", "files/ex_4_5.lp");
		GurobiSolution gs5 = GurobiLp.gurobi("files/ex_4_5.lp");
		System.out.println("line 5");
		System.out.println(new Solution_Subset(gs5.objVal, gs5.values));
	}

}
