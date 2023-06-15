package m;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MangaControl {

	private StringProperty quadri = new SimpleStringProperty("");
	private StringProperty magaka = new SimpleStringProperty("");
	private StringProperty shoujo = new SimpleStringProperty("");
	
	private  MangaDAO daoManga = new MangaDAO();
	
	private boolean  editando = false;
	private String nomeAntigo = null;
	private ObservableList<Manga> lista = FXCollections.observableArrayList();
	
	public Manga getEntity() {
		
		Manga m = new Manga ();
		m.setQuadri(quadri.get());
		m.setMagaka(magaka.get());
		m.setShoujo(shoujo.get());
		return m;
	}
	
	public void setEntity(Manga m) {
		quadri.set(m.getQuadri());
		magaka.set(m.getMagaka());
		shoujo.set(m.getShoujo());
		
	}
	
	public void limpar() {
		
		quadri.set("");
		magaka.set("");
		shoujo.set("");
	}
	
	public void editar () {
		this.editando = true;
		this.nomeAntigo = quadri.get();
	}
	
	public void adicionar () {
		Manga m = getEntity();
		if (this.editando) {
			daoManga.atualizar(nomeAntigo,m);
		} else {
		
		daoManga.create(m);
		}
	}
	
	public void pesquisar() {
		
	List<Manga> tempLista = daoManga.pesquisarPorNome(quadri.get());
	lista.clear();
	lista.addAll(tempLista);
	}
	
	public void delete (Manga m) {
		
		daoManga.delete(m);
		
	}
	
	public StringProperty quadriProperty() {
		
		return quadri;
		
	}
	
	public StringProperty magakaProperty() {
		
		return magaka;
		
	}

	public StringProperty shoujoProperty() {
	
	return shoujo;
	
	}
	public ObservableList<Manga> getLista() {
		return lista;
	}

	public boolean isEditando() {
		return editando;
	}

	public void setEditando(boolean editando) {
		this.editando = editando;
	}

	public String getNomeAntigo() {
		return nomeAntigo;
	}

	public void setNomeAntigo(String nomeAntigo) {
		this.nomeAntigo = nomeAntigo;
	}
	


}
