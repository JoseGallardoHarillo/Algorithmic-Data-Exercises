package pack;

import org.jgrapht.Graph;

public class User {

	public static User of() {
		return new User("");
	}

	public static User ofFormat(String[] format) {
		return new User(format);
	}

	public static User ofName(String name) {
		return new User(name);
	}
	
	private String name;

	private User(String name) {
		super();
		this.name = name;
	}

	private User(String[] format){
		super();
		this.name = format[0];
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}
}
