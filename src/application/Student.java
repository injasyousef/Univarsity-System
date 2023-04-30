package application;

public class Student implements Comparable<Student> {
	private String name;
	private int id;
	private double avg;
	private String gengder;
	
	
	public Student(String name) {
		super();
		this.name = name;
	}
	
	public Student(String name, int id, double avg, String gengder) {
		super();
		this.name = name;
		this.id = id;
		this.avg = avg;
		this.gengder = gengder;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public String getGengder() {
		return gengder;
	}
	public void setGengder(String gengder) {
		this.gengder = gengder;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", avg=" + avg + ", gengder=" + gengder + "]";
	}
	@Override
	public int hashCode() {
		int hash=0;
		for(int i=0;i<name.length();i++)
			hash+=name.charAt(i);
		return hash;
	}
	@Override
	public int compareTo(Student o) {
		return name.compareTo(o.getName());
	}
}
