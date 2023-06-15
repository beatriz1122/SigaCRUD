package biblioteca;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SalaControl {

	private StringProperty sa = new SimpleStringProperty("");
	private StringProperty ti = new SimpleStringProperty("");
	
	private  SalaDAO daoSala = new SalaDAO();
	
	private boolean  editando = false;
	private String nomeAntigo = null;
	private ObservableList<Sala> lista = FXCollections.observableArrayList();
	
	public Sala getEntity() {
		
		Sala s = new Sala ();
		s.setSa(sa.get());
		s.setTi(ti.get());
		return s;
	}
	
	public void setEntity(Sala s) {
		sa.set(s.getSa());
		ti.set(s.getTi());
	}
	
	public void limpar() {
		
		sa.set("");
		ti.set("");
	}
	
	public void editar () {
		this.editando = true;
		this.nomeAntigo = sa.get();
	}
	
	public void adicionar () {
		Sala s = getEntity();
		if (this.editando) {
			daoSala.atualizar(nomeAntigo,s);
		} else {
		
		daoSala.create(s);
		}
	}
	
	public void pesquisar() {
		
	List<Sala> tempLista = daoSala.pesquisarPorNome(sa.get());
	lista.clear();
	lista.addAll(tempLista);
	}
	
	public void delete (Sala s) {
		
		daoSala.delete(s);
		
	}
	
	public StringProperty saProperty() {
		
		return sa;
		
	}
	
	public StringProperty tiProperty() {
		
		return ti;
		
	}

	public ObservableList<Sala> getLista() {
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
