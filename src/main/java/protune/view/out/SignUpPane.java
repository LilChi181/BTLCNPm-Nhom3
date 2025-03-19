package protune.view.out;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import protune.controller.auth.SignUpCheck;
import protune.model.UserData;

public class SignUpPane extends AnchorPane {
    SignInPane paneSwitch;
    SignUpCheck checker = new SignUpCheck();
    public SignUpPane(){
        this.setPrefSize(400, 400);
        this.setLayoutX(80);
        this.setLayoutY(230);

        TextField firstNameLabel = new TextField();
        firstNameLabel.setPromptText("First name");
        firstNameLabel.setId("name-label");

        TextField lastNameLabel = new TextField();
        lastNameLabel.setPromptText("Last name");
        lastNameLabel.setId("name-label");
        lastNameLabel.setLayoutX(155);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setLayoutY(65);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setLayoutY(130);

        Button signupBtn = new Button("Sign up");
        signupBtn.setLayoutX(200);
        signupBtn.setLayoutY(200);

        Label noti = new Label();
        noti.setVisible(false);
        noti.setLayoutY(260);

        Label lb = new Label("Already had an account?");
        lb.setId("recom-label");
        Label lb2 = new Label("Sign in");
        lb2.setId("sign-label");
        lb2.setOnMouseClicked(e -> {
            noti.setVisible(false);
            this.setVisible(false);
            paneSwitch.setVisible(true);
        });

        lb.setLayoutY(210);
        lb2.setLayoutY(230);

        signupBtn.setOnAction(e -> {
            UserData newUser = new UserData(firstNameLabel.getText(), lastNameLabel.getText(), usernameField.getText(), passwordField.getText());
            String message = checker.isValid(newUser);

            if(!message.equals("accept")){
                noti.setId("noti-label");
                noti.setText(message);
            }
            else{
                firstNameLabel.clear();
                lastNameLabel.clear();
                usernameField.clear();
                passwordField.clear();
                noti.setId("accept-noti-label");
                noti.setText("Registration completed!");
            }
            noti.setVisible(true);
        });

        this.getChildren().addAll(firstNameLabel, lastNameLabel, usernameField, passwordField, noti, lb, lb2, signupBtn);
        this.setVisible(false);
    }

    public void setPaneSwitch(SignInPane signInPane){
        this.paneSwitch = signInPane;
    }
}
