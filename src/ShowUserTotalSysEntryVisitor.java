import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShowUserTotalSysEntryVisitor implements SysEntryVisitor {
    @Override
    public void visit(User user) {
        Stage stage = new Stage();
        stage.setTitle("User Total");
        stage.setScene(new Scene( new TextField("Number of Users: "+ String.valueOf(user.getNumberOfUsers())), 450, 450));
        stage.show();
    }

    @Override
    public void visit(UserGroup userGroup) {
    }


}
