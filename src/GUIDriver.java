import javafx.application.Application;

import javafx.beans.value.ChangeListener;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GUIDriver extends Application {
//Main driver class which supports a main method that runs the entire program and the GUI
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        List<User> listOfUsers = new ArrayList<>();
        List<UserGroup> listOfGroups = new ArrayList<>();

        AdminPanel adminPanel = AdminPanel.getInstance();
        adminPanel.getRoot().setExpanded(true);
        TreeView<String> treeView = new TreeView<>(AdminPanel.getRoot());

        StackPane treeContainer = new StackPane();
        treeContainer.getChildren().add(treeView);


        HBox hb1 = new HBox(15, adminPanel.getUserId(), adminPanel.getGroupId(), adminPanel.getShowUserTotal(), adminPanel.getShowMsgTotal());
        HBox hb2 = new HBox(15, adminPanel.getAddUser(), adminPanel.getAddGroup(),adminPanel.getShowGroupTotal(), adminPanel.getShowPosPercent());
        HBox hb3 = new HBox(10, adminPanel.getUserTotal(), adminPanel.getGroupTotal(), adminPanel.getMsgTotal(), adminPanel.getPosPercent());
        VBox vbox = new VBox(20, hb1, adminPanel.getOpenUserView(), hb2, hb3);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(5));

        adminPanel.setVgap(5);
        adminPanel.setHgap(5);
        adminPanel.add(treeContainer,0 ,0);
        adminPanel.add(vbox, 1,0);


        //Lambda function that attaches an event listener to tree items so that when a user presses on one of them an event triggers and runs this code
        treeView.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
        //In my implementation, I disable the ability to add users or groups until you navigate through a treeview item so that you can add one to the root you are at
            adminPanel.getAddUser().setDisable(false);
            adminPanel.getAddGroup().setDisable(false);
            TreeItem<String> selectedItem = (TreeItem<String>) newValue;
            //Scenario in which selected tree item is a leaf AKA student
            if(selectedItem.getChildren().isEmpty()){
                if((selectedItem.getChildren().isEmpty() && selectedItem.getParent()==adminPanel.getRoot())){
                    adminPanel.getUserId().setText(selectedItem.getValue());
                    adminPanel.getGroupId().setText("CPP");
                }
                else{
                    adminPanel.getUserId().setText(selectedItem.getValue());
                    adminPanel.getGroupId().setText(selectedItem.getParent().getValue());
                }
            }
            //Else we're looking at a root or parent AKA a group
            else {
                adminPanel.getUserId().setText("");
                adminPanel.getGroupId().setText(selectedItem.getValue());
            }
            adminPanel.getAddUser().setOnAction(event -> {
                TextInputDialog input = new TextInputDialog();
                input.setTitle("User addition");
                input.getDialogPane().setContentText("User Name:");
                input.getDialogPane().setContentText("Group Name:");
                Optional<String> result = input.showAndWait();
                TextField inputTF = input.getEditor();
                if(inputTF.getText()!=null && inputTF.getText().length()!=0 && newValue!=null && oldValue!=null){
                    adminPanel.createBranch(inputTF.getText(), (TreeItem<String>)newValue);
                    User newUser = new User(inputTF.getText());
                    listOfUsers.add(newUser);
                }
            });
            adminPanel.getAddGroup().setOnAction(event -> {

            });

            });
        adminPanel.getShowGroupTotal().setOnAction(event ->{
            //Add one because of default group CS3560 that is statically initialized, I do not count the root as a group to be counted.
            adminPanel.getGroupTotal().setText(String.valueOf(listOfGroups.toArray().length+1));
        });



        adminPanel.getShowUserTotal().setOnAction(event -> {
            adminPanel.getUserTotal().setText(String.valueOf(listOfUsers.toArray().length));
        });



        Scene myScene = new Scene(adminPanel, 900, 600);

        primaryStage.setScene(myScene);
        primaryStage.setTitle("Twitter");
        primaryStage.show();
    }

}
