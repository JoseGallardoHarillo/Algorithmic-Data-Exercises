package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.jgrapht.GraphPath;

import pack.Data_Product;
import pack.Product;
import pack.Solution_Products;
import us.lsi.common.Sets2;
import us.lsi.graphs.virtual.ActionVirtualVertex;

public class Product_Vertex extends ActionVirtualVertex<Product_Vertex, Product_Edge, Integer> {
	
	public Integer index;
	public Set<String> remainingFuncionalities;
	public static Integer NP;
	
	//Factorias
	
	public Product_Vertex() {
		this.index = 0;
		this.remainingFuncionalities = Sets2.of(Data_Product.getWishedFuncionalities());
		Product_Vertex.NP = Data_Product.NP;
	}
	
	public Product_Vertex(Integer i, Set<String> s) {
		this.index = i;
		this.remainingFuncionalities = s;
	}
	
	public static Product_Vertex of(Integer i, Set<String> s) {
		final Product_Vertex newVertex = new Product_Vertex(i, s);
		return newVertex;
	}
	
	public static Product_Vertex initialVertex() {
		final Product_Vertex newVertex = new Product_Vertex();
		
		return newVertex;
	}
	
	public static Boolean goal(Product_Vertex v) {
		
		return v.index == Data_Product.NP && v.remainingFuncionalities.size()==0;
	}
	
	//Auxiliary methods
	
	public static Product_Vertex copy(Product_Vertex v) {
		final Product_Vertex copy = new Product_Vertex(v.index, v.remainingFuncionalities);
		
		return copy;
	}
	
	//Overide methods
	
	
	@Override
	public Boolean isValid() {
		// TODO Auto-generated method stub
		
		Boolean condition1 = this.index>=0 && this.index<=Product_Vertex.NP;
		Boolean condition2 = true;
		
		for(String r : remainingFuncionalities) {
			condition2 = condition2 && Data_Product.getWishedFuncionalities().contains(r);
		}
		
		return condition1 && condition2;
	}

	@Override
	public List<Integer> actions() {
		// TODO Auto-generated method stub
		
		List<Integer> alternatives = new ArrayList<Integer>();
		
		if(this.index < Product_Vertex.NP) {
			
			//Tengo ya todos?
			
			if(remainingFuncionalities.size()==0) {
				//Then I don't catch nothing
				alternatives.add(0);
				return alternatives;
			}
			
			//I'm in the penultimate vertex, it would be somo alternative resolved?
			
			if(this.index==Product_Vertex.NP-1) {
				
				Set<String> proposal = Sets2.of(remainingFuncionalities);
				
				Set<String> itemsToTakeOut = Sets2.of(Data_Product.getProduct(this.index).
						getFunctionalities());
				proposal.removeAll(itemsToTakeOut);
				
				//If this path doesn't reach to a solution, then we don't give any alternative
				
				if(proposal.size()==0) {
					alternatives.add(1);
				}
				
				return alternatives;
			}
			
			else {
				//Otherwise, I see catch and not catch the alternative
				
				alternatives.add(0);
				alternatives.add(1);
			}
		}
		
		return alternatives;
	}

	@Override
	public Product_Vertex neighbor(Integer a) {
		// TODO Auto-generated method stub
		
		Set<String> new_alternative = Sets2.copy(this.remainingFuncionalities);
		
		if(a==1) {
			Set<String> itemsToTakeOut = Sets2.of(Data_Product.getProduct(this.index).
					getFunctionalities());
			new_alternative.removeAll(itemsToTakeOut);
		}
		
		
		return Product_Vertex.of(index+1, new_alternative);
	}

	@Override
	public Product_Edge edge(Integer a) {
		// TODO Auto-generated method stub
		return Product_Edge.of(this,this.neighbor(a),a);
	}
	
	//Auxiliary
	
	public static Solution_Products getSolution_Products(GraphPath<Product_Vertex, Product_Edge> path) {
		return Product_Vertex.getSolution_Products(path.getEdgeList());
	}
	
	public static Solution_Products getSolution_Products(List<Product_Edge> l) {
		
		List<Integer> alternatives = new ArrayList<Integer>();
		
		for(Product_Edge alternative : l) {
			alternatives.add(alternative.alternative);
		}
		
		Solution_Products s = new Solution_Products(alternatives);
		
		return s;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((index == null) ? 0 : index.hashCode());
		result = prime * result + ((remainingFuncionalities == null) ? 0 : remainingFuncionalities.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product_Vertex other = (Product_Vertex) obj;
		if (index == null) {
			if (other.index != null)
				return false;
		} else if (!index.equals(other.index))
			return false;
		if (remainingFuncionalities == null) {
			if (other.remainingFuncionalities != null)
				return false;
		} else if (!remainingFuncionalities.equals(other.remainingFuncionalities))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product_Vertex [index=" + index + ", remainingFuncionalities=" + remainingFuncionalities + "]";
	}

	

}
