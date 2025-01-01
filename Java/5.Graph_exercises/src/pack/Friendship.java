package pack;

import us.lsi.grafos.datos.Carretera;
import us.lsi.grafos.datos.Ciudad;

public class Friendship {
	
	public static Friendship of() {
		return new Friendship();
	}

	public static Friendship ofVertex(User u1, User u2) {
		return new Friendship(u1,u2);
	}

	public static Friendship ofFormat(User u1, User u2, String[] formato) {
		return new Friendship(u1,u2,formato);
	}

	public static Friendship ofWeight(User u1, User u2, Double km) {
		return new Friendship(u1, u2, km);
	}
	
	public static Friendship reverse(Friendship a) {
		return new Friendship(a.target, a.source,a.km);
	}

	private static int num =0;
	private User source;
	private User target;
	private Double km;
	private int id;

	private Friendship(User u1, User u2) {
		this.source = u1;
		this.target = u2;
		this.km = 0.;
		this.id = num;
		num++;
	}
	
	private Friendship() {
		this.source = null;
		this.target = null;
		this.km = 0.;
		this.id = num;
		num++;
	} 
	
	private Friendship(User source, User target, Double km, String name) {
		super();
		this.source = source;
		this.target = target;
		this.km = km;
		this.id = num;
		num++;
	}

	private Friendship(User u1, User u2, String[] name) {
		this.source = u1;
		this.target = u2;
		this.km = 1.0;
		this.id = num;
		num++;
	}
	
	private Friendship(User u1, User u2, Double km) {
		this.source = u1;
		this.target = u2;
		this.km = km;
		this.id = num;
		num++;
	}
	
	public User getSource(){
		return source;
	}
	
	public User getTarget(){
		return target;
	}
	
	public Double getKm(){
		return km;
	}
	
	@Override
	public String toString() {
		return "";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Carretera))
			return false;
		Friendship other = (Friendship) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
