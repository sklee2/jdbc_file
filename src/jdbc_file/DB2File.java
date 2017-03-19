package jdbc_file;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB2File {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String DB_URL = "jdbc:postgresql://localhost/tulip";
		String DB_USER = "tulip";
		String DB_PASSWORD = "tulip";

		Connection conn = null;

		try {
			// 드라이버를 로딩한다.
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e ) {
			e.printStackTrace();
		}

		try {
			// 데이터베이스의 연결을 설정한다.
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

				

			PreparedStatement ps = conn.prepareStatement("SELECT photo FROM st_user_photo WHERE user_id = ?");
			ps.setString(1, "000A011982");
			ResultSet rs = ps.executeQuery();
			
			System.out.println(ps);
			
			while (rs.next()) {
			    byte[] imgBytes = rs.getBytes(1);
			    // use the data in some way here
			    FileOutputStream fos = new FileOutputStream("skee.jpg");
			    fos.write(imgBytes);
			    fos.close();
			}
			rs.close();
			ps.close();
			



						

		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			try {
				// Connection를 닫는다.
				conn.close();
			} catch ( SQLException e ) {}
		}
	}

}
