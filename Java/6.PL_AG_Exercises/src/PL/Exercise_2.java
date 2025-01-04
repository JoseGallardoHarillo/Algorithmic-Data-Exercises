package PL;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import pack.Data_Lawyer;
import pack.Data_Student;
import pack.Solution_Lawyers;
import pack.Solution_Students;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Exercise_2 {
	
	public static Integer getNLawyer() {
		return Data_Lawyer.NLawyer;
	}
	
	public static Integer getHour(Integer i, Integer j) {
		return Data_Lawyer.getHour(i, j);
	}
	
	public static Integer getNC() {
		return Data_Lawyer.NC;
	}
	
	
	
	public static void main(String[] arg) throws IOException{
		Locale.setDefault(new Locale("en","US"));
		
		System.out.println("Exercise 2:");
		System.out.println("");
		
		Data_Lawyer.iniDatas("files/input_2_1.txt");
		AuxGrammar.generate(Exercise_2.class,"models/ex_2.lsi", "files/ex_2_1.lp");
		GurobiSolution gs = GurobiLp.gurobi("files/ex_2_1.lp");
		System.out.println("file 1");
		System.out.println(new Solution_Lawyers(gs.values));
		
		System.out.println("");
		Data_Lawyer.iniDatas("files/input_2_2.txt");
		AuxGrammar.generate(Exercise_2.class,"models/ex_2.lsi", "files/ex_2_2.lp");
		GurobiSolution gs2 = GurobiLp.gurobi("files/ex_2_2.lp");
		System.out.println("");
		System.out.println("file 2");
		System.out.println(new Solution_Lawyers(gs2.values));
		
		System.out.println("");
		Data_Lawyer.iniDatas("files/input_2_3.txt");
		AuxGrammar.generate(Exercise_2.class,"models/ex_2.lsi", "files/ex_2_3.lp");
		GurobiSolution gs3 = GurobiLp.gurobi("files/ex_2_3.lp");
		System.out.println("");
		System.out.println("file 3");
		System.out.println(new Solution_Lawyers(gs3.values));
	}
}