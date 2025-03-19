package protune.view.in.navbar;

import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import protune.controller.auth.Authorization;
import protune.model.Constant;
import protune.view.in.navbar.item.*;
import protune.view.in.navbar.playlist.PlayListBar;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class NavBar extends ToolBar {

    PlayListBar playListBar = new PlayListBar();

    UserItem userItem;

    public NavBar() throws FileNotFoundException {
        this.setOrientation(Orientation.VERTICAL);
        this.setPrefSize(230, 480);
        this.setLayoutX(10);
        this.setLayoutY(10);
        this.getStyleClass().add("bg");

        List<NavItem> itemList = new ArrayList<>();
        itemList.add(new HomeItem(Constant.homeIconPath, "Home"));
        itemList.add(new SearchItem(Constant.searchIconPath, "Search"));
        itemList.add(new AddSongItem(Constant.uploadIconPath, "Add song"));
        itemList.add(new MyLocalItem(Constant.localIconPath, "My local"));
        itemList.add(new ExitItem(Constant.exitIconPath, "Log out"));

        this.getStylesheets().add(getClass().getResource("/stylesheet/inapp.css").toExternalForm());
        this.getItems().addAll(itemList);
    }

    public void modify(){
        if(Authorization.isAccount()){
            try {
                userItem = new UserItem(Constant.userIconPath, Authorization.getCurrentUser().getFullName());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            this.getItems().remove(playListBar);
            playListBar.importPlaylists();
            this.getItems().add(4, playListBar);
            this.getItems().add(0, userItem);
        }
        else this.getItems().remove(playListBar);
    }

    public void reset(){
        this.getItems().removeAll(userItem, playListBar);
    }
}
