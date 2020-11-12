import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ShowMessageTotalSysEntryVisitor implements SysEntryVisitor {

    @Override
    public void visit(UserGroup userGroup){

    }
    @Override
    public void visit(User user) {
        Stage stage = new Stage();
        stage.setTitle("Message Total");
        stage.setScene(new Scene( new TextArea("Number of Messages: " + String.valueOf(user.getMsgTotal())), 450, 450));
        stage.show();
    }
}
