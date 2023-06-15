package c;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComDAO implements DAOCom {
	public final static String URL = "jdbc:mariadb://localhost:3306/bibli";
	public final static String user = "root";
	public final static String pass = "";
	private Connection con;

	public ComDAO() {
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(URL,user,pass);
			
	}catch(ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
		 	
}
	
	@Override
	public void create (Com c) {
		String sql = "INSERT INTO computador (marca,modelo,fabricante)"
		+ "VALUES ('"+ c.getMarca()+"', '"+c.getModelo()+"','"+c.getFabricante()+"') ";	 	
	 	
			try {	
					PreparedStatement pstmt = con.prepareStatement(sql); 
					pstmt.executeUpdate();
					
	 		}catch (SQLException e) {
	 			e.printStackTrace();
	 		}
	}


	@Override
	public List<Com> pesquisarPorNome(String marca) {
		List <Com> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM computador WHERE marca LIKE '%"+ marca +"%' ";
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			
			Com c = new Com();
			c.setMarca(rs.getString("marca"));
			c.setModelo(rs.getString("modelo"));
			c.setFabricante(rs.getString("fabricante"));
			
			lista.add(c);
		}
		
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void delete(Com c) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM computador "
				+ "WHERE marca = '"+ c.getMarca()+"'";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
	}

	@Override
	public void atualizar(String nomeAntigo, Com c) {
		String sql = "UPDATE computador SET marca = '"+ c.getMarca()+"', modelo = '"+c.getModelo()+"', fabricante = '"+c.getFabricante()+"' WHERE marca = '"+ nomeAntigo +"' ";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
		
	}
}
