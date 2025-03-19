package protune.view.out;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import protune.Init;
import protune.controller.auth.Authorization;
import protune.controller.auth.Role;
import protune.controller.auth.SignInCheck;
import protune.model.UserData;

public class SignInPane extends AnchorPane {

    SignUpPane paneSwitch;
    SignInCheck checker = new SignInCheck();

    public SignInPane(){
        this.setPrefSize(400, 400);
        this.setLayoutX(80);
        this.setLayoutY(230);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setLayoutY(100);

        Button signinBtn = new Button("Sign in");
        signinBtn.setLayoutX(200);
        signinBtn.setLayoutY(200);

        Label noti = new Label();
        noti.setVisible(false);
        noti.setLayoutY(160);
        noti.setId("noti-label");


        Label lb = new Label("No account yet?");
        lb.setId("recom-label");
        Label lb2 = new Label("Sign up");
        lb2.setId("sign-label");
        lb2.setOnMouseClicked(e -> {
            noti.setVisible(false);
            this.setVisible(false);
            paneSwitch.setVisible(true);
        });

        Label guestLabel = new Label("Enter as guest.");
        guestLabel.setLayoutY(260);
        guestLabel.setId("sign-label");
        guestLabel.setOnMouseClicked(e ->{
            Authorization.setCurrentRole(null, Role.GUEST);
            Init.appStage.comeIn();
        });

        lb.setLayoutY(220);
        lb2.setLayoutY(217);
        lb2.setLayoutX(110);



        signinBtn.setOnAction(e -> {
            UserData newUser = new UserData(usernameField.getText(), passwordField.getText());
            String message = checker.isValid(newUser);

            if(!message.equals("accept")){
                noti.setText(message);
                noti.setVisible(true);
            }
            else{
                Init.appStage.comeIn();
                noti.setVisible(false);
            }
        });

        this.getChildren().addAll(usernameField, passwordField, noti, signinBtn, lb, lb2, guestLabel);
    }

    public void setPaneSwitch(SignUpPane signUpPane){
        this.paneSwitch = signUpPane;
    }
}
