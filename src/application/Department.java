package application;

public class Department implements Comparable<Department> {
	private String name;
	private String fileName;
	HTable<Student> students=new HTable<>(11);
	
	public Department(String name) {
		super();
		this.name = name;
	}
	
	public Department(String name, String fileName) {
		super();
		this.name = name;
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	@Override
	public String toString() {
		return "Department [name=" + name + ", fileName=" + fileName + "]";
	}

	@Override
	public int compareTo(Department o) {
		return name.compareTo(o.getName());
	}
	
	
}
