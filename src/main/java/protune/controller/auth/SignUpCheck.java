package protune.controller.auth;

import protune.model.UserData;

public class SignUpCheck implements SignCheck{
    @Override
    public String isValid(UserData userData) {
        if(!userData.isValid()) return "Invalid information!";
        if(UserManager.exist(userData.getUsername())) return "Username's already existed";
        UserManager.add(userData);
        return "accept";
    }
}
