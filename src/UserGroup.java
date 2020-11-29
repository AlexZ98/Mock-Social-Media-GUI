import java.util.Date;

public class UserGroup implements UserInterface, SysEntry {
    private String userGroupId;
    private static int numberGroups = 0;
    private long groupCreated;

    public UserGroup(String userGroupId){
        this.userGroupId = userGroupId;
        numberGroups++;
        groupCreated = System.currentTimeMillis();
    }

    public String toString(){
        return userGroupId;
    }
    public Date getGroupCreated(){
        return new Date(groupCreated);
    }
    public int getNumberGroups(){
        return numberGroups;
    }

    @Override
    public void accept(SysEntryVisitor sysEntryVisitor) {
        sysEntryVisitor.visit(this);
    }
}
