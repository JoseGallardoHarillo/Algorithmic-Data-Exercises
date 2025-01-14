package graph;

import us.lsi.graphs.virtual.ActionSimpleEdge;

public class Subset_Edge extends ActionSimpleEdge<Subset_Vertex,Integer> {

	protected Subset_Edge(Subset_Vertex c1, Subset_Vertex c2, Integer action) {
		super(c1, c2, action);
		this.weight = action==0 ? 1.0:0.0;
	}
	
	public static Subset_Edge of(Subset_Vertex v, Subset_Vertex v2, Integer action) {
		
		final Subset_Edge edge = new Subset_Edge(v, v2, action);
		return edge;
	}
}