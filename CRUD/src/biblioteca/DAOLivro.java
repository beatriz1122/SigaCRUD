package biblioteca;

import java.util.List;

public interface DAOLivro {

	void create(Livro l);
	List<Livro> pesquisarPorNome(String nome);
	
	void delete(Livro l);
	
	void atualizar(String nomeAntigo, Livro l);
}

