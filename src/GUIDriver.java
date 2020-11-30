import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

//Admin control panel which extends GridPane to utilize features from the GUI class
public class AdminPanel extends GridPane {
    private static AdminPanel instance;
    private static TreeView<UserInterface> treeView;
    private static TreeItem<UserInterface> cs3560, root;
    private static TextField userId, groupId;
    private static Button addUser, addGroup, openUserView, showUserTotal, showGroupTotal, showMsgTotal, showPosPercent, idVerification, recentUpdate;
    private static UserInterface cpp, CS3560;
    private static Label userIdLabel, userGroupLabel;

    public static Label getUserIdLabel(){
        if(userIdLabel!=null){
            return userIdLabel;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static Label getUserGroupLabel(){
        if(userGroupLabel!=null){
            return userGroupLabel;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static Button getIdVerification() {
        if(idVerification!=null){
            return idVerification;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static Button getShowGroupTotal(){
        if(showGroupTotal!=null){
            return showGroupTotal;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static Button getRecentUpdate(){
        if(recentUpdate!=null){
            return recentUpdate;
        }
        throw new IllegalStateException("Does not exist");
    }


    public static Button getAddGroup(){
        if(addGroup!=null) {
            return addGroup;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static Button getAddUser(){
        if(addUser!=null) {
            return addUser;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static Button getOpenUserView(){
        if(openUserView!=null){
            return openUserView;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static Button getShowUserTotal(){
        if(showUserTotal!=null){
            return showUserTotal;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static Button getShowMsgTotal(){
        if(showMsgTotal!=null){
            return showMsgTotal;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static Button getShowPosPercent(){
        if(showPosPercent!=null){
            return showPosPercent;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static TextField getUserId(){
        if(userId!=null){
            return userId;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static TextField getGroupId(){
        if(groupId!=null){
            return groupId;
        }
        throw new IllegalStateException("Does not exist");
    }
    //Helper method to add an item to the TreeView
    public static TreeItem<UserInterface> createBranch(UserInterface userInterface, TreeItem<UserInterface> parent){
        TreeItem<UserInterface> treeItem = new TreeItem<>(userInterface);
        treeItem.setExpanded(true);
        parent.getChildren().add(treeItem);
        return treeItem;
    }
    //Helper method to add an item to the TreeView, this version allows for including a Shape object (graphic to distinguish between usergroup & users for this purpose)
    public static TreeItem<UserInterface> createBranch(UserInterface userInterface, TreeItem<UserInterface> parent, Shape shape){
        TreeItem<UserInterface> treeItem = new TreeItem<>(userInterface, shape);
        treeItem.setExpanded(true);
        parent.getChildren().add(treeItem);
        return treeItem;
    }
    private AdminPanel(){

    }
    public static TreeItem<UserInterface> getRoot(){
       return root;
    }
    public static AdminPanel getInstance(){
        if(instance==null){
            treeView = new TreeView<>();
            CS3560 = new UserGroup("CS3560");
            cpp = new UserGroup("CPP");
            userGroupLabel = new Label("User Group: ");
            userIdLabel = new Label("User Name:");
            root = new TreeItem<>(cpp, new Circle(5, Color.BLACK));
            cs3560 = createBranch(CS3560, root, new Circle(5, Color.BLACK));
            userId = new TextField();
            userId.setDisable(true);
            groupId = new TextField();
            groupId.setDisable(true);
            addUser = new Button("Add user");
            addUser.setDisable(true);
            addGroup = new Button("Add group");
            addGroup.setDisable(true);
            openUserView = new Button("Open User View");
            openUserView.setDisable(true);
            showUserTotal = new Button("Show User Total");
            showGroupTotal = new Button("Show Group Total");
            showMsgTotal = new Button("Show Message Total");
            showPosPercent = new Button("Show Positive Percentage");
            idVerification = new Button("Verify ID's");
            recentUpdate = new Button("Most Recently Updated User");

            instance = new AdminPanel();

        }
        return instance;
    }



}
