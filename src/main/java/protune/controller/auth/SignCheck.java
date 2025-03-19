package protune.controller.auth;

import protune.model.UserData;

public interface SignCheck {
    String isValid(UserData userData);
}
