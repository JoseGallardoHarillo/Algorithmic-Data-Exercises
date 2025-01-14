package pack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import us.lsi.common.Files2;

public class Data_Student {
	
	public static Integer NStudents;
	public static Integer NG;
	public static List<Student> students;
	
	public static void iniDatas(String file) {
		
		students = new ArrayList<Student>();
		
		for(String line: Files2.linesFromFile(file)) {
			
			Student a = Student.create(line);
			students.add(a);
		}
		
		NStudents = students.size();
		
		NG = students.get(0).getNG(students.get(0).getGroupList());
		
	}
	
	public static List<Student> getStudents() {
		return students;
	}
	
	
	public static Integer getNStudents() {
		return NStudents;
	}
	
	public static Integer getNG() {
		return NG;
	}
	
	public static Student getStudent(Integer index) {
		return students.get(index);
	}
	
	public static Double getAfinity(Integer index,Integer group) {
		return students.get(index).getGroupList().get(group);
	}
	
	public static Map<Integer, Double> getGrupos(Integer index) {
		return getStudent(index).getGroupList();
	}
	
	public static void toconsole() {
		System.out.println("Students: "+getStudents());
	}
}
