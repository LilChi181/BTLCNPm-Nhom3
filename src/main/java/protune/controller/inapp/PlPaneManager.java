package protune.controller.inapp;

import protune.Init;
import protune.model.AudioData;
import protune.model.Playlist;
import protune.view.in.mainzone.homepane.audiocard.PLCard;
import protune.view.in.mainzone.playlistpane.PlaylistPane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlPaneManager {
    static List<PlaylistPane> paneList = new ArrayList<>();

    static Map<String, PlaylistPane> panes = new HashMap<>();

    public static void add(Playlist playlist){
        PlaylistPane newPane = new PlaylistPane(playlist.getName());
        paneList.add(newPane);
        panes.put(playlist.getName(), newPane);

        for(String Id : playlist.getAudioIDList()){
            AudioData audioData = SongListManager.getAudiobyID(Id);
            if(audioData != null){
                audioData.setPlaylist(playlist.getName());
                newPane.addSong(new PLCard(audioData, playlist.getName()));
            }
        }

        Init.mainZone.getChildren().add(newPane);
        newPane.toBack();
    }

    public static void showPane(String plName){
        panes.get(plName).toFront();
    }

    public static void changeName(String oldName, String newName){
        panes.put(newName, panes.get(oldName));
        panes.remove(oldName);
    }

    public static void delPane(Playlist playlist){
        Init.mainZone.getChildren().remove(panes.get(playlist.getName()));
        panes.remove(playlist.getName());
    }

    public static void delAllPane(){
        panes.clear();
        Init.mainZone.getChildren().remove(paneList);
        paneList.clear();
    }

    public static void addTrack(AudioData audioData, String plName){
        audioData.setPlaylist(plName);
        panes.get(plName).addSong(new PLCard(audioData, plName));
    }

    public static void delTrack(PLCard plCard, String plName){
        panes.get(plName).del(plCard);
        if(Init.playBar.isHaveSong() && Init.playBar.getPlayingSong().equals(plCard.getdata())) Init.playBar.reset();
    }
}
