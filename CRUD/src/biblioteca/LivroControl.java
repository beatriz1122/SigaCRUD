package biblioteca;


import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LivroControl {

	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty autor = new SimpleStringProperty("");
	private StringProperty editora = new SimpleStringProperty("");
	
	private  LivroDAO daoLivro = new LivroDAO();
	
	private boolean  editando = false;
	private String nomeAntigo = null;
	private ObservableList<Livro> lista = FXCollections.observableArrayList();
	
	public Livro getEntity() {
		
		Livro l = new Livro ();
		l.setNome(nome.get());
		l.setAutor(autor.get());
		l.setEditora(editora.get());
		return l;
	}
	
	public void setEntity(Livro l) {
		nome.set(l.getNome());
		autor.set(l.getAutor());
		editora.set(l.getEditora());
		
	}
	
	public void limpar() {
		
		nome.set("");
		autor.set("");
		editora.set("");
	}
	
	public void editar () {
		this.editando = true;
		this.nomeAntigo = nome.get();
	}
	
	public void adicionar () {
		Livro l = getEntity();
		if (this.editando) {
			daoLivro.atualizar(nomeAntigo,l);
		} else {
		
		daoLivro.create(l);
		}
	}
	
	public void pesquisar() {
		
	List<Livro> tempLista = daoLivro.pesquisarPorNome(nome.get());
	lista.clear();
	lista.addAll(tempLista);
	}
	
	public void delete (Livro l) {
		
		daoLivro.delete(l);
		
	}
	
	public StringProperty nomeProperty() {
		
		return nome;
		
	}
	
	public StringProperty autorProperty() {
		
		return autor;
		
	}

	public StringProperty editoraProperty() {
	
	return editora;
	
	}
	public ObservableList<Livro> getLista() {
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
