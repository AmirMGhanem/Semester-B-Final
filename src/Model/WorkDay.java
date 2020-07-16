package Model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

public class WorkDay extends Thread {

	private GregorianCalendar LocalDate;
	private Vector<Task> task = new Vector<Task>();

	public Vector<Task> getTask() {
		return task;
	}

	public void setTask(Vector<Task> task) {
		this.task = task;
	}

	public void addTask(Task t) {
		this.task.add(t);
	}

	public WorkDay(GregorianCalendar localDate) {
		super();
		LocalDate = localDate;
	}

	public WorkDay() {

	}

	public GregorianCalendar getLocalDate() {
		return LocalDate;
	}

	public void setLocalDate(GregorianCalendar localDate) {
		LocalDate = localDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((LocalDate == null) ? 0 : LocalDate.hashCode());
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
		WorkDay other = (WorkDay) obj;
		if (LocalDate == null) {
			if (other.LocalDate != null)
				return false;
		} else if (!LocalDate.equals(other.LocalDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return task + " Year : " + LocalDate.get(Calendar.YEAR) + "  Month : " + LocalDate.get(Calendar.MONTH)
				+ "  Day : " + LocalDate.get(Calendar.DAY_OF_MONTH);
	}

	public String date() {
		return LocalDate.get(Calendar.DAY_OF_MONTH) + "/" + LocalDate.get(Calendar.MONTH) + "/"
				+ LocalDate.get(Calendar.YEAR);

	}

	@Override
	public void run() {
		try {
			for (;;)
				for (Task t : task) {
					
					System.out.println("Description *** " + t.getDescription() + " Duration *** " + t.getDuration());
					sleep(3000);
				}
		} catch (InterruptedException e) {
			System.out.println("sleep interrupted");
		}
	}

}
