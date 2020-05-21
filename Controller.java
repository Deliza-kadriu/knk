import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;

public class Controller implements Initializable {

	
	@FXML
    private TableView<?> TreeTableView;

    @FXML
    private TreeTableColumn<?, ?> PnameCol;

    @FXML
    private TreeTableColumn<?, ?> DnameCol;

    @FXML
    private TreeTableColumn<?, ?> AgeCol;

    @FXML
    private TreeTableColumn<?, ?> DateCol;

    @FXML
    private TreeTableColumn<?, ?> TypeCol;

    @FXML
    private TextField PnameTF;

    @FXML
    private TextField DnameTF;

    @FXML
    private TextField AgeTF;

    @FXML
    private ChoiceBox<?> TypeTF;

    @FXML
    private DatePicker DateTF;

    @FXML
    private Label PnameLB;

    @FXML
    private Label DnameLB;

    @FXML
    private Label AgeLB;

    @FXML
    private Label DateLB;

    @FXML
    private Label TypeLB;



	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

	
	
	
}
