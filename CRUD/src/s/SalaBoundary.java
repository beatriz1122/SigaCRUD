package s;

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

public class SalaBoundary extends Application {

	/* Caixa de texto*/
private TextField txtSa = new TextField("");
private TextField txtTi = new TextField("");


private SalaControl control = new SalaControl();

private TableView<Sala> table = new TableView<>();

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
gp.add(new Label("Nome "), 0, 0);
gp.add(txtSa, 1, 0);
gp.add(new Label("Tipo de Sala "), 0, 1);
gp.add(txtTi, 1, 1);



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
stage.setTitle(" Salas ");
stage.show();

}

private void prepararTable() {
TableColumn<Sala, String> col1 = new TableColumn<>("Sa");
col1.setCellValueFactory(new PropertyValueFactory<Sala, String>("sa"));

TableColumn<Sala, String> col2 = new TableColumn<>("Ti");
col2.setCellValueFactory(new PropertyValueFactory<Sala, String>("ti"));


TableColumn<Sala, String> col3 = new TableColumn<>("Ações");
//col4.setCellValueFactory(new PropertyValueFactory<Livro, String>("DUMMY"));

Callback<TableColumn<Sala, String>, TableCell<Sala, String>> cellFactory
= // 
new Callback<TableColumn<Sala, String>, TableCell<Sala, String>>(){

@Override 
public TableCell call(final TableColumn<Sala, String> param) {
final TableCell<Sala, String> cell =new TableCell<Sala, String>(){
		
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
					Sala s = getTableView().getItems().get(getIndex());
					control.delete(s);
					control.limpar();
					control.pesquisar();
				});
				btnEditar.setOnAction(event -> {
					Sala s = getTableView().getItems().get(getIndex());
					control.setEntity(s);
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
col3.setCellFactory(cellFactory);


table.getColumns().clear();
table.getColumns().addAll(col1,col2,col3);

table.setItems(control.getLista());

table.getSelectionModel().selectedItemProperty()
.addListener((prop, antiga, novo) -> {

control.setEntity(novo);

});

}

public void vincular () {

Bindings.bindBidirectional(control.saProperty(), txtSa.textProperty());
Bindings.bindBidirectional(control.tiProperty(), txtTi.textProperty());
}

public static void main(String[] args) {

Application.launch(SalaBoundary.class, args);

}
}
