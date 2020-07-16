package DBH;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Child;
import Model.GardenClass;
import Util.DatabaseConnection;
import Util.FileControl;

public class ChildDBH {

	static Connection con = DatabaseConnection.getConnection();

	public int add(Child ch) throws SQLException {

		String sql = "insert into tblchild(id, cname, age, number) values(?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setInt(1, Integer.parseInt(ch.getId().toString()));
		ps.setString(2, ch.getName());
		ps.setInt(3, ch.getAge());
		ps.setInt(4, ch.getGarden().getNumber());

		int rows = ps.executeUpdate();

		ps.close();

		return rows;

	}


	public void delete(int id) throws SQLException {
		String sql = "delete from tblchild where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
	}

	public Child getChild(int id) throws SQLException {
		String sql = "select * from tblchild where id = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		Child ch = null;
		if (rs.next()) {
			ch = new Child(Integer.toString(id), rs.getString("cname"), rs.getInt("age"));

			for (GardenClass gc : View.view.ALcl) {
				if (gc.getNumber() == rs.getInt("number"))
					ch.setGarden(gc);
			}
		}
		rs.close();
		ps.close();
		return ch;
	}

	public int getChilds(int number) throws SQLException {
		int cnt = 0;
		String sql = "select count(*) from tblchild where number = ?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, number);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			cnt = rs.getInt(1);
		}
		rs.close();
		ps.close();
		return cnt;
	}

	

	public static void main(String[] args) throws SQLException, IOException {

		alDBH al = new alDBH();
		GardenDBH gdb = new GardenDBH();
		View.view.ALcl = gdb.getGardenClasses();
		View.view.ALch = al.getChilds();
		// System.out.println(al.getGardenClasses());
		
		ChildDBH cdb = new ChildDBH();
		// cdb.add(333, "amirrr", 12, 666);
		//System.out.println(cdb.getChild(123));
		//FileControl.save("childs",View.view.ALch);
		
		//gdb.add(777, 12, 4);
		
		//WorkerDBH wdb = new WorkerDBH();
		//wdb.add("alam");
		
		
		//cdb.add(333, "amirrr", 12, 777);
		
	}

}
