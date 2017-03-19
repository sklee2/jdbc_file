package jdbc_file;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class File2DB {

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

				
			File file = new File("000A011982");
			FileInputStream fis = new FileInputStream(file);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO ST_USER_PHOTO  VALUES (?, ?,?) ");
			
			ps.setString(1, file.getName());
			ps.setLong(2, file.length());
			ps.setBinaryStream(3, fis, (int)file.length());
			
			System.out.println(ps);

			ps.executeUpdate();
			ps.close();
			fis.close();
						

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
