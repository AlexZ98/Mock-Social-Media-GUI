import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.*;

public class GUIDriver extends Application {
//Main driver class which supports a main method that runs the entire program and the GUI
    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        List<String> uniqueUsers = new ArrayList<>();
        List<User> users = new ArrayList<>();
        List<UserGroup> groups = new ArrayList<>();

        AdminPanel adminPanel = AdminPanel.getInstance();
        adminPanel.getRoot().setExpanded(true);
        TreeView<UserInterface> treeView = new TreeView<>(AdminPanel.getRoot());

        StackPane treeContainer = new StackPane();
        treeContainer.getChildren().add(treeView);

        HBox hb1 = new HBox(15,adminPanel.getUserIdLabel(), adminPanel.getUserId(),adminPanel.getUserGroupLabel(), adminPanel.getGroupId());
        HBox hb2 = new HBox(15, adminPanel.getAddUser(), adminPanel.getAddGroup());
        HBox hb3 = new HBox(15, adminPanel.getShowGroupTotal(), adminPanel.getShowPosPercent());
        HBox hb4 = new HBox(15,adminPanel.getShowUserTotal(), adminPanel.getShowMsgTotal());
        HBox hb5 = new HBox(15, adminPanel.getOpenUserView());
        VBox vbox = new VBox(20, hb1, hb2, hb3, hb4, hb5);
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.setPadding(new Insets(5));

        adminPanel.setVgap(5);
        adminPanel.setHgap(5);
        adminPanel.add(treeContainer,0 ,0);
        adminPanel.add(vbox, 1,0);

        //Lambda function that attaches an event listener to tree items so that when a user presses on one of them an event triggers and runs this code
        treeView.getSelectionModel().selectedItemProperty().addListener((ChangeListener) (observable, oldValue, newValue) -> {
        //In my implementation, I disable the ability to add users or groups until you navigate through a treeview item so that you can add one to the root you are at
            TreeItem<UserInterface> selectedItem = (TreeItem<UserInterface>) newValue;
            TreeItem<UserInterface> previousItem = (TreeItem<UserInterface>) oldValue;
            //differentiate between users and user groups to get correct info displayed in textfields, and opens up certain features to be accessible by the user
            if(selectedItem.getValue() instanceof User){
                adminPanel.getUserId().setText(String.valueOf(selectedItem.getValue()));
                adminPanel.getGroupId().setText(String.valueOf(selectedItem.getParent().getValue()));
                adminPanel.getOpenUserView().setDisable(false);
                adminPanel.getAddUser().setDisable(true);
                adminPanel.getAddGroup().setDisable(true);
            }
            else if(selectedItem.getValue() instanceof UserGroup){
                adminPanel.getUserId().setText("");
                adminPanel.getGroupId().setText(String.valueOf(selectedItem.getValue()));
                adminPanel.getOpenUserView().setDisable(true);
                adminPanel.getAddUser().setDisable(false);
                adminPanel.getAddGroup().setDisable(false);
            }

            adminPanel.getAddUser().setOnAction(event -> {
                TextInputDialog input = new TextInputDialog();
                input.setTitle("User addition");
                input.setHeaderText("Adding a new User");
                input.getDialogPane().setContentText("User Name:");
                Optional<String> result = input.showAndWait();
                TextField inputTF = input.getEditor();
                //Soft input validation, also does not allow adding anything under users only groups, if still attempted nothing will change and program will not produce an error/crash
                //Also can't add a new user if the given ID was already given to another created user at an earlier point
                if(!uniqueUsers.contains(inputTF.getText()) && inputTF.getText()!=null && inputTF.getText().length()!=0 && newValue!=null && ((TreeItem<UserInterface>) newValue).getValue() instanceof UserGroup){
                    User newUser = new User(inputTF.getText());
                    uniqueUsers.add(inputTF.getText());
                    users.add(newUser);
                    adminPanel.createBranch(newUser, (TreeItem<UserInterface>)newValue);
                }
            });
            adminPanel.getAddGroup().setOnAction(event -> {
                TextInputDialog input = new TextInputDialog();
                input.setTitle("Group Addition");
                input.setHeaderText("Adding a new Group");
                input.getDialogPane().setContentText("Group Name:");
                Optional<String> result = input.showAndWait();
                TextField inputTF = input.getEditor();
                //Soft input validation, also does not allow adding anything under users only groups, if still attempted nothing will change and program will not produce an error/crash
                if(inputTF.getText()!=null && inputTF.getText().length()!=0 && newValue!=null && ((TreeItem<UserInterface>) newValue).getValue() instanceof UserGroup){
                    UserGroup newUserGroup = new UserGroup(inputTF.getText());
                    adminPanel.createBranch(newUserGroup, (TreeItem<UserInterface>)newValue, new Circle(5, Color.BLACK));
                    groups.add(newUserGroup);

                }
            });
            adminPanel.getOpenUserView().setOnAction(event -> {
                Stage stage = new Stage();
                stage.setTitle("User View");
                GridPane gridPane = new GridPane();
                ListView<String> userListView = new ListView<>();
                userListView.setPrefSize(100,75);
                userListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
                userListView.getItems().addAll(((TreeItem<User>) newValue).getValue().getFollowerIds());
                Label followers = new Label("Followers");
                Button followUserButton = new Button("Follow " + selectedItem.getValue().toString());
                    followUserButton.setOnAction(event2 -> {
                        if(previousItem.getValue() instanceof User && selectedItem.getValue() instanceof User && previousItem!=selectedItem && selectedItem!=null && previousItem!=null){
                            ((User) previousItem.getValue()).followUser((User) selectedItem.getValue(),selectedItem.getValue().toString());
                            userListView.getItems().add(String.valueOf(((TreeItem<User>) oldValue).getValue()));
                        };
                    });
                ListView<String> newsFeed = new ListView<>();
                newsFeed.setPrefSize(100,75);
                ((User)selectedItem.getValue()).getTweetMsgs().forEach((list)->{
                    newsFeed.getItems().add(String.valueOf(list));
                });
                TextField tweet = new TextField();
                Label news = new Label("Tweets: ");
                Button postTweet = new Button("Post Tweet");
                    postTweet.setOnAction(event1 -> {
                        ((TreeItem<User>)newValue).getValue().Tweet(tweet.getText());
                        newsFeed.getItems().add(tweet.getText());
                        tweet.setText("");
                        ((User) selectedItem.getValue()).update((User)selectedItem.getValue());
                    });

                gridPane.addRow(0, adminPanel.getUserIdLabel(), adminPanel.getUserId(), followUserButton);
                gridPane.addRow(1, followers, userListView);
                gridPane.addRow(2, postTweet, tweet);
                gridPane.addRow(3, news, newsFeed);
                gridPane.setVgap(20);
                gridPane.setHgap(20);

                stage.setScene(new Scene( gridPane, 500, 500));
                stage.show();

            });

            });
        adminPanel.getShowGroupTotal().setOnAction(event ->{
            SysEntryVisitor visitor = new ShowGroupTotalSysEntryVisitor();
            visitor.visit(groups.get(0));
        });

        adminPanel.getShowUserTotal().setOnAction(event -> {
            SysEntryVisitor visitor = new ShowUserTotalSysEntryVisitor();
            visitor.visit(users.get(0));
        });
        adminPanel.getShowPosPercent().setOnAction(event -> {
            SysEntryVisitor visitor = new ShowPositivePercentSysEntryVisitor();
            visitor.visit(users.get(0));
        });
        adminPanel.getShowMsgTotal().setOnAction(event -> {
            SysEntryVisitor visitor  = new ShowMessageTotalSysEntryVisitor();
            visitor.visit(users.get(0));
        });


        Scene myScene = new Scene(adminPanel, 900, 600);

        primaryStage.setScene(myScene);
        primaryStage.setTitle("Twitter");
        primaryStage.show();
    }

}
