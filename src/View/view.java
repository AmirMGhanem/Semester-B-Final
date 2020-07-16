package View;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;



import DBH.ChildDBH;
import DBH.GardenDBH;
import DBH.WorkerDBH;
import DBH.alDBH;
import Model.Child;
import Model.GardenClass;
import Model.IService;
import Model.Task;
import Model.WorkDay;
import Model.Worker;
import Util.FileControl;

public class view extends JFrame implements ActionListener, IService {

	public static ArrayList<Worker> ALw = new ArrayList<Worker>();
	public static ArrayList<Child> ALch = new ArrayList<Child>();
	public static ArrayList<GardenClass> ALcl = new ArrayList<GardenClass>();
	public static ArrayList<Task> ALta = new ArrayList<Task>();
	public static ArrayList<WorkDay> ALwd = new ArrayList<WorkDay>();

	protected JPanel pnav = new JPanel(new GridLayout(0, 1));
	protected JPanel pcenter2 = new JPanel(new GridLayout(0, 1));
	public JPanel pcenter = new JPanel(new FlowLayout());
	protected JPanel pLineEnd = new JPanel();

	protected JTextArea Res = new JTextArea();

	protected JPanel pclass = new JPanel(new GridLayout(0, 2));
	protected JPanel pworker = new JPanel(new GridLayout(0, 3));
	protected JPanel pchild = new JPanel(new GridLayout(0, 2));
	public JPanel ptask = new JPanel(new GridLayout(0, 3));
	protected JPanel pSearch = new JPanel(new GridLayout(0, 2));
	protected JPanel pWorkdays = new JPanel(new GridLayout(0, 3));

	// pworkdays

	protected SpinnerModel SMday = new SpinnerNumberModel(1, 1, 30, 1);
	protected JSpinner Sday = new JSpinner(SMday);
	protected SpinnerModel SMmonth = new SpinnerNumberModel(1, 1, 12, 1);
	protected JSpinner Smonth = new JSpinner(SMmonth);
	protected SpinnerModel SMyear = new SpinnerNumberModel(2020, 2019, 2040, 1);
	protected JSpinner Syear = new JSpinner(SMyear);
	protected JButton BwdAddWorkDay = new JButton("Add To Worker");

	protected Choice CHworker = new Choice();

	protected JButton Bclass = new JButton("Add class");;
	protected JButton Bworker = new JButton("Add Worker");
	protected JButton Bchild = new JButton("Add Child");
	protected JButton Btask = new JButton("Add Task");
	protected JButton Bsearch = new JButton("Search");

	// Add class
	protected JTextField TFclassNum = new JTextField("");
	protected JTextField TFclassQTY = new JTextField("");
	protected Choice cTeacher = new Choice();
	protected JButton BcSubmit = new JButton("Submit");
	protected JButton BcClear = new JButton("Clear");

	// Add worker
	protected JTextField TFwName = new JTextField("");

	protected JButton BWorkdays = new JButton("WorkDays");
	protected JButton BwSubmit = new JButton("Submit");
	protected JButton BwClear = new JButton("Clear");

	// Add child
	protected JTextField TFchname = new JTextField("");

	protected JTextField TFchid = new JTextField("");
	protected JTextField TFchAge = new JTextField("");
	protected Choice Cgarden = new Choice();
	protected JButton BchSubmit = new JButton("Submit");
	protected JButton BchClear = new JButton("Clear");

	// Add Task

	protected JTextField TFtaskDuration = new JTextField();
	protected JTextArea TFdescription = new JTextArea();
	protected JTextField TFtaskHint = new JTextField();
	protected JButton BtSubmit = new JButton("Submit!");
	protected JButton BtClear = new JButton("Clear");
	protected Choice CHtWorker = new Choice();
	public Choice CHtWD = new Choice();
	protected JButton BupdateWorker = new JButton("Update Choice");

	// Search

	protected Choice Cworker = new Choice();
	protected JButton BworkerSearch = new JButton("Run Thread");
	protected JButton BworkerDelete = new JButton("Terminate");

	
	ImageIcon img = new ImageIcon(new ImageIcon("files/signature.png").getImage().getScaledInstance(260, 70, Image.SCALE_DEFAULT));
	JLabel jlPic = new JLabel(img);

	private boolean isOpened = false;
	private boolean isOpenedClass = false;
	private boolean isOpenedChild = false;
	private boolean isOpenedworkday = false;

	public view() throws SQLException, FileNotFoundException {
		super("Gardent Managment System");
		setLayout(new BorderLayout());

		pnav.add(Bclass);
		Bclass.addActionListener(this);
		Bclass.setBackground(Color.lightGray);
		pnav.add(Bworker);
		Bworker.addActionListener(this);
		Bworker.setBackground(Color.lightGray);
		pnav.add(Bchild);
		Bchild.addActionListener(this);
		Bchild.setBackground(Color.lightGray);
		pnav.add(Btask);
		Btask.addActionListener(this);
		Btask.setBackground(Color.lightGray);
		pnav.add(Bsearch);
		Bsearch.addActionListener(this);
		Bsearch.setBackground(Color.lightGray);
		// -----------
		classinit();
		childinit();
		workerinit();
		taskinit();
		searchinit();
		add(pnav, BorderLayout.LINE_START);

		pnav.setPreferredSize(new Dimension(140, 10));

		pcenter2.add(pcenter);
		add(pcenter, BorderLayout.CENTER);

		// ------------------------------------------------------------------------------

		ALw.addAll(new alDBH().GetWorkers());
		ALch.addAll(new alDBH().getChilds());
		ALcl.addAll(new alDBH().getGardenClasses());

		FileControl.loadTask();
		for (Worker w : ALw)
			System.out.println(w.getName() + " [Workdays --" + w.getWorkDays() + "]");

	}

	private void classinit() {

		pclass.add(new JLabel("                  Class"));
		pclass.add(new JLabel("Adder"));
		pclass.add(new JLabel("Enter Number :"));
		pclass.add(TFclassNum);
		pclass.add(new JLabel("Quantity : "));
		pclass.add(TFclassQTY);
		pclass.add(new JLabel("Choose Worker :"));
		pclass.add(cTeacher);
		pclass.add(BcSubmit);
		BcSubmit.addActionListener(this);
		BcSubmit.setBackground(Color.green);
		pclass.add(BcClear);
		BcClear.addActionListener(this);
		BcClear.setBackground(Color.orange);


	}

	private void childinit() {
		pchild.add(new JLabel("                  Child"));
		pchild.add(new JLabel("Adder"));
		pchild.add(new JLabel("Child id"));
		pchild.add(TFchid);
		pchild.add(new JLabel("Child Firstname"));
		pchild.add(TFchname);
		pchild.add(new JLabel("Age :"));
		pchild.add(TFchAge);
		pchild.add(new JLabel("Select Garden"));
		pchild.add(Cgarden);
		pchild.add(BchSubmit);
		BchSubmit.addActionListener(this);
		BchSubmit.setBackground(Color.green);
		pchild.add(BchClear);
		BchClear.addActionListener(this);
		BchClear.setBackground(Color.ORANGE);

	}

	private void workerinit() {

		pworker.add(new JLabel("Worker Name "));
		pworker.add(TFwName);
		pworker.add(new JLabel());

		pworker.add(BwSubmit);
		BwSubmit.addActionListener(this);
		BwSubmit.setBackground(Color.green);
		pworker.add(BwClear);
		BwClear.addActionListener(this);
		BwClear.setBackground(Color.ORANGE);
		pworker.add(BWorkdays);
		BWorkdays.addActionListener(this);
		BWorkdays.setBackground(Color.green);

		// --------- pWorkdays

		pWorkdays.add(new JLabel("Day :"));
		pWorkdays.add(new JLabel("Month :"));
		pWorkdays.add(new JLabel("Year :"));
		pWorkdays.add(Sday);
		pWorkdays.add(Smonth);
		pWorkdays.add(Syear);
		pWorkdays.add(new JLabel("Choose worker"));
		pWorkdays.add(CHworker);
		pWorkdays.add(BwdAddWorkDay);
		BwdAddWorkDay.addActionListener(this);
		BwdAddWorkDay.setBackground(Color.green);

	}

	private void taskinit() {
		ptask.add(new JLabel("                  Task"));
		ptask.add(new JLabel("Adder"));
		ptask.add(new JLabel());

		ptask.add(CHtWorker);
		CHtWorker.add("Select Worker");

		ptask.add(CHtWD);
		CHtWD.add("Select WD");
		ptask.add(BupdateWorker);
		BupdateWorker.addActionListener(this);
		BupdateWorker.setBackground(Color.green);

		ptask.add(new JLabel("Task Hint"));
		ptask.add(TFtaskHint);
		ptask.add(new JLabel());
		ptask.add(new JLabel("Task Duration"));
		ptask.add(TFtaskDuration);
		ptask.add(new JLabel());
		ptask.add(new JLabel("Task Descriotion"));
		ptask.add(TFdescription);
		ptask.add(new JLabel());
		ptask.add(BtSubmit);
		BtSubmit.addActionListener(this);
		BtSubmit.setBackground(Color.green);
		ptask.add(BtClear);
		BtClear.addActionListener(this);
		BtClear.setBackground(Color.ORANGE);
		ptask.add(new JLabel());
	}

	private void searchinit() {

		pSearch.add(Cworker);
		pSearch.add(new JLabel());
		pSearch.add(BworkerSearch);
		BworkerSearch.addActionListener(this);
		BworkerSearch.setBackground(Color.green);
		pSearch.add(BworkerDelete);
		BworkerDelete.addActionListener(this);
		BworkerDelete.setBackground(Color.RED);

		
		pSearch.add(jlPic );
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// --------------------------------------------------------------------------------------------------------------------
		if (e.getSource().equals(Bclass)) {

			pcenter.removeAll();
			pcenter.add(pclass);
			pcenter.revalidate();
			pcenter.repaint();

			if (cTeacher.getItemCount() == ALw.size())
				isOpenedClass = true;
			else {
				cTeacher.removeAll();
				for (Worker w : ALw)
					cTeacher.add(w.getName());
			}

		}

		if (e.getSource().equals(BcSubmit)) {
			GardenDBH GDB = new GardenDBH();
			GardenClass c = new GardenClass(Integer.parseInt(TFclassNum.getText()),
					Integer.parseInt(TFclassQTY.getText()));
			for (Worker w : ALw) {
				if (w.getName().equals(cTeacher.getSelectedItem())) {
					c.setWorker(w);
					try {
						GDB.add(c);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}
			}
			ALcl.add(c);
			System.out.println(c);
		}
		if (e.getSource().equals(BcClear)) {
			TFclassNum.setText("");
			TFclassQTY.setText("");

		}

		// --------------------------------------------------------------------------------------------------------------------

		if (e.getSource().equals(Bchild)) {
			pcenter.removeAll();
			pcenter.add(pchild);
			pcenter.revalidate();
			pcenter.repaint();

			if (Cgarden.getItemCount() == ALcl.size()) {
				isOpenedChild = true;
			} else {
				Cgarden.removeAll();
				for (GardenClass c : ALcl)
					Cgarden.add(Integer.toString(c.getNumber()));

			}
		}

		if (e.getSource().equals(BchClear)) {
			TFchid.setText("");
			TFchname.setText("");
			TFchAge.setText("");

		}

		if (e.getSource().equals(BchSubmit)) {

			Child ch = new Child(TFchid.getText(), TFchname.getText(), Integer.parseInt(TFchAge.getText()));
			System.out.println(ch);

			for (GardenClass cl : ALcl)
				if (Cgarden.getSelectedItem().equals(Integer.toString(cl.getNumber()))) {
					cl.addChild(ch);
					ch.setGarden(cl);
					System.out.println(cl.toString());
					System.out.println("garden classes" + ALcl);
					ChildDBH chdb = new ChildDBH();
					try {
						chdb.add(ch);
					} catch (NumberFormatException | SQLException e1) {

						e1.printStackTrace();
					}
				}

		}

		// --------------------------------------------------------------------------------------------------------------------
		if (e.getSource().equals(Bworker)) {
			pcenter.removeAll();
			pcenter.add(pworker);
			pcenter.revalidate();
			pcenter.repaint();
		}

		if (e.getSource().equals(BwClear)) {
			System.out.println(ALw);
		}

		if (e.getSource().equals(BwSubmit)) // ADD WORKER TO DB
		{

			Worker w = new Worker();
			w.setName(TFwName.getText());

			CHworker.add(w.getName());

			WorkerDBH WDB = new WorkerDBH();
			try {
				WDB.add(w.getName());
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			ALw.clear();
			try {
				if (ALw.isEmpty()) {
					ALw.addAll(new alDBH().GetWorkers());

				}

			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			// *************updating arraylist from database

			System.out.println(w); // WORKER TOSTRING();

		}

		if (e.getSource().equals(BWorkdays)) // OPENS WORKDAYS PANEL ON BOTTOM OF WORKER
		{

			System.out.println(ALw.size());

			pcenter.add(pWorkdays);
			pcenter.revalidate();
			pcenter.repaint();

			if (CHworker.getItemCount() == ALw.size())
				isOpenedworkday = true;

			else {
				CHworker.removeAll();
				for (Worker w : ALw) {
					CHworker.add(w.getName());
				}
			}
			System.out.println(ALw.size());
		}

		if (e.getSource().equals(BwdAddWorkDay)) // ADD TASK TO WORKDAY
		{
			WorkDay wd;
			wd = new WorkDay();
			int year = (Integer) Syear.getValue();
			int month = (Integer) Smonth.getValue();
			int day = (Integer) Sday.getValue();
			System.out.println(year + " " + month + " " + day);
			GregorianCalendar GC = new GregorianCalendar();
			GC.set(GregorianCalendar.YEAR, year);
			GC.set(GregorianCalendar.MONTH, month);
			GC.set(GregorianCalendar.DATE, day);
			wd.setLocalDate(GC);

			for (Worker wrk : ALw) {
				if (wrk.getName().equals(CHworker.getSelectedItem())) {
					wrk.addWorkDays(wd);
					System.out.println(wrk);
				}
			}

			System.out.println(wd.toString());

			ALwd.add(wd);

		}

		// --------------------------------------------------------------------------------------------------------------------

		if (e.getSource().equals(Btask)) // NAV BAR TASK BUTTON
		{
			pcenter.removeAll();
			pcenter.add(ptask);
			pcenter.revalidate();
			pcenter.repaint();

			if (CHtWorker.getItemCount() - 1 == ALw.size())
				isOpened = true;

			else {
				CHtWorker.removeAll();
				for (Worker w : ALw) {
					CHtWorker.add(w.getName());
				}
			}

		}

		if (e.getSource().equals(BupdateWorker)) {
			CHtWD.removeAll();
			for (Worker w : ALw) {
				if (w.getName().equals(CHtWorker.getSelectedItem()))
					for (int i = 0; i < w.getWorkDays().size(); i++)
						CHtWD.add(w.getWorkDays().get(i).date());
			}
		}

		if (e.getSource().equals(BtSubmit)) {
			Task t = new Task(TFtaskHint.getText(), TFdescription.getText(),
					Integer.parseInt(TFtaskDuration.getText()));
			ALta.add(t);

			for (Worker w : ALw) {
				if (w.getName().equals(CHtWorker.getSelectedItem())) {
					for (WorkDay wd : w.getWorkDays()) {
						if (wd.date().equals(CHtWD.getSelectedItem())) {
							wd.addTask(t);
							System.out.println(w.toString());

							try {
								FileControl.saveTask("Tasks", t.saveFormat(), w, wd.date());
							} catch (IOException e1) {
								e1.printStackTrace();
							}

						}
					}
				}

			}

		}

		if (e.getSource().equals(BtClear)) // CLEARS TASK FIELDS
		{
			TFtaskDuration.setText("");
			TFtaskHint.setText("");
			TFdescription.setText("");

		}

		// ---------------------------------------------------------------------------------------------------------------------

		if (e.getSource().equals(Bsearch)) {
			pcenter.removeAll();
			pcenter.add(pSearch);
			pcenter.revalidate();
			pcenter.repaint();

			Cworker.removeAll();
			for (Worker w : ALw) {
				Cworker.add(w.getName());
			}
		}

		if (e.getSource().equals(BworkerSearch)) {
			for (Worker w : ALw) {

				if (w.getName().equals(Cworker.getSelectedItem())) {
					System.out.println(w.getName() + "      ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓");
					for (WorkDay wd : w.getWorkDays()) {

						wd.start();
					}
				}
			}

		}

		if (e.getSource().equals(BworkerDelete)) {
			for (Worker w : ALw) {

				if (w.getName().equals(Cworker.getSelectedItem())) {
					for (WorkDay wd : w.getWorkDays()) {
						System.out.println(wd.getName() + " Stopped");
						wd.stop();
					}
				}
			}
		}
	}

	public static void main(String[] args) throws SQLException, FileNotFoundException {

		view v = new view();
		v.setSize(650, 350);
		v.show();

	}

}
