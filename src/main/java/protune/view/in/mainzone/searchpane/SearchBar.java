package protune.view.in.mainzone.searchpane;

import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.FlowPane;
import protune.model.Constant;

import java.io.File;

public class SearchBar extends FlowPane {
    public SearchBar(SearchResult searchResultPane){
        this.setHeight(100);
        this.setHgap(10);

        ImageView searchIcon = new ImageView(new Image(new File(Constant.searchIconPath).toURI().toString()));

        TextField searchZone = new TextField();
        searchZone.setPrefHeight(50);
        searchZone.setPrefWidth(600);

        this.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                this.getParent().requestFocus();
                searchResultPane.displayResult(searchZone.getText());
            }
        });

        this.getStyleClass().add("search-bar");
        this.getChildren().addAll(searchIcon, searchZone);
    }
}
