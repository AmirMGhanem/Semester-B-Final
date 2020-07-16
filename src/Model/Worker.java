package Model;

import java.util.ArrayList;
import java.util.Stack;

public class Worker implements IService{
	
	private int id; // A.I
	private String name;
	private ArrayList<WorkDay> workDays=  new ArrayList<WorkDay>();
	
	public Worker()
	{
		
	}
	public Worker(String name) {setName(name);}
	public Worker(int id,String name)
	{
		setName(name);
		setId(id);
	}
	

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	
	public void setName(String name)
	{
		if(nameValidation(name))
			this.name=name;
		
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
		Worker other = (Worker) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Worker [name=" + name + ", workDays=" + workDays +"]";
	}


	public ArrayList<WorkDay> getWorkDays() {
		return workDays;
	}


	public void setWorkDays(ArrayList<WorkDay> workDays) {
		this.workDays = workDays;
	}

	public void addWorkDays(WorkDay Workday)
	{
		workDays.add(Workday);
	}
	

		




}
