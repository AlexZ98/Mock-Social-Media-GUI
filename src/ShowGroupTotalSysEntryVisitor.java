import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ShowGroupTotalSysEntryVisitor implements SysEntryVisitor {
    @Override
    public void visitUserTotal(User user) {

    }

    @Override
    public void visitGroupTotal(UserGroup userGroup) {
        Stage stage = new Stage();
        stage.setTitle("Group Total");
        stage.setScene(new Scene( new TextArea("Number of Groups: " + String.valueOf(userGroup.getNumberGroups())), 450, 450));
        stage.show();

    }

    @Override
    public void visitMessageTotal(User user) {

    }

    @Override
    public void visitPositivePercentage(User user) {

    }
}
