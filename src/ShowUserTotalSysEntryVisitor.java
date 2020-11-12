import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowUserTotalSysEntryVisitor implements SysEntryVisitor {
    @Override
    public void visitUserTotal(User user) {
        Stage stage = new Stage();
        stage.setTitle("User Total");
        stage.setScene(new Scene( new TextField("Number of Users: "+ String.valueOf(user.getNumberOfUsers())), 450, 450));
        stage.show();
    }

    @Override
    public void visitGroupTotal(UserGroup userGroup) {
    }

    @Override
    public void visitMessageTotal(User user) {
    }

    @Override
    public void visitPositivePercentage(User user) {
    }
}
