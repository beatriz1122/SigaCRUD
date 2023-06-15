package biblioteca;

import java.util.List;

public interface DAOManga {
	void create(Manga m);
	List<Manga> pesquisarPorNome(String mnome);
	
	void delete(Manga m);
	
	void atualizar(String nomeAntigo, Manga m);

}
