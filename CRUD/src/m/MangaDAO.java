package m;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MangaDAO implements DAOManga{
	
	public final static String URL = "jdbc:mariadb://localhost:3306/bibli";
	public final static String user = "root";
	public final static String pass = "";
	private Connection con;

	public MangaDAO() {
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(URL,user,pass);
			
	}catch(ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
		 	
}
	
	@Override
	public void create (Manga m) {
		String sql = "INSERT INTO manga (quadri,magaka,shoujo)"
		+ "VALUES ('"+ m.getQuadri()+"', '"+m.getMagaka()+"','"+m.getShoujo()+"') ";	 	
	 	
			try {	
					PreparedStatement pstmt = con.prepareStatement(sql); 
					pstmt.executeUpdate();
					
	 		}catch (SQLException e) {
	 			e.printStackTrace();
	 		}
	}


	@Override
	public List<Manga> pesquisarPorNome(String quadri) {
		List <Manga> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM manga WHERE quadri LIKE '%"+ quadri +"%' ";
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			
			Manga m = new Manga();
			m.setQuadri(rs.getString("quadri"));
			m.setMagaka(rs.getString("magaka"));
			m.setShoujo(rs.getString("shoujo"));
			
			lista.add(m);
		}
		
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void delete(Manga m) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM manga WHERE quadri = '"+ m.getQuadri()+"'";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
	}

	@Override
	public void atualizar(String nomeAntigo, Manga m) {
		String sql = "UPDATE manga SET quadri = '"+ m.getQuadri()+"', magaka = '"+m.getMagaka()+"', shoujo = '"+m.getShoujo()+"' WHERE quadri = '"+ nomeAntigo +"' ";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}		
	}

}