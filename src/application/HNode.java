package application;

public class HNode<T> {
	T data;
	char flag;
	
	public HNode(T data,char flag) {
		super();
		this.data = data;
		this.flag = flag;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public char getF() {
		return flag;
	}

	public void setF(char flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "HNode [ " + data + "," + flag + " ]";
	}
	
	
}

