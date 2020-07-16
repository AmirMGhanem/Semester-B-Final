package Util;
import View.view.*;
import View.view;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import java.util.Scanner;

import Model.Task;
import Model.WorkDay;
import Model.Worker;

public class FileControl {
	public static void save(String fileName, Object obj) throws IOException{
		File f = new File("files/"+fileName);
		f.createNewFile();
		PrintWriter pw = new PrintWriter(f);
		pw.print(obj);
		pw.close();
	}
	
	
	public static void saveTask(String fileName,Object obj,Worker w,String date) throws IOException{
		FileWriter f = new FileWriter("files/"+fileName,true);
		f.write("\n");
		f.write((w.getName()+"/"));
		f.write(obj.toString());
		f.write(date.toString());
		
		f.close();
	}
	
	
	
	public static String load(String fileName) throws FileNotFoundException{
		File f = new File("files/"+fileName);
		Scanner s = new Scanner(f);
		String str = s.nextLine();
		s.close();
		return str;
	}
	
	
	
	public static int loadTask() throws FileNotFoundException {
		File f = new File("files/Tasks");
		Scanner s = new Scanner(f);

		

		while(s.hasNextLine())
		{
			String str = s.nextLine();

			String[] stringline = new String[7];

			stringline = str.split("/");
			
			Task t = new Task(stringline[1], stringline[2], Integer.parseInt(stringline[3]));
			WorkDay wd = new WorkDay();
			GregorianCalendar GC = new GregorianCalendar();
			GC.set(GregorianCalendar.YEAR,Integer.parseInt( stringline[6]));
			GC.set(GregorianCalendar.MONTH,Integer.parseInt(stringline[5]));
			GC.set(GregorianCalendar.DATE,Integer.parseInt(stringline[4]));
			wd.setLocalDate(GC);
			wd.addTask(t);
			
			for(Worker w : View.view.ALw)
			{
				if(w.getName().equals(stringline[0]))
					w.addWorkDays(wd);
					
			}
			
		}
		
		
		s.close();
		return View.view.ALta.size();
	}


	
	
}