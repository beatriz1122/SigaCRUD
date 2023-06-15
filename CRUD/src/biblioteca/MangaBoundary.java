package biblioteca;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MangaBoundary extends Application {
	

	/* Caixa de texto*/
private TextField txtNome = new TextField("");
private TextField txtAutor = new TextField("");
private TextField txtEditora = new TextField("");

private MangaControl control = new MangaControl();

private TableView<Manga> table = new TableView<>();

		/*Botões*/
private Button btnAdicionar = new Button ("Adicionar");
//private Button btnExcluir = new Button ("Excluir");
private Button btnPesquisar = new Button ("Pesquisar");

@Override
public void start(Stage stage) throws Exception {
// TODO Auto-generated method stub
BorderPane bp = new BorderPane();
GridPane gp = new GridPane();

Scene scn = new Scene (bp, 400,300);
bp.setTop(gp);
bp.setCenter(table);

prepararTable();

/* Enquadrando caixas de texto*/
gp.add(new Label("Nome "), 0, 0);
gp.add(txtNome, 1, 0);
gp.add(new Label("Autor(a) "), 0, 1);
gp.add(txtAutor, 1, 1);
gp.add(new Label("Editora "), 0, 2);
gp.add(txtEditora, 1, 2);


/* Enquadrando botões*/
gp.add(btnAdicionar, 3, 0);

gp.add(btnPesquisar, 3, 2);


btnAdicionar.setOnAction(e -> {
control.adicionar();
control.limpar();
control.pesquisar();
});
btnPesquisar.setOnAction(e -> control.pesquisar());

vincular();

stage.setScene(scn);
stage.setTitle(" Manga ");
stage.show();

}

private void prepararTable() {
TableColumn<Manga, String> col1 = new TableColumn<>("Nome");
col1.setCellValueFactory(new PropertyValueFactory<Manga, String>("mnome"));

TableColumn<Manga, String> col2 = new TableColumn<>("Autor");
col2.setCellValueFactory(new PropertyValueFactory<Manga, String>("mautor"));

TableColumn<Manga, String> col3 = new TableColumn<>("Editora");
col3.setCellValueFactory(new PropertyValueFactory<Manga, String>("meditora"));

TableColumn<Manga, String> col4 = new TableColumn<>("Ações");
//col4.setCellValueFactory(new PropertyValueFactory<Livro, String>("DUMMY"));

Callback<TableColumn<Manga, String>, TableCell<Manga, String>> cellFactory
= // 
new Callback<TableColumn<Manga, String>, TableCell<Manga, String>>(){

@Override 
public TableCell call(final TableColumn<Manga, String> param) {
final TableCell<Manga, String> cell =new TableCell<Manga, String>(){
		
		final Button btnEditar =new Button("Editar");
		final Button btn =new Button("Apagar");
		
		
@Override
public void updateItem(String item, boolean empty) {
	super.updateItem(item, empty);
		if (empty) {
			setGraphic(null);
			setText(null);
			} else {
				btn.setOnAction(event -> {
					Manga m = getTableView().getItems().get(getIndex());
					control.delete(m);
					control.limpar();
					control.pesquisar();
				});
				btnEditar.setOnAction(event -> {
					Manga m = getTableView().getItems().get(getIndex());
					control.setEntity(m);
					control.editar();
				});
				FlowPane fpanel = new FlowPane();
				fpanel.getChildren().addAll(btnEditar, btn);
				setGraphic(fpanel);
				setText(null);
			}
	}

};
return cell;
}		
};
col4.setCellFactory(cellFactory);


table.getColumns().clear();
table.getColumns().addAll(col1,col2,col3, col4);

table.setItems(control.getLista());

table.getSelectionModel().selectedItemProperty()
.addListener((prop, antiga, novo) -> {

control.setEntity(novo);

});

}

public void vincular () {

Bindings.bindBidirectional(control.nomeProperty(), txtNome.textProperty());
Bindings.bindBidirectional(control.autorProperty(), txtAutor.textProperty());
Bindings.bindBidirectional(control.editoraProperty(), txtEditora.textProperty());
}

public static void main(String[] args) {

Application.launch(MangaBoundary.class, args);

}

}