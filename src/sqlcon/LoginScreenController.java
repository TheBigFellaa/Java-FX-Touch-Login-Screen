/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlcon;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.AnchorPane;
import static sqlcon.SQLCon.statement;

/**
 * FXML Controller class
 *
 * @author theBigFella
 */
public class LoginScreenController implements Initializable {

    public static int inputUserID;
    public static String userName;
    int userLvl;
    public static int kitchenBarID;
    int userNo = 0;
    int rsLenght;
    user[] userArray;

    //@FXML Elements
    @FXML
    private AnchorPane LoginScreen;

    @FXML
    private Label label;

    @FXML
    private Label errorMessage;

    @FXML
    private void buttonZeroClicked(ActionEvent event) throws IOException, SQLException {
        passwordField.appendText("0");
        errorMessage.setText("");
        if (passwordField.getText().length() == 4) {
            submitUser();
        }
    }

    @FXML
    private void buttonOneClicked(ActionEvent event) throws IOException, SQLException {
        passwordField.appendText("1");
        errorMessage.setText("");
        if (passwordField.getText().length() == 4) {
            submitUser();
        }
    }

    @FXML
    private void buttonTwoClicked(ActionEvent event) throws IOException, SQLException {
        passwordField.appendText("2");
        errorMessage.setText("");
        if (passwordField.getText().length() == 4) {
            submitUser();
        }
    }

    @FXML
    private void buttonThreeClicked(ActionEvent event) throws IOException, SQLException {
        passwordField.appendText("3");
        errorMessage.setText("");
        if (passwordField.getText().length() == 4) {
            submitUser();
        }
    }

    @FXML
    private void buttonFourClicked(ActionEvent event) throws IOException, SQLException {
        passwordField.appendText("4");
        errorMessage.setText("");
        if (passwordField.getText().length() == 4) {
            submitUser();
        }
    }

    @FXML
    private void buttonFiveClicked(ActionEvent event) throws IOException, SQLException {
        passwordField.appendText("5");
        errorMessage.setText("");
        if (passwordField.getText().length() == 4) {
            submitUser();
        }
    }

    @FXML
    private void buttonSixClicked(ActionEvent event) throws IOException, SQLException {
        passwordField.appendText("6");
        errorMessage.setText("");
        if (passwordField.getText().length() == 4) {
            submitUser();
        }
    }

    @FXML
    private void buttonSevenClicked(ActionEvent event) throws IOException, SQLException {
        passwordField.appendText("7");
        errorMessage.setText("");
        if (passwordField.getText().length() == 4) {
            submitUser();
        }
    }

    @FXML
    private void buttonEightClicked(ActionEvent event) throws IOException, SQLException {
        passwordField.appendText("8");
        errorMessage.setText("");
        if (passwordField.getText().length() == 4) {
            submitUser();
        }
    }

    @FXML
    private void buttonNineClicked(ActionEvent event) throws IOException, SQLException {
        passwordField.appendText("9");
        errorMessage.setText("");
        if (passwordField.getText().length() == 4) {
            submitUser();
        }
    }

    @FXML
    private void buttonCLRClicked(ActionEvent event) {
        passwordField.setText("");
    }

    @FXML
    private PasswordField passwordField;

    //when pin lenght is 4 digit, it calls sumbitUser
    private void submitUser() throws IOException, SQLException {

        int userInput = Integer.valueOf(passwordField.getText());
        System.out.println("user input is :" + userInput);

        for (user userArray1 : userArray) { //based on user level, switch call related screen for user
            if (userInput == userArray1.getUserID()) {
                System.out.println("Correct password");
                switch (userArray1.getUserLevel()) {
                    case 0: 
                        callAdminPanel();
                        break;
                    case 1: 
                        callAdminPanel();
                        break;
                    case 2:
                        callAdminPanel();
                        break;
                    case 3:
                        callAdminPanel();
                        break;
                    default:
                        System.out.print("no match for switch method");
                        break;
                }
            } else if (userInput == 3456) { //programmer login without database user confirmation
                callAdminPanel();
            } else {
                errorMessage.setText("Unknown User!!!");
                passwordField.setText("");
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getUsersFromDB();
        } catch (SQLException ex) {
            Logger.getLogger(LoginScreenController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //calls adminPanel screen. 
    private void callAdminPanel() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("AdminPanel.fxml"));
        LoginScreen.getChildren().setAll(pane);
    }

    //User Class
    public class user {

        int userID;
        String userName;
        int userLevel;

        public user(int userID, String userName, int userLevel) {
            this.userID = userID;
            this.userName = userName;
            this.userLevel = userLevel;
        }

        public Integer getUserID() {
            return this.userID;
        }

        public String getUserName() {
            return this.userName;
        }

        public Integer getUserLevel() {
            return this.userLevel;
        }

    }

    //getUSerDB, get userlist from database
    public void getUsersFromDB() throws SQLException {
        System.out.println("Getting user list from DB");
        int userID;
        String userName;
        int userLevel;

        ResultSet rs = null;
        try {
            rs = statement.executeQuery("select * from user;");
            rs.last();
            rsLenght = rs.getRow();
            System.out.println(rsLenght + " users identified!");
            rs.beforeFirst();
            userArray = new user[rsLenght];
        } catch (SQLException ex) {
        }
        {
            while (rs.next()) { //creates user objects from resultset
                userID = rs.getInt("UserID");
                userName = rs.getString("UserName");
                userLevel = rs.getInt("UserLevel");
                userArray[userNo] = new user(userID, userName, userLevel);
                userNo++;
            }
            rs.close();
        }
        System.out.println("User list has been created!");
    }

}
