package modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BddConnection {
	
	private static Connection cnn = null;
	
	private BddConnection() {
		String server = "localhost:3306";
		String database = "productos";
		String user = "root";
		String password = "";
		
		String url = "jdbc:mysql://" + server + "/" + database;
		
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			cnn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConexion() {
		if(cnn == null) 
			new BddConnection();
		return cnn;
		
	}
	
	public static void cerrar (ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		rs = null;
	}
	
	public static void cerrar(PreparedStatement pstat) {
		try {
			pstat.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// cerrar la conexion si esta activa
	public static void cerrar() {
		if(cnn != null) {
			try {
				cnn.close();
				cnn = null;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
