public class UserGroup implements UserInterface {
    private String userGroupId;

    public UserGroup(String userGroupId){
        this.userGroupId = userGroupId;
    }

    public String toString(){
        return userGroupId;
    }
}
