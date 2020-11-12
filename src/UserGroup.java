public class UserGroup implements UserInterface, SysEntry {
    private String userGroupId;
    private static int numberGroups = 0;

    public UserGroup(String userGroupId){
        this.userGroupId = userGroupId;
        numberGroups++;
    }

    public String toString(){
        return userGroupId;
    }
    public int getNumberGroups(){
        return numberGroups;
    }

    @Override
    public void accept(SysEntryVisitor sysEntryVisitor) {
        sysEntryVisitor.visit(this);
    }
}
