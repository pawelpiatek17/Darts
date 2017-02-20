package Darts.Util;


public class Pair {
	private boolean key;
	private int value;
	public Pair(boolean key, int value) {
		super();
		this.key = key;
		this.value = value;
	}
	public boolean getKey() {
		return key;
	}
	public void setKey(boolean key) {
		this.key = key;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Pair [key=" + key + ", value=" + value + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (key ? 1231 : 1237);
		result = prime * result + value;
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
		Pair other = (Pair) obj;
		if (key != other.key)
			return false;
		if (value != other.value)
			return false;
		return true;
	}
	
	
}
