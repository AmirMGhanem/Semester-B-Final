package Model;
import View.view;

public class Task   {
	private String hint;
	private String description;
	private int duration;
	
	
	
	public Task(String hint, String description, int duration) {
		super();
		setHint(hint);
		this.description = description;
		this.duration = duration;
		
	}
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + duration;
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
		Task other = (Task) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (duration != other.duration)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Task [description=" + description + ", duration=" + duration + "]";
	}

	public String getHint() {
		return hint;
	}
	public void setHint(String hint) {
		
		this.hint = hint;
	}
	
	public String saveFormat()
	{
		return hint + "/" + description + "/" + duration+"/";
		
	}
}
