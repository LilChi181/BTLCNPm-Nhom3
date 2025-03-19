package protune.controller.auth;

import protune.model.UserData;

public class SignInCheck implements SignCheck{
    @Override
    public String isValid(UserData userData) {
        if(!UserManager.exist(userData.getUsername())) return "Username doesn't exist!";
        if(!UserManager.valid(userData)) return "Invalid password!";

        userData = UserManager.getUserbyUsername(userData.getUsername());
        Authorization.setCurrentRole(userData, Role.ACCOUNT);
        return "accept";
    }
}
