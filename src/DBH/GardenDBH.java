package DBH;

import Util.*;
import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GardenDBH {

	static Connection con = DatabaseConnection.getConnection();

	public int add(GardenClass gc) throws SQLException {

		String sql = "insert into tblgarden(number, qty, id) values(?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, gc.getNumber());
		ps.setInt(2, gc.getQuantity());
		ps.setInt(3, gc.getWorker().getId());

		int row = ps.executeUpdate();

		ps.close();

		return row;

	}

	public void delete(int number) throws SQLException {

		String sql = "delete from tblgarden where number = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, number);

		ps.executeUpdate();
		ps.close();
	}

	public GardenClass GetGardenClass(int number) throws SQLException {
		String sql = "select * from tblgarden where number = ?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, number);
		ResultSet rs = ps.executeQuery();

		GardenClass gc = null;
		if (rs.next()) {
			gc = new GardenClass(rs.getInt("qty"), number);

			for (Worker w : View.view.ALw) {
				if (rs.getInt("id") == w.getId())
					gc.setWorker(w);
			}
		}
		rs.close();
		ps.close();
		System.out.println(gc);
		return gc;

	}

	public ArrayList<GardenClass> getGardenClasses() throws SQLException{
		
		ArrayList<GardenClass> list = new ArrayList<>();
		String sql = "select * from tblgarden";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
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

	public static void main(String[] args) throws SQLException {

		// -------------------------------------------------------------------------------------------------------------
		alDBH al = new alDBH();
		View.view.ALw = al.GetWorkers();
		System.out.println(al.GetWorkers());
		//****************************************
		GardenDBH gdb = new GardenDBH();
		View.view.ALcl = gdb.getGardenClasses();
		System.out.println(al.getGardenClasses());
		// -------------------------------------------------------------------------------------------------------------

		WorkerDBH wdb = new WorkerDBH();
		
		
		//gdb.add(777, 12, 3);
		// gdb.GetGardenClass(666);

	}

}
