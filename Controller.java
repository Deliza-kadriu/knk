package ordinance;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableCell;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class Controller implements Initializable {

	
	@FXML
    private JFXTreeTableView<Model> TreeTableView;

    @FXML
    private TreeTableColumn<Model, String> nameCol;

    @FXML
    private TreeTableColumn<Model, String> dnameCol;

    @FXML
    private TreeTableColumn<Model, String> ageCol;

    @FXML
    private TreeTableColumn<Model, String> dateCol;

    @FXML
    private TreeTableColumn<Model, String> typeCol;

    @FXML
    private TreeTableColumn<Model, String> resultCol;

    @FXML
    private JFXTextField searchTF;

    @FXML
    private JFXTextField dnameTF;

    @FXML
    private JFXTextField nameTF;

    @FXML
    private JFXTextField ageTF;

    @FXML
    private JFXTextField resultTF;

    @FXML
    private JFXTextField dateTF;

    @FXML
    private JFXComboBox<String> typeTF;

    @FXML
    private Label nameLB;

    @FXML
    private Label dnameLB;

    @FXML
    private Label ageLB;

    @FXML
    private Label dateLB;

    @FXML
    private Label typeLB;

    @FXML
    private Label resultLB;
    
    ObservableList<Model> list;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		typeTF.getItems().addAll("1","2","3");

        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().name;
            }
        });

        
        dnameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().dname;
            }
        });

        ageCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().age;
            }
        });
        
        dateCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().date;
            }
        });
        
        typeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().type;
            }
        });
        
        resultCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Model, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Model, String> param) {
                return param.getValue().getValue().result;
            }
        });
        
        list = FXCollections.observableArrayList();
        TreeItem<Model> root = new RecursiveTreeItem<Model>(list, RecursiveTreeObject:: getChildren);
        TreeTableView.setRoot(root);
        TreeTableView.setShowRoot(false);
        
        list.add(new Model("name", "dname"," age2"," date"," type"," result"));
        list.add(new Model("name1", "dname"," age"," date"," type"," result"));
        list.add(new Model("name2", "dname"," age"," date"," type"," result"));

        
        searchTF.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                TreeTableView.setPredicate(new Predicate<TreeItem<Model>>() {
                    @Override
                    public boolean test(TreeItem<Model> modelTreeItem) {
                        return modelTreeItem.getValue().name.getValue().contains(newValue) |modelTreeItem.getValue().age.getValue().contains(newValue) ;
                    }
                });
            }
        });
        
        TreeTableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showDetails(newValue);
        });


      

    }
	
	
	
	@FXML
	void addAction(ActionEvent event) {
		list.addAll(new Model(nameTF.getText(),dnameTF.getText(),ageTF.getText(),dateTF.getText(),typeTF.getSelectionModel().getSelectedItem(),resultTF.getText()));     
	}
	
	 @FXML
	    void  deleteAction(ActionEvent event){
	        int index=TreeTableView.getSelectionModel().getSelectedIndex();
	        list.remove(index);
	        clearFields();
	      
	    }
	 
	 public void showDetails(TreeItem<Model> treeItem)  {

		 String a = treeItem.getValue().getName().get();
	        nameTF.setText(a);
	        nameLB.setText(a);

	        dnameTF.setText(treeItem.getValue().getDname().get());
	        dnameLB.setText(dnameTF.getText());
	        
	        dateTF.setText(treeItem.getValue().getDate().get());
	        dateLB.setText(treeItem.getValue().getDate().get());

	        ageTF.setText(treeItem.getValue().getAge().get());
	        ageLB.setText(treeItem.getValue().getAge().get());

	        typeTF.getSelectionModel().select(treeItem.getValue().getType().get());
	        typeLB.setText(treeItem.getValue().getType().get());
	        
	        resultTF.setText(treeItem.getValue().getResult().get());
	        resultLB.setText(treeItem.getValue().getResult().get());


	}
	 
	 public void clearFields(){
	        nameTF.setText("");
	        nameLB.setText("");
	        dnameTF.setText("");
	        dnameLB.setText("");
	        dateTF.setText("");
	        dateLB.setText("");
	        resultTF.setText("");
	        resultLB.setText("");
	        ageTF.setText("");
	        ageLB.setText("");
	        typeLB.setText("");
	        typeTF.getSelectionModel().select("");
	    }

	    @FXML
	    void clearAction(ActionEvent event){
	        clearFields();
	    }

	    @FXML
	    void editAction(ActionEvent event) {
	    	TreeItem<Model> treeItem = TreeTableView.getSelectionModel().getSelectedItem();
	    
	    	Model m = new Model(nameTF.getText(), dnameTF.getText(), ageTF.getText(), dateTF.getText(), typeTF.getSelectionModel().getSelectedItem(), resultTF.getText());
	    	treeItem.setValue(m);
	    }
	    
	}











