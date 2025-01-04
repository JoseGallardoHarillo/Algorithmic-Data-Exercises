package PL;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import pack.Data_Student;
import pack.Data_Product;
import pack.Solution_Students;
import pack.Solution_Products;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Exercise_1 {
	
	public static Integer getNA() {
		return Data_Student.NStudents;
	}
	
	public static Integer getNG() {
		return Data_Student.NG;
	}
	
	public static Double getAfinity(Integer i, Integer j) {
		return Data_Student.getAfinity(i, j);
	}
	
	public static Integer getMeanStudents() {
		return Data_Student.getNStudents()/Data_Student.getNG();
	}
	
	public static void main(String[] args) throws IOException{
		
		Locale.setDefault(new Locale("en","US"));
		System.out.println("Exercise 1:");
		System.out.println("");
		
		Data_Student.iniDatas("files/input_1_1.txt");
		AuxGrammar.generate(Exercise_1.class,"models/ex_1.lsi", "files/ex_1_1.lp");
		GurobiSolution gs = GurobiLp.gurobi("files/ex_1_1.lp");
		System.out.println("file 1");
		System.out.println(new Solution_Students(gs.objVal, gs.values));
		
		System.out.println("");
		Data_Student.iniDatas("files/input_1_2.txt");
		AuxGrammar.generate(Exercise_1.class,"models/ex_1.lsi", "files/ex_1_2.lp");
		GurobiSolution gs2 = GurobiLp.gurobi("files/ex_1_2.lp");
		System.out.println("");
		System.out.println("file 2");
		System.out.println(new Solution_Students(gs2.objVal, gs2.values));
		
		System.out.println("");
		Data_Student.iniDatas("files/input_1_3.txt");
		AuxGrammar.generate(Exercise_1.class,"models/ex_1.lsi", "files/ex_1_3.lp");
		GurobiSolution gs3 = GurobiLp.gurobi("files/ex_1_3.lp");
		System.out.println("");
		System.out.println("file 3");
		System.out.println(new Solution_Students(gs3.objVal, gs3.values));
	}
}
