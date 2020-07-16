package Model;

import java.util.ArrayList;

public class GardenClass {

	static int max=8;
	
	private int number;
	private int quantity;
	private Worker worker;
	private ArrayList<Child> childInGarden = new ArrayList<Child>();
	
	
	
	public GardenClass(int number, int quantity) {
		super();
		this.number = number;
		this.quantity = quantity;
		
	}
	
	
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public ArrayList<Child> getChildInGarden() {
		return childInGarden;
	}


	public void setChildInGarden(ArrayList<Child> childInGarden) {
		this.childInGarden = childInGarden;
	}

	public void addChild(Child child)
	{
		if(childInGarden.size()<max)
			childInGarden.add(child);
		else {System.out.println("Max is 8 childrens");}
	}

	public Worker getWorker() {
		return worker;
	}

	public void setWorker(Worker worker) {
		this.worker = worker;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		result = prime * result + quantity;
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
		GardenClass other = (GardenClass) obj;
		if (number != other.number)
			return false;
		if (quantity != other.quantity)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GardenClass [number=" + number + ", quantity=" + quantity + "Worker = "+ worker+ " Childs " + childInGarden+ "]";
	}



	
		


	
	
}
