package protune.controller.inapp;

import protune.controller.auth.Authorization;
import protune.controller.io.FileIOSystem;
import protune.model.AudioData;
import protune.model.Playlist;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaylistTrackManager {
    private static final Map<String, Playlist> playlists = new HashMap<>();

    static int cnt;

    public static String getNewName(){ return "Playlist" + cnt++;}

    static File plFile;

    public static  void importPlaylists(){
        playlists.clear();
        System.out.println(Authorization.getCurrentUser().getUsername());
        plFile = new File("src/main/data/useraudio/" + Authorization.getCurrentUser().getUsername() + "/playlists.bin");
        List<Playlist> l;
        try {
            l = FileIOSystem.read(plFile.getPath());
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(Playlist pl : l){
            playlists.put(pl.getName(), pl);
            PlPaneManager.add(pl);
        }

        cnt = l.size();
    }

    public static void exportPlaylists(){

        List<Playlist> l = new ArrayList<>(playlists.values());

        FileIOSystem.write(l, plFile.getPath());
    }

    public static void delPl(Playlist playlist){
        playlists.remove(playlist.getName());
    }

    public static AudioData getNextAudio(AudioData current, String plName){

        AudioData next = SongListManager.getAudiobyID(playlists.get(plName).getNextId(current.getID()));
        next.setPlaylist(plName);

        return next;
    }

    public static void changePlName(String oldName, String newName){
        playlists.get(oldName).setName(newName);
        playlists.put(newName, playlists.get(oldName));
        playlists.remove(oldName);
    }

    public static AudioData getPrevAudio(AudioData current, String plName){
        playlists.get(plName).getPrevId(current.getID());

        AudioData prev = SongListManager.getAudiobyID(playlists.get(plName).getPrevId(current.getID()));
        prev.setPlaylist(plName);
        return prev;
    }

    public static boolean exist(String plName){
        return playlists.containsKey(plName);
    }

    public static AudioData getRandAudio(AudioData current, String plName){
        playlists.get(plName).getRandomId(current.getID());

        AudioData rand = SongListManager.getAudiobyID(playlists.get(plName).getRandomId(current.getID()));
        rand.setPlaylist(plName);
        return rand;
    }

    public static void add(Playlist playlist) {
            playlists.put(playlist.getName(), playlist);
    }

    public static void addTracktoPlaylist(String plName, AudioData audioData){
        playlists.get(plName).add(audioData.getID());
        PlPaneManager.addTrack(audioData, plName);
    }

    public static void delTrack(String plName, AudioData audioData){
        playlists.get(plName).del(audioData.getID());
    }

    public static List<String> getPlNames(){
        return new ArrayList<>(playlists.keySet());
    }

    public static List<Playlist> getList(){ return new ArrayList<>(playlists.values()); }
}
