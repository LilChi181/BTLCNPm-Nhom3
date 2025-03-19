package protune.view.in.mainzone.homepane.audiocard;

import javafx.scene.Cursor;
import javafx.scene.control.ListView;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import protune.controller.inapp.PlaylistTrackManager;
import protune.model.AudioData;

import java.util.List;

public class PlaylistList extends ListView<String> {

    AudioData currentChoose;

    public PlaylistList(){
        this.setVisible(false);
        this.setCursor(Cursor.HAND);

        this.setPrefSize(100, 100);
        this.setBorder(Border.stroke(Color.RED));

        this.setOnMouseClicked(e -> {
            String plName = this.getSelectionModel().getSelectedItem();

            if(plName != null){
                PlaylistTrackManager.addTracktoPlaylist(plName, this.currentChoose);
            }

            this.setVisible(false);
        });
    }

    public void changeName(String oldName, String newName){
        int idx = this.getItems().indexOf(oldName);
        this.getItems().remove(oldName);
        this.getItems().add(idx, newName);
    }

    public void removeItem(String itemName){
        this.getItems().remove(itemName);
    }

    public void prepare(AudioData audioData){
        currentChoose = audioData;
    }

    public void importList(){
        this.setVisible(false);
        this.getItems().clear();
        List<String> plNames = PlaylistTrackManager.getPlNames();

        this.getItems().addAll(plNames);
    }
}
