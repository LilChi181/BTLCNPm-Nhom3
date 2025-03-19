package protune.view.in.navbar.item;

import protune.Init;
import protune.controller.inapp.PlaylistTrackManager;
import protune.controller.auth.Authorization;
import protune.controller.inapp.SongListManager;
import protune.controller.inapp.PlPaneManager;

import java.io.FileNotFoundException;

public class ExitItem extends NavItem{
    public ExitItem(String iconPath, String content) throws FileNotFoundException {
        super(iconPath, content);
        this.setOnMouseClicked(e -> {
            Init.appStage.comeOut();
            Init.inAppScene.reset();
            if(Authorization.isAccount()) PlaylistTrackManager.exportPlaylists();
            SongListManager.exportLocalList();
            PlPaneManager.delAllPane();
        });
    }
}
