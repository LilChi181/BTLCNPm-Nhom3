package protune.controller.auth;

import protune.model.UserData;

public class Authorization {
    private static Role currentRole;

    public static UserData getCurrentUser() {
        return currentUser;
    }

    private static UserData currentUser;

    public static void setCurrentRole(UserData user, Role role){
        currentUser = user;
        currentRole = role;
    }

    public static boolean isAccount(){ return currentRole == Role.ACCOUNT; }
}
