package protune.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Playlist implements Serializable {

    String name;

    transient Random rnd = new Random();

    public Playlist(String name){
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<String> getAudioIDList() {
        return audioIDList;
    }

    List<String> audioIDList = new ArrayList<>();

    public String getNextId(String currID){

        System.out.println("ID list: " + currID);
        for(String id : audioIDList) System.out.println(id);


        int idx = audioIDList.indexOf(currID);

        System.out.println("Current: " + idx);

        if(idx == audioIDList.size() - 1) return audioIDList.get(idx);
        return audioIDList.get(idx + 1);
    }

    public String getPrevId(String currID){
        int idx = audioIDList.indexOf(currID);
        if(idx == 0) return audioIDList.getFirst();
        return audioIDList.get(idx - 1);
    }

    public String getRandomId(String currID){
        int idx = audioIDList.indexOf(currID);
        rnd = new Random();
        int res = rnd.nextInt(audioIDList.size());
        while(res == idx) res = rnd.nextInt(audioIDList.size());
        return audioIDList.get(res);
    }

    public void add(String audioID){
        audioIDList.add(audioID);
    }

    public void del(String audioID){
        audioIDList.remove(audioID);
    }
}
