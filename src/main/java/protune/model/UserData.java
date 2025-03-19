package protune.model;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class UserData implements Serializable {
    private String firstName, lastName, fullName;

    private final String username;

    private final String password;

    public String getFullName() {
        return fullName;
    }

    public UserData(String firstName, String lastName, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.fullName = firstName + " " + lastName;
        this.username = username;
        this.password = password;
    }

    public UserData(String username, String password){
        this.username = username;
        this.password = password;
    }

    public void createPath(){
        File userPath = new File("src/main/data/useraudio/" + username);
        userPath.mkdir();

        File playlistsPath = new File("src/main/data/useraudio/" + username + "/playlists.bin");
        File local = new File("src/main/data/useraudio/" + username + "/local.bin");

        try {
            local.createNewFile();
            playlistsPath.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername(){ return username; }

    public String getPassword(){ return password; }

    public boolean isValid(){
        return !username.isEmpty() && !username.contains(" ") && !firstName.isEmpty() && !lastName.isEmpty();
    }
}
