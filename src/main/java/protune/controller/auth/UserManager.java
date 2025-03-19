package protune.controller.auth;

import protune.controller.io.FileIOSystem;
import protune.model.UserData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserManager {
    private static List<UserData> userDataList = new ArrayList<>(); 

    private static final Map<String, UserData> dataMP = new HashMap<>();

    public static void importList() throws IOException, ClassNotFoundException {
        userDataList = FileIOSystem.read("src/main/data/userlist.bin");
        for(UserData user : userDataList){
            dataMP.put(user.getUsername(), user);
        }
    }

    public static void exportList(){
        FileIOSystem.write(userDataList, "src/main/data/userlist.bin");
    }

    public static void add(UserData userData){
        userData.createPath();
        userDataList.add(userData);
        dataMP.put(userData.getUsername(), userData);
    }

    public static UserData getUserbyUsername(String username){
        return dataMP.get(username);
    }

    public static boolean exist(String username){
        return dataMP.containsKey(username);
    }

    public static boolean valid(UserData userData){
        UserData tmp = dataMP.get(userData.getUsername());
        return tmp.getPassword().equals(userData.getPassword());
    }

}
