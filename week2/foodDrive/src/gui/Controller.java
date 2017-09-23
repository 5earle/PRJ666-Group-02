package gui;

import admin.adminController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Labeled;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import database.dbConnection;
import database.loginType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import user.userController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.sun.javaws.ui.SplashScreen.hide;


//fx:controller="gui.Controller"
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
    @FXML
    private Labeled errorMsg;


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


    public void closeLoginScene(ActionEvent event){
        Platform.exit();
    }
    @FXML
    public void loginMethod(ActionEvent event){
        String username = this.username.getText();
        String password = this.password.getText();
        String option = this.combo.getValue().toString();



        try {
            if(db.isLogin(username,password,option)){

                Stage stage = (Stage)this.okbtn.getScene().getWindow();
                stage.close();


                switch (option){
                    case "ADMIN":
                        adminLogin();
                        break;
                    case "USER":
                        userLogin();
                        break;
                }
            } else{
                this.errorMsg.setText("wrong information");
                System.out.print(username+" "+password+" "+option);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void userLogin(){
        try {
            Stage userStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            Pane root = (Pane)loader.load(getClass().getResource("/user/userFXML.fxml").openStream());

            //attach usercontroller to user fxml
            userController uc =(userController)loader.getController();

            Scene scene = new Scene(root);
            userStage.setScene(scene);
            userStage.setTitle("User DashBoard");
            userStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void adminLogin() throws IOException {
        Stage userStage = new Stage();
        FXMLLoader loader = new FXMLLoader();
        Pane root = (Pane) loader.load(getClass().getResource("/admin/adminFXML.fxml").openStream());

        //attach admincontroller to asmin fxml
        adminController ac =(adminController)loader.getController();

        Scene scene = new Scene(root);
        userStage.setScene(scene);
        userStage.setTitle("Admin DashBoard");
        userStage.show();
    }


}


