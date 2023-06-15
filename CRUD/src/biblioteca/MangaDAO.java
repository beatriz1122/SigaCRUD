package biblioteca;

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
		String sql = "INSERT INTO mangas (mnome,mautor,meditora)"
		+ "VALUES ('"+ m.getMNome()+"', '"+m.getMAutor()+"','"+m.getMEditora()+"') ";	 	
	 	
			try {	
					PreparedStatement pstmt = con.prepareStatement(sql); 
					pstmt.executeUpdate();
					
	 		}catch (SQLException e) {
	 			e.printStackTrace();
	 		}
	}


	@Override
	public List<Manga> pesquisarPorNome(String mnome) {
		List <Manga> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM mangas WHERE mnome LIKE '%"+ mnome +"%' ";
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			
			Manga m = new Manga();
			m.setMNome(rs.getString("nome"));
			m.setMAutor(rs.getString("autor"));
			m.setMEditora(rs.getString("editora"));
			
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
		String sql = "DELETE FROM mangas "
				+ "WHERE mnome = '"+ m.getMNome()+"'";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
	}

	@Override
	public void atualizar(String nomeAntigo, Manga m) {
		String sql = "UPDATE mangas SET mnome = '"+ m.getMNome()+"', mautor = '"+m.getMAutor()+"', meditora = '"+m.getMEditora()+"' WHERE mnome = '"+ nomeAntigo +"' ";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
		
	}


}
