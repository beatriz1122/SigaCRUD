package c;

import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ComControl {

	private StringProperty marca = new SimpleStringProperty("");
	private StringProperty modelo = new SimpleStringProperty("");
	private StringProperty fabricante = new SimpleStringProperty("");
	
	private  ComDAO daoCom = new ComDAO();
	
	private boolean  editando = false;
	private String nomeAntigo = null;
	private ObservableList<Com> lista = FXCollections.observableArrayList();
	
	public Com getEntity() {
		
		Com c = new Com ();
		c.setMarca(marca.get());
		c.setModelo(modelo.get());
		c.setFabricante(fabricante.get());
		return c;
	}
	
	public void setEntity(Com c) {
		marca.set(c.getMarca());
		modelo.set(c.getModelo());
		fabricante.set(c.getFabricante());
		
	}
	
	public void limpar() {
		
		marca.set("");
		modelo.set("");
		fabricante.set("");
	}
	
	public void editar () {
		this.editando = true;
		this.nomeAntigo = marca.get();
	}
	
	public void adicionar () {
		Com c = getEntity();
		if (this.editando) {
			daoCom.atualizar(nomeAntigo,c);
		} else {
		
		daoCom.create(c);
		}
	}
	
	public void pesquisar() {
		
	List<Com> tempLista = daoCom.pesquisarPorNome(marca.get());
	lista.clear();
	lista.addAll(tempLista);
	}
	
	public void delete (Com c) {
		
		daoCom.delete(c);
		
	}
	
	public StringProperty nomeProperty() {
		
		return marca;
		
	}
	
	public StringProperty autorProperty() {
		
		return modelo;
		
	}

	public StringProperty editoraProperty() {
	
	return fabricante;
	
	}
	public ObservableList<Com> getLista() {
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
