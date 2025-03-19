package protune.view.in.mainzone.searchpane;

import javafx.scene.layout.VBox;
import protune.model.AudioData;
import protune.view.in.mainzone.homepane.audiocard.AudioCard;

public class SearchPane extends VBox {
    SearchResult searchResultPane;
    public SearchPane(){
        this.getStyleClass().add("bg");

        searchResultPane = new SearchResult();
        SearchBar searchBar = new SearchBar(searchResultPane);

        this.getChildren().addAll(searchBar, searchResultPane);
        this.getStylesheets().add(getClass().getResource("/stylesheet/inapp.css").toExternalForm());
    }

    public void del(AudioCard audioCard){
        searchResultPane.del(audioCard);
    }

    public void editCard(AudioData audioData){
        searchResultPane.editCard(audioData);
    }
}
