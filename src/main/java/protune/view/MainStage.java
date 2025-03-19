package protune.view;

import javafx.scene.image.Image;
import javafx.stage.Stage;
import protune.Init;
import protune.controller.auth.Authorization;
import protune.controller.auth.UserManager;
import protune.controller.inapp.PlaylistTrackManager;
import protune.controller.inapp.SongListManager;

import java.io.File;

public class MainStage extends Stage {

    public MainStage(){
        this.setTitle("ProTune");

        this.setScene(Init.logScene);

        this.setResizable(false);

        Image icon = new Image(new File("src/main/resources/img/appicon.png").toURI().toString());

        this.getIcons().add(icon);

        this.setOnHidden(e -> {
            SongListManager.exportLocalList();
            UserManager.exportList();
            if(Authorization.isAccount()) PlaylistTrackManager.exportPlaylists();
            System.exit(0);
        });
    }

    public void comeIn(){
        Init.inAppScene.modify();
        this.setScene(Init.inAppScene);
    }

    public void comeOut(){
        this.setScene(Init.logScene);
    }
}
