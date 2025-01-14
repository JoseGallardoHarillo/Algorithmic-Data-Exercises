package Manual;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import org.jgrapht.GraphPath;

import pack.Data_Product;
import pack.Product;
import pack.Solution_Products;
import us.lsi.common.Lists2;
import us.lsi.common.Sets2;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class Product_BT_Vertex {
	
	public Integer index;
	public List<Integer> selectedProducts;
	public static Integer NP;
	
	//Factories
	
	public Product_BT_Vertex() {
		// TODO Auto-generated constructor stub
		this.index = 0;
		this.selectedProducts = new ArrayList<Integer>();
		
		Product_BT_Vertex.NP = Data_Product.NP;	
	}
	
	public Product_BT_Vertex(Integer i, List<Integer> l) {
		// TODO Auto-generated constructor stub
		this.index = i;
		this.selectedProducts = l;
	}
	
	public static Product_BT_Vertex of(Integer i, List<Integer> l) {
		// TODO Auto-generated constructor stub
		final Product_BT_Vertex new_Vertex = new Product_BT_Vertex(i, l);
		
		return new_Vertex;
	}
	
	//We'll define the initial and final vertex
	
	public static Product_BT_Vertex initialVertex() {
		final Product_BT_Vertex new_Vertex = new Product_BT_Vertex();
		
		return new_Vertex;
	}
	
	public static Boolean goal(Product_BT_Vertex v) {
		
		return v.index == Data_Product.NP && Product_BT_Vertex.verificator(v.selectedProducts);
	}
	
	//we'll complete the auxiliary methods
	
	public static Product_BT_Vertex copy(Product_BT_Vertex v) {
		final Product_BT_Vertex copy = new Product_BT_Vertex(v.index, v.selectedProducts);
		
		return copy;
	}
	
	//overide methods
	
	public Boolean isValid() {
		// TODO Auto-generated method stub
		
		Boolean condition1 = this.index>=0 && this.index<=Product_BT_Vertex.NP;
		
		return condition1;
	}
	
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		
		List<Integer> alternatives = new ArrayList<Integer>();
		
		if(this.index < Product_BT_Vertex.NP) {
			
			//Do we have all of them?
			
			if(verificator(selectedProducts)) {
				//Then I don't catch nothing
				alternatives.add(0);
				return alternatives;
			}
			
			//I'm in the penultimate vertex, I would use some alternative?
			
			if(this.index==Product_BT_Vertex.NP-1) {
				
				List<Integer> proposal = Lists2.copy(this.selectedProducts);
				
				//if the path don't arrive to the solution, then we don't give the alternatives
				
				if(verificator(proposal))
					alternatives.add(0);
				
				else {
					
					proposal.add(index);
					
					if(verificator(proposal))
						alternatives.add(1);
				}
				
				return alternatives;
			}
			
			else {
				//Otherwise, I contemplate catch and not catch the alternatives
				
				alternatives.add(0);
				alternatives.add(1);
			}
		}
		
		return alternatives;
	}

	
	public Product_BT_Vertex neighbor(Integer a) {
		// TODO Auto-generated method stub
		List<Integer> new_Alternative = Lists2.copy(this.selectedProducts);
		
		if(a==1)
			new_Alternative.add(this.index);
		
		return Product_BT_Vertex.of(index+1, new_Alternative);
	}

	//Auxiliares

	public Double Value_Sum() {
		
		return Product_BT_Vertex.selectionValue(this.selectedProducts);
	}
	
	public static Boolean verificator(List<Integer> selected) {
		
		Set<String> fun_Selected = Sets2.empty();
		
		for(Integer selected_Index : selected)
			fun_Selected.addAll(Data_Product.getProduct(selected_Index).second);
		
		Boolean ver = true;
		for(String func: Data_Product.getWishedFuncionalities())
			ver = ver && fun_Selected.contains(func);
		
		return ver;
	}
	
	public void forward(Integer a) {
		
		if(a==1)
			this.selectedProducts.add(this.index);
		
		this.index++;
	}
	
	public void back(Integer a) {
		
		if(a==1)
			Lists2.removeLast(this.selectedProducts);
		
		this.index--;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((selectedProducts == null) ? 0 : selectedProducts.hashCode());
		
		return result;
	}
	
	public Double Limit(Integer a) {
		
		List<Integer> alternative_Selection = Lists2.copy(this.selectedProducts);
		
		if(a==1)
			alternative_Selection.add(this.index);
		
		Double weightLimit = selectionValue(alternative_Selection);
		
		return weightLimit;
	}
	
	
	public static Double selectionValue(List<Integer> selected_pr_s) {
		
		Double value = 0.0;
		
		for(Integer selected_pr: selected_pr_s) 
			value+= Data_Product.getValue(selected_pr);
		
		return value;
	}
	
	public static List<String> itemsSelection(List<Integer> selection){
		List<String> items = new ArrayList<String>();
		
		for(int i=0;i<selection.size();i++) {
			
			if(selection.get(i)>0)
				items.addAll(Data_Product.getProduct(i).getFunctionalities());
		}
		return items;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product_BT_Vertex other = (Product_BT_Vertex) obj; 
		if (other.index != this.index) {
			return false;
		} 
		if(!this.selectedProducts.equals(other.selectedProducts)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Product_BT [index=" + index + ", selected products=" + selectedProducts + "]";
	}
}
