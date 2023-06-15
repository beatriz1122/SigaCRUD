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

public class ComBoundary extends Application {

	/* Caixa de texto*/
private TextField txtMarca = new TextField("");
private TextField txtModelo = new TextField("");
private TextField txtFabr = new TextField("");

private ComControl control = new ComControl();

private TableView<Com> table = new TableView<>();

		/*Botões*/
private Button btnAdicionar = new Button ("Adicionar");

private Button btnExcluir = new Button ("Excluir");
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
gp.add(new Label("Marca "), 0, 0);
gp.add(txtMarca, 1, 0);
gp.add(new Label("Modelo "), 0, 1);
gp.add(txtModelo, 1, 1);
gp.add(new Label("Fabricante "), 0, 2);
gp.add(txtFabr, 1, 2);


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
stage.setTitle(" Computadores ");
stage.show();

}

private void prepararTable() {
TableColumn<Com, String> col1 = new TableColumn<>("Marca");
col1.setCellValueFactory(new PropertyValueFactory<Com, String>("marca"));

TableColumn<Com, String> col2 = new TableColumn<>("Modelo");
col2.setCellValueFactory(new PropertyValueFactory<Com, String>("modelo"));

TableColumn<Com, String> col3 = new TableColumn<>("Fabricante");
col3.setCellValueFactory(new PropertyValueFactory<Com, String>("fabricante"));

TableColumn<Com, String> col4 = new TableColumn<>("Ações");
//col4.setCellValueFactory(new PropertyValueFactory<Livro, String>("DUMMY"));

Callback<TableColumn<Com, String>, TableCell<Com, String>> cellFactory
= // 
new Callback<TableColumn<Com, String>, TableCell<Com, String>>(){

@Override 
public TableCell call(final TableColumn<Com, String> param) {
final TableCell<Com, String> cell =new TableCell<Com, String>(){
		
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
					Com c = getTableView().getItems().get(getIndex());
					control.delete(c);
					control.limpar();
					control.pesquisar();
				});
				btnEditar.setOnAction(event -> {
					Com c = getTableView().getItems().get(getIndex());
					control.setEntity(c);
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

Bindings.bindBidirectional(control.nomeProperty(), txtMarca.textProperty());
Bindings.bindBidirectional(control.autorProperty(), txtModelo.textProperty());
Bindings.bindBidirectional(control.editoraProperty(), txtFabr.textProperty());
}

public static void main(String[] args) {

Application.launch(ComBoundary.class, args);

}

}
