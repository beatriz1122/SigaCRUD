package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaDAO implements DaoSala {
	
	public final static String URL = "jdbc:mariadb://localhost:3306/bibli";
	public final static String user = "root";
	public final static String pass = "";
	private Connection con;

	public SalaDAO() {
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(URL,user,pass);
			
	}catch(ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
		 	
}
	
	@Override
	public void create (Sala s) {
		String sql = "INSERT INTO salas (sa,ti)"
		+ "VALUES ('"+ s.getSa()+"', '"+s.getTi()+"') ";	 	
	 	
			try {	
					PreparedStatement pstmt = con.prepareStatement(sql); 
					pstmt.executeUpdate();
					
	 		}catch (SQLException e) {
	 			e.printStackTrace();
	 		}
	}


	@Override
	public List<Sala> pesquisarPorNome(String sa) {
		List <Sala> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM salas WHERE sa LIKE '%"+ sa +"%' ";
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			
			Sala s = new Sala();
			s.setSa(rs.getString("sa"));
			s.setTi(rs.getString("ti"));
			
			
			lista.add(s);
		}
		
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void delete(Sala s) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM salas WHERE sa = '" + s.getSa() + "'" ; 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
	}

	@Override
	public void atualizar(String nomeAntigo, Sala s) {
		String sql = "UPDATE salas SET sa = '"+ s.getSa()+"', ti = '"+s.getTi()+"' WHERE salas = '"+ nomeAntigo +"' ";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
		
	}



}
