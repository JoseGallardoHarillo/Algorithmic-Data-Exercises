package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.math3.fraction.Fraction;

import us.lsi.common.Files2;
import us.lsi.common.Matrix;

public class Exercise_1 {
	/*
	public static Boolean ex_1(Matrix<Fraction> matrix) {
		int n = matrix.nc();
		return ex_1_aux(matrix, 0, 0, n);
	}
	
	public static Boolean ex_1_aux(Matrix<Fraction> matrix,Integer i, Integer j, Integer n) {
		
		Boolean r;
		Fraction Corner1 = matrix.get(i, j);
		Fraction Corner2 = matrix.get(i+n-1, j);
		Fraction Corner3 = matrix.get(i, j+n-1);
		Fraction Corner4 = matrix.get(i+n-1, j+n-1);
		
		if(n==2) {
			
			if(Corner1.equals(Corner2)||Corner1.equals(Corner3)||Corner1.equals(Corner4)||Corner2.equals(Corner3)||
					Corner2.equals(Corner4)||Corner3.equals(Corner4)) r = false;
			else r=true;
		}
		
		else {
			
			if(Corner1.equals(Corner2)||Corner1.equals(Corner3)||Corner1.equals(Corner4)||Corner2.equals(Corner3)||
					Corner2.equals(Corner4)||Corner3.equals(Corner4)) r = false;
			
			else r=ex_1_aux(matrix, i, j, n/2)&&ex_1_aux(matrix, i+(n/2), j, n/2)&&ex_1_aux(matrix, i, j+(n/2), n/2)&&
					ex_1_aux(matrix, i+(n/2), j+(n/2), n/2);
		}
		
		return r;
	}
	
	//File Reading
	
	public static Matrix<Fraction> read_f(String file){
		
		List<List<Integer>> datas = Files2.streamFromFile(file).map(line->read_lines(line))
				.collect(Collectors.toList());
		
		Integer[][] matrix = new Integer[datas.size()][datas.size()];
		
		for(int i=0;i<datas.size();i++) {
		
			List<Integer> row = datas.get(i);
			
			for(int j=0;j<row.size();j++) {
			
				Integer number = row.get(j);
				matrix[i][j] = number;
			}
		}
		
		return Matrix.of(matrix);
	}
		
	public static List<Integer> read_lines(String line){
		
		List<Integer> r = new ArrayList<>();
		
		if(line.length()>0) for(String n: line.split(" ")) r.add(Integer.parseInt(n));
				
		return r;
	}*/
}
