package gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.dbConnection;
import model.loginType;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    dbConnection db;


    @FXML
    private TextField username;;
    @FXML
    private PasswordField password;
    @FXML
    private ComboBox combo;
    @FXML
    private Labeled status;
    @FXML
    private Button okbtn;
    @FXML
    private Button canbtn;


    public Controller(){
       db = new dbConnection();
       combo = new ComboBox();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
         if(db.isDatabaseCon()){
            this.status.setText("Connected");
         }
         else{
             this.status.setText("Not Connected");
         }

         combo.getItems().addAll(loginType.values());
         combo.setValue("Admin/User");
    }

}
