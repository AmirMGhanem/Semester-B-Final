package DBH;

import Util.*;
import Model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

public class WorkerDBH {

	static Connection con = DatabaseConnection.getConnection();

	public int add(String name) throws SQLException {

		String sql = "insert into tblworker(name) values (?)";
		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, name);
		int rows = ps.executeUpdate();
		ps.close();

		return rows;

	}

	public void delete(int id) throws SQLException {

		String sql = "delete from tblworker where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();

	}

	public Worker GetWorkers(String name) throws SQLException {

		String sql = "select * from tblworker where name = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, name);
		ResultSet rs = ps.executeQuery();

		Worker worker = null;
		if (rs.next())
			worker = new Worker(rs.getInt("id"), name);
		rs.close();
		ps.close();

		return worker;

	}

	public List<Worker> GetWorkers() throws SQLException {

		List<Worker> list = new Vector<>();
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

	


}
