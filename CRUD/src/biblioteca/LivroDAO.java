package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO implements DAOLivro {
	
	public final static String URL = "jdbc:mariadb://localhost:3306/bibli";
	public final static String user = "root";
	public final static String pass = "";
	private Connection con;

	public LivroDAO() {
		
		try {
			
			Class.forName("org.mariadb.jdbc.Driver");
			con = DriverManager.getConnection(URL,user,pass);
			
	}catch(ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	}
		 	
}
	
	@Override
	public void create (Livro l) {
		String sql = "INSERT INTO livros (nome,autor,editora)"
		+ "VALUES ('"+ l.getNome()+"', '"+l.getAutor()+"','"+l.getEditora()+"') ";	 	
	 	
			try {	
					PreparedStatement pstmt = con.prepareStatement(sql); 
					pstmt.executeUpdate();
					
	 		}catch (SQLException e) {
	 			e.printStackTrace();
	 		}
	}


	@Override
	public List<Livro> pesquisarPorNome(String nome) {
		List <Livro> lista = new ArrayList<>();
		
		String sql = "SELECT * FROM livros WHERE nome LIKE '%"+ nome +"%' ";
		
		try {
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			
			Livro l = new Livro();
			l.setNome(rs.getString("nome"));
			l.setAutor(rs.getString("autor"));
			l.setEditora(rs.getString("editora"));
			
			lista.add(l);
		}
		
		}catch (SQLException e){
			e.printStackTrace();
		}
		
		return lista;
	}

	@Override
	public void delete(Livro l) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM livros "
				+ "WHERE nome = '"+ l.getNome()+"'";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
	}

	@Override
	public void atualizar(String nomeAntigo, Livro l) {
		String sql = "UPDATE livros SET nome = '"+ l.getNome()+"', autor = '"+l.getAutor()+"', editora = '"+l.getEditora()+"' WHERE nome = '"+ nomeAntigo +"' ";	 	
			 	
					try {	
							PreparedStatement pstmt = con.prepareStatement(sql); 
							pstmt.executeUpdate();
							
			 		}catch (SQLException e) {
			 			e.printStackTrace();
			 		}
		
	}


}
