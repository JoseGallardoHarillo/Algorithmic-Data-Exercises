package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.math3.analysis.function.Max;

import us.lsi.common.Files2;
import us.lsi.common.Tuple;
import us.lsi.common.Tuple3;

public class Exercise_2 {
	
	public static Tuple3<Integer, Integer, Integer> ex_2(List<Integer> li){
		Integer n = li.size();
		return ex_2_aux(li, 0, n-1);
	}
	
	public static Tuple3<Integer, Integer, Integer> ex_2_aux(List<Integer> li, Integer left, Integer right){
		
		Tuple3<Integer, Integer, Integer> maxTuple;
		
		if(right-left<=1) return Tuple.create(left, right, li.get(left));
		
		Integer k = (left+right)/2;
		
		Tuple3<Integer, Integer, Integer> s1= ex_2_aux(li, left, k);
		
		Tuple3<Integer, Integer, Integer> s2= ex_2_aux(li, k+1, right);
		
		Tuple3<Integer, Integer, Integer> s3= ex_2_aux_2(li, left, k, right);
		
		Integer maxIntermediate = Math.max(s1.v3, s2.v3);
		Integer maxTotal = Math.max(maxIntermediate, s3.v3);
		
		if(maxTotal==s1.v3) maxTuple = s1;
		
		else if(maxTotal==s2.v3) maxTuple = s2;
		
		else maxTuple = s3;
		
		return maxTuple;
	}
	
	public static Tuple3<Integer, Integer, Integer> ex_2_aux_2(List<Integer> li, Integer left, Integer k, Integer right){
		
		Integer sum = 0;
		Integer l = 0;
		
		int leftSum = 0;
		
		for(int i=k;i>=left;i--) {
			
			sum = sum+li.get(i);
			
			if(sum>leftSum) {
			
				leftSum=sum;
				l=i;
			}
		}
		
		sum = 0;
		Integer r = 0;
		int rightSum = 0;
		
		for(int i=k+1;i<=right;i++) {
			
			sum = sum+li.get(i);
			
			if(sum>rightSum) {
				rightSum=sum;
				r=i+1;
			}
		}
		
		return Tuple.create(l, r, leftSum+rightSum);
	}
	
	
	
	//File Reading
	
	public static List<List<Integer>> read_f2(String file){
		List<List<Integer>> datas = Files2.streamFromFile(file).map(line->read_lines_2(line))
				.collect(Collectors.toList());
		return datas;
	}
		
	public static List<Integer> read_lines_2(String line){
		
		List<Integer> r = new ArrayList<>();
		
		if(line.length()>0) for(String n: line.split(", ")) r.add(Integer.parseInt(n));
				
		return r;
	}


}
