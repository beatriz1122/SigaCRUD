package s;

import java.util.List;

public interface DaoSala {
	
	void create(Sala s);
	List<Sala> pesquisarPorNome(String sa);
	
	void delete(Sala s);
	
	void atualizar(String nomeAntigo, Sala s);
	
}
