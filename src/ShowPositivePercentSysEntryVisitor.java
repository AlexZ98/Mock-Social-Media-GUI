import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ShowPositivePercentSysEntryVisitor implements SysEntryVisitor {

    @Override
    public void visitUserTotal(User user) {

    }

    @Override
    public void visitGroupTotal(UserGroup userGroup) {

    }

    @Override
    public void visitMessageTotal(User user) {

    }

    @Override
    public void visitPositivePercentage(User user) {
        Stage stage = new Stage();
        stage.setTitle("Positive Percent Total");
        stage.setScene(new Scene( new TextField("Percentage of Positive Messages: "+ String.valueOf(user.getPosPercent())), 450, 450));
        stage.show();
    }
}
