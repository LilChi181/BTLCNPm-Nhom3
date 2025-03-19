package protune.view.in.mainzone.searchpane;

import protune.controller.inapp.SongListManager;
import protune.model.AudioData;
import protune.view.in.mainzone.homepane.HomePane;
import protune.view.in.mainzone.homepane.audiocard.AudioCard;
import protune.view.in.mainzone.homepane.audiocard.LocalAudioCard;

import java.util.List;

public class SearchResult extends HomePane {
    public SearchResult(){
        this.setPrefSize(740, 410);

        inside.setPrefWidth(740);
        inside.setPrefHeight(410);
        inside.getStyleClass().addAll("bg","search-result");

        this.setContent(inside);
    }

    public void displayResult(String key){
        inside.getChildren().clear();
        List<AudioData> result = SongListManager.findByKey(key);
        for(AudioData audioData : result){
            if(audioData.isLocal()) inside.getChildren().add(new LocalAudioCard(audioData));
            else inside.getChildren().add(new AudioCard(audioData));
            audioCardList.add((AudioCard) inside.getChildren().getLast());
        }
    }
}
