package org.michaelmathews.membershipfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Window extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Image icon = new Image("icon.png");

        primaryStage.setTitle("Membership Application");
        primaryStage.getIcons().add(icon);
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25, 25, 25, 25));

        BorderPane borderPane = new BorderPane();


        Text title = new Text("Membership Application");
        title.setFont(Font.font("Tohoma", FontWeight.NORMAL, 20));
        gridPane.add(title, 0, 0, 2, 1);

        Label userName = new Label("User Name: ");
        gridPane.add(userName, 0, 1);

        TextField userNameField = new TextField();
        gridPane.add(userNameField, 1, 1);
        Text invalidUserOrPassword = new Text();
        invalidUserOrPassword.setStyle("-fx-text-inner-color: rgba(196,0,0,0.51)");
        gridPane.add(invalidUserOrPassword, 1, 3,2,1);

        Label password = new Label("Password: ");
        gridPane.add(password, 0, 2);

        PasswordField passwordField = new PasswordField();
        gridPane.add(passwordField, 1, 2);
        //gridPane.setGridLinesVisible(true);

        Button signInButton = new Button("Sign in");
        HBox signInButtonBox = new HBox(10);
        signInButtonBox.setAlignment(Pos.BOTTOM_RIGHT);
        signInButtonBox.getChildren().add(signInButton);
        gridPane.add(signInButtonBox, 1, 4);



        Button backButton = new Button("Back");
        borderPane.setTop(backButton);

        Button newUserButton = new Button("New User");
        borderPane.setBottom(newUserButton);



        GridPane newUserGridPane = new GridPane();
        newUserGridPane.setAlignment(Pos.CENTER);
        borderPane.setCenter(newUserGridPane);

        TextField newUserTextField = new TextField();
        newUserTextField.setPromptText("New User Username...");
        newUserGridPane.add(newUserTextField, 0, 0);
        TextField newUserPasswordField = new TextField();
        newUserPasswordField.setPromptText("New User Password...");
        newUserGridPane.add(newUserPasswordField, 0, 1);
        Text createdUser = new Text();
        newUserGridPane.add(createdUser, 0, 2);

        newUserButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Auth.createUser(newUserTextField.getText(), newUserPasswordField.getText());
                createdUser.setText("Added User: " + newUserTextField.getText());
                newUserTextField.clear();
                newUserPasswordField.clear();

            }
        });

        Scene otherScreen = new Scene(borderPane, 300, 250);
        Scene welcomeScene = new Scene(gridPane, 300, 250);

        signInButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                if(Auth.authorize(userNameField.getText(), passwordField.getText())) {
                    userNameField.clear();
                    passwordField.clear();
                    invalidUserOrPassword.setText(null);
                    primaryStage.setScene(otherScreen);
                }
                else {
                    userNameField.clear();
                    passwordField.clear();
                    invalidUserOrPassword.setText("Invalid Username or Password..");

                }

            }

        });

        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                    primaryStage.setScene(welcomeScene);
                    createdUser.setText("");
            }
        });



        primaryStage.setScene(welcomeScene);

        primaryStage.show();
    }



}
