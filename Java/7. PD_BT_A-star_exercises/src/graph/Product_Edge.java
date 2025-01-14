package graph;

import pack.Data_Product;
import us.lsi.graphs.virtual.ActionSimpleEdge;

public class Product_Edge extends ActionSimpleEdge<Product_Vertex, Integer>{
	
	public Integer alternative;
	
	protected Product_Edge(Product_Vertex v, Product_Vertex v2, Integer action) {
		
		super(v,v2,action);
		this.alternative = action;
		this.weight = Data_Product.getValue(v.index)*action;
		
	}
	
	public static Product_Edge of(Product_Vertex v, Product_Vertex v2, Integer action) {
		
		final Product_Edge edge = new Product_Edge(v, v2, action);
		return edge;
	}

	@Override
	public String toString() {
		return "Product_Edge [alternative=" + alternative + "]";
	}

	
	
	
	
}
