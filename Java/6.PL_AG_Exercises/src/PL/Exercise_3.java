package PL;

import java.io.IOException;
import java.util.Locale;

import pack.Data_Student;
import pack.Data_Product;
import pack.Product;
import pack.Solution_Products;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class Exercise_3 {
	
	public static Integer getNP() {
		return Data_Product.NP;
	}
	
	public static Double getValue(Integer i) {
		return Data_Product.getValue(i);
	}
	
	public static Integer getNF() {
		return Data_Product.NFD;
	}
	
	public static Integer functionalityContained(Integer i,Integer j) {
		Product p = Data_Product.getProduct(i);
		String f = Data_Product.getFD(j);
		
		return p.functionalityContained(f)?1:0;
		
	}
	
	public static void main(String[] args) throws IOException{
		Locale.setDefault(new Locale("en","US"));
		
		System.out.println("Exercise 3:");
		System.out.println("");
		
		Data_Product.iniDatas("files/input_3_1.txt");
		AuxGrammar.generate(Exercise_3.class,"models/ex_3.lsi", "files/ex_3_1.lp");
		GurobiSolution gs = GurobiLp.gurobi("files/ex_3_1.lp");
		System.out.println("file 1");
		System.out.println(new Solution_Products(gs.objVal, gs.values));
		
		System.out.println();
		Data_Product.iniDatas("files/input_3_2.txt");
		AuxGrammar.generate(Exercise_3.class,"models/ex_3.lsi", "files/ex_3_2.lp");
		GurobiSolution gs2 = GurobiLp.gurobi("files/ex_3_2.lp");
		System.out.println("");
		System.out.println("file 2");
		System.out.println(new Solution_Products(gs2.objVal, gs2.values));
		
		System.out.println("");
		Data_Product.iniDatas("files/input_3_3.txt");
		AuxGrammar.generate(Exercise_3.class,"models/ex_3.lsi", "ffiles/ex_3_3.lp");
		GurobiSolution gs3 = GurobiLp.gurobi("files/ex_3_3.lp");
		System.out.println("");
		System.out.println("file 3");
		System.out.println(new Solution_Products(gs3.objVal, gs3.values));
	}

}
