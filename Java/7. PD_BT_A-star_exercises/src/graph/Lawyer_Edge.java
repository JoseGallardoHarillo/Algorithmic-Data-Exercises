package graph;

import us.lsi.graphs.virtual.ActionSimpleEdge;

public class Lawyer_Edge extends ActionSimpleEdge<Lawyer_Vertex,Integer>{

		protected Lawyer_Edge(Lawyer_Vertex v, Lawyer_Vertex v2, Integer action) {
			super(v, v2, action);
		}
		
		public static Lawyer_Edge of(Lawyer_Vertex v, Lawyer_Vertex v2, Integer action) {
			
			final Lawyer_Edge edge = new Lawyer_Edge(v, v2, action);
			return edge;
		}

}

