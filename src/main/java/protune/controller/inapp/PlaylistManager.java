package protune.controller.inapp;

import protune.Init;
import protune.model.Playlist;

public class PlaylistManager {

    public static void add(Playlist newPl){
        Init.playlistList.getItems().add(newPl.getName());
        PlaylistTrackManager.add(newPl);
        PlPaneManager.add(newPl);
    }

    public static void del(Playlist playlist){
        PlaylistTrackManager.delPl(playlist);
        PlPaneManager.delPane(playlist);
        Init.playlistList.removeItem(playlist.getName());
    }

    public static void changeName(String oldName, String newName){
        PlaylistTrackManager.changePlName(oldName, newName);
        PlPaneManager.changeName(oldName, newName);
        Init.playlistList.changeName(oldName, newName);
    }
}
