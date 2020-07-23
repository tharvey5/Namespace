package com.jtaodyssey.namespace.ui.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jtaodyssey.namespace.components.BasicRegistration;
import com.jtaodyssey.namespace.components.JTARegistration;
import com.jtaodyssey.namespace.components.LoggedInUser;
import com.jtaodyssey.namespace.notification.*;
import com.jtaodyssey.namespace.services.JTACachedUser;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileEditUsername implements Initializable, JTANotificationObserver
{
    // ************
    // * Label(s) *
    // ************

    @FXML
    private Label usernameLabel;

    // *****************
    // * Text Field(s) *
    // *****************

    @FXML
    private JFXPasswordField passwordField;
    @FXML
    private JFXTextField newUsernameField;
    @FXML
    private JFXTextField confirmUsernameField;

    // *************
    // * Button(s) *
    // *************

    @FXML
    private JFXButton saveUsernameButton;

    @FXML
    private JFXButton returnToProfileButton;

    // ***************
    // * Constructor *
    // ***************

    public ProfileEditUsername()
    {
        ToUINotifier.getInstance().addObserver(this);
    }


    // **************
    // * Initialize *
    // **************

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {

    }


    // ***********************
    // * Notification Update *
    // ***********************

    @Override
    public void update(JTANotification notification)
    {

    }


    // ***************
    // * Function(s) *
    // ***************

    @FXML
    public void onClickSaveUsername()
    {
        String password        = passwordField.getText();
        String newUsername     = newUsernameField.getText();
        String confirmUsername = confirmUsernameField.getText();


        if(!password.equals("") && !newUsername.equals("") && !confirmUsername.equals(""))
        {
            JTACachedUser cachedUser = LoggedInUser.getInstance().getUser();
            //FromUINotifier.getInstance().notify(new UpdateUserNotification(new BasicRegistration(cachedUser.getUser().getFirstName(), cachedUser.getUser().getLastName())));
            swapScene("Home", saveUsernameButton);
        }
    }

    @FXML
    public void onClickReturnToProfile()
    {
        swapScene("Home", returnToProfileButton);
    }

    // This function is used to swap scenes
    public void swapScene(String fileName, Button button)
    {
        Parent root = null;
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/jtaodyssey/namespace/ui/fxml/" + fileName + ".fxml"));
            root = loader.load();
            Scene scene  = new Scene(root);
            Stage window = (Stage)(button).getScene().getWindow();

            Home crtl = loader.getController();
            crtl.onClickShowProfile();

            Platform.runLater(()->
            {
                ToUINotifier.getInstance().removeObserver(this);
                window.setScene(scene);
                window.show();
            });
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
