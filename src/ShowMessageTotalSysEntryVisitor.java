import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ShowMessageTotalSysEntryVisitor implements SysEntryVisitor {

    @Override
    public void visitUserTotal(User user) {

    }

    @Override
    public void visitGroupTotal(UserGroup userGroup) {

    }

    @Override
    public void visitMessageTotal(User user) {
        Stage stage = new Stage();
        stage.setTitle("Message Total");
        stage.setScene(new Scene( new TextArea("Number of Messages: " + String.valueOf(user.getMsgTotal())), 450, 450));
        stage.show();
    }

    @Override
    public void visitPositivePercentage(User user) {

    }
}
