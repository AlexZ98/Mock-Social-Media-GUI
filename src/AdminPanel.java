import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


//Admin control panel which extends GridPane to utilize features from the GUI class
public class AdminPanel extends GridPane {
    private static AdminPanel instance;
    private static TreeView<UserInterface> treeView = new TreeView<>();
    private static TreeItem<UserInterface> cs3560, root;
    private static TextField userId, groupId, groupTotal, userTotal, posPercent, msgTotal;
    private static Button addUser, addGroup, openUserView, showUserTotal, showGroupTotal, showMsgTotal, showPosPercent;
    private static UserInterface cpp, CS3560;

    public static TextField getMsgTotal(){
        if(msgTotal!=null) {
            return msgTotal;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static Button getShowGroupTotal(){
        if(showGroupTotal!=null){
            return showGroupTotal;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static TextField getGroupTotal(){
        if(groupTotal!=null) {
            return groupTotal;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static TextField getUserTotal(){
        if(userTotal!=null) {
            return userTotal;
        }
        throw new IllegalStateException("Does not exist");
    }
    public static TextField getPosPercent(){
        if(posPercent!=null) {
            return posPercent;
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

    public static TreeItem<UserInterface> createBranch(UserInterface userInterface, TreeItem<UserInterface> parent){
        TreeItem<UserInterface> treeItem = new TreeItem<>(userInterface);
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
            CS3560 = new UserGroup("CS3560");
            cpp = new UserGroup("CPP");
            root = new TreeItem<>(cpp, new Circle(5, Color.BLACK));
            cs3560 = createBranch(CS3560, root);
            userId = new TextField();
            userId.setPromptText("User ID:");
            userId.setDisable(true);
            groupId = new TextField();
            groupId.setPromptText("Group ID");
            groupId.setDisable(true);
            groupTotal = new TextField();
            groupTotal.setPromptText("Group Total");
            groupTotal.setDisable(true);
            userTotal = new TextField();
            userTotal.setPromptText("User Total");
            userTotal.setDisable(true);
            posPercent = new TextField();
            posPercent.setPromptText("Percentage of Positive Messages");
            posPercent.setDisable(true);
            addUser = new Button("Add user");
            addUser.setDisable(true);
            addGroup = new Button("Add group");
            addGroup.setDisable(true);
            openUserView = new Button("Open User View");
            showUserTotal = new Button("Show User Total");
            showGroupTotal = new Button("Show Group Total");
            showMsgTotal = new Button("Show Message Total");
            showPosPercent = new Button("Show Positive Percentage");
            msgTotal= new TextField();
            msgTotal.setDisable(true);
            msgTotal.setPromptText("Total Number of Messages");

            instance = new AdminPanel();

        }
        return instance;
    }



}
