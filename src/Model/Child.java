package Model;

public class Child implements IService{

	private String id;
	private String name;
	private int age;
	private GardenClass garden;
	
	public Child()
	{
		
	}
	
	
	public Child(String id,String name,int age)
	{
		setId(id);
		setName(name);
		setAge(age);
		
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		if(numValidation(id,3))
		{
		this.id=id;			
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(nameValidation(name)){
			this.name=name;
		}
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Child other = (Child) obj;
		if (age != other.age)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Child [id=" + id + ", name=" + name + ", age=" + age + "]";
	}


	public GardenClass getGarden() {
		return garden;
	}


	public void setGarden(GardenClass garden) {
		this.garden = garden;
	}
	
}
