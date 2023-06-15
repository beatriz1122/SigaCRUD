package biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// Classe de conecção banco e conecção com o banco

public class Conect {

	// configurando local do banco, usuario e senha
	public final static String URL = "jdbc:mariadb://localhost:3306/bibli";
	public final static String user = "root";
	public final static String pass = "";
	
	 public static void main (String[] args) {
		 
		 try {
			 /* conecta classe banco*/
		 Class.forName("org.mariadb.jdbc.Driver");
		
		 	System.out.println("classe conectada ");
		 	
		 	// conecta o banco
		 	Connection con = DriverManager.getConnection(URL,user,pass);
		
		 	System.out.println("banco conctado");
		
 //comanto para inserir dados diretamente no banco
		String sql = "INSERT INTO livros (nome,autor,editora)"
			+ "VALUES ('Caraval', 'Stefany Garber','novoconceito') ";	 	
 			PreparedStatement pstmt = con.prepareStatement(sql); 
		 	pstmt.executeUpdate(); 
		 	
		 	} catch (ClassNotFoundException | SQLException e) {
		 		e.printStackTrace();
		 }
	 }
}
