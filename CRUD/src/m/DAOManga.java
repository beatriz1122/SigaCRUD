package m;

import java.util.List;

public interface DAOManga {
	
	void create(Manga m);
	List<Manga> pesquisarPorNome(String quadri);
	
	void delete(Manga m);
	
	void atualizar(String nomeAntigo, Manga m);
}

