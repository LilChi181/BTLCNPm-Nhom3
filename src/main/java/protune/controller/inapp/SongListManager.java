package protune.controller.inapp;

import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import protune.Init;
import protune.controller.auth.Authorization;
import protune.controller.io.FileIOSystem;
import protune.model.AudioData;
import protune.view.in.mainzone.homepane.audiocard.AudioCard;
import protune.view.in.mainzone.homepane.audiocard.LocalAudioCard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SongListManager {
    private static List<AudioData> localSDList = new ArrayList<>();
    private static List<String> urlList;
    private static final List<AudioData> homeSDList = new ArrayList<>();
    public static List<AudioData> getLocalList(){
        return localSDList;
    }
    public static int onlineAudioCount;

    public static void addSong(AudioData audioData){
        homeSDList.add(audioData);
        localSDList.add(audioData);
    }

    public static AudioData getAudiobyID(String id){
        for(AudioData audioData : homeSDList){
            if(audioData.getID().equals(id)) return audioData;
        }
        return null;
    }

    public static void getOnlineAudioList(){
        urlList = AWSS3Handle.getAudioUrlList();
        onlineAudioCount = urlList.size();

        for(int i = 0; i < urlList.size(); i += 5){
            int finalI = i;
            new Thread(() -> {
                for(int j = finalI; j < Math.min(finalI + 5, urlList.size()); ++j){
                    AudioData audioData = new AudioData(urlList.get(j));

                    try {
                        audioData.init();
                        homeSDList.add(audioData);
                    } catch (InvalidAudioFrameException | IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }

    }

    public static void importLocalAudio() throws ClassNotFoundException, InvalidAudioFrameException, IOException {
        for(int i = onlineAudioCount; i < homeSDList.size(); ++i) homeSDList.remove(i);

        localSDList.clear();

        if(Authorization.isAccount()){
            localSDList = FileIOSystem.read("src/main/data/useraudio/" + Authorization.getCurrentUser().getUsername() + "/local.bin");
        }
        else{
            localSDList = FileIOSystem.read("src/main/data/guestlocal.bin");
        }
        for(int i = 0; i < localSDList.size(); ++i){
            try {
                localSDList.get(i).init();
                Init.homePane.addSong(new LocalAudioCard(localSDList.get(i)));
                Init.localPane.addSong(new LocalAudioCard(localSDList.get(i)));
            } catch (IOException e) {
                localSDList.remove(i);
            }
        }
        homeSDList.addAll(localSDList);
    }

    public static void addOnlineAudio(){
        while(homeSDList.size() < onlineAudioCount) System.out.print("");
        for(AudioData audioData : homeSDList){
            Init.homePane.addSong(new AudioCard(audioData));
        }
    }

    public static void exportLocalList(){
         if(Authorization.isAccount()) {
             FileIOSystem.write(getLocalList(), "src/main/data/useraudio/" + Authorization.getCurrentUser().getUsername() + "/local.bin");
         }
         else FileIOSystem.write(getLocalList(), "src/main/data/guestlocal.bin");
    }

    public static AudioData getRandomAudio(AudioData currentAudio){
        Random rnd = new Random();
        int curr = find(currentAudio);
        int rand = rnd.nextInt(homeSDList.size());
        while(rand == curr) rand = rnd.nextInt(homeSDList.size());
        return homeSDList.get(rand);
    }

    public static AudioData getNextSong(AudioData currentAudio){
        int idx = find(currentAudio);
        if(idx == homeSDList.size() - 1) return currentAudio;
        return homeSDList.get(idx + 1);
    }

    public static AudioData getPrevSong(AudioData currentAudio){
        int idx = find(currentAudio);
        if(idx == 0) return currentAudio;
        return homeSDList.get(idx-  1);
    }

    public static int find(AudioData audioData){
        for(int i = 0; i < homeSDList.size(); ++i){
            if(homeSDList.get(i).equal(audioData)){
                System.out.println("found" + homeSDList.get(i).getTitle());
                return i;
            }
        }
        return -1;
    }

    public static void del(AudioData audioData){
        for(int i = 0; i < localSDList.size(); ++i){
            if(localSDList.get(i).equal(audioData)){
                localSDList.remove(i);
                homeSDList.remove(onlineAudioCount + i);
            }
        }
    }

    public static List<AudioData> findByKey(String key){
        List<AudioData> result = new ArrayList<>();
        for(AudioData audioData : homeSDList){
            if(audioData.getTitleWithoutVietAccent().toLowerCase().contains(key.toLowerCase()) ||
               audioData.getTitle().toLowerCase().contains(key.toLowerCase())) result.add(audioData);
        }
        return result;
    }
}
