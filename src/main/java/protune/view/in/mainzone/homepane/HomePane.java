package protune.view.in.mainzone.homepane;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import protune.model.AudioData;
import protune.view.in.mainzone.homepane.audiocard.AudioCard;
import protune.view.in.mainzone.homepane.audiocard.LocalAudioCard;

import java.util.ArrayList;
import java.util.List;

public class HomePane extends ScrollPane {
    protected List<AudioCard> audioCardList = new ArrayList<>();

    protected FlowPane inside;
    public HomePane(){
        this.setHbarPolicy(ScrollBarPolicy.NEVER);
        this.getStyleClass().add("bg");

        inside = new FlowPane();
        inside.setPadding(new Insets(15, 20, 15, 20));
        inside.setPrefWidth(740);
        inside.setPrefHeight(480);
        inside.getStyleClass().add("bg");
        inside.setVgap(15);
        inside.setHgap(40);

        this.setContent(inside);
        this.getStylesheets().add(getClass().getResource("/stylesheet/inapp.css").toExternalForm());
    }

    public void addSong(AudioCard audioCard){
        audioCardList.add(audioCard);
        inside.getChildren().add(audioCard);
    }

    public void del(AudioCard audioCard){
        for(Node child : inside.getChildren()){
            if(audioCard.equal((AudioCard) child)){
                audioCardList.remove((AudioCard) child);
                inside.getChildren().remove(child);
                break;
            }
        }
    }

    public void editCard(AudioData audioData){
        for(int i = 0; i < audioCardList.size(); ++i){
            System.out.println(audioCardList.get(i).getID() + " " + audioData.getID());
            if(audioCardList.get(i).getID().equals(audioData.getID())){
                System.out.println("found");
                inside.getChildren().remove(audioCardList.get(i));
                audioCardList.remove(i);
                LocalAudioCard newCard = new LocalAudioCard(audioData);
                audioCardList.add(i, newCard);
                inside.getChildren().add(i,newCard);
                break;
            }
        }
    }

    public void clearLocal(){
        for(int i = audioCardList.size() - 1; i >= 0; --i){
            if(audioCardList.get(i) instanceof LocalAudioCard){
                inside.getChildren().remove(i);
                audioCardList.remove(i);
                System.out.println("co");
            }
            else break;
        }
    }
}
