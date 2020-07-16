package DBH;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import Model.Child;
import Model.GardenClass;
import Model.Worker;
import Util.DatabaseConnection;

public class alDBH {
	static Connection con = DatabaseConnection.getConnection();

	public ArrayList<Worker> GetWorkers() throws SQLException {

		ArrayList<Worker> list = new ArrayList<>();
		String sql = "select * from tblworker";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Worker worker = new Worker(rs.getInt("id"), rs.getString("name"));
			list.add(worker);
		}
		rs.close();
		ps.close();

		return list;
	}

	public ArrayList<GardenClass> getGardenClasses() throws SQLException {

		ArrayList<GardenClass> list = new ArrayList<>();
		String sql = "select * from tblgarden";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			GardenClass gc = new GardenClass(rs.getInt("number"), rs.getInt("qty"));
			for (Worker w : View.view.ALw) {
				if (rs.getInt("id") == w.getId())
					gc.setWorker(w);
			}
			list.add(gc);
		}
		rs.close();
		ps.close();
		return list;
	}
	

	public ArrayList<Child> getChilds() throws SQLException{
		ArrayList<Child> list = new ArrayList<>();
		String sql = "select * from tblchild";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Child ch = new Child(Integer.toString(rs.getInt("id")), rs.getString("cname"), rs.getInt("age"));

			for (GardenClass gc : View.view.ALcl) {
				if (gc.getNumber() == rs.getInt("number"))
					ch.setGarden(gc);
			}
			list.add(ch);
		}
		rs.close();
		ps.close();
		return list;
	}

	
	
}
