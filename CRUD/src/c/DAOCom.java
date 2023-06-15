package c;

import java.util.List;

public interface DAOCom {

	void create(Com c);
	List<Com> pesquisarPorNome(String marca);
	
	void delete(Com c);
	
	void atualizar(String nomeAntigo, Com c);
}
