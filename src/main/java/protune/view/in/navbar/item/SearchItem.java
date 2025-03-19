package protune.view.in.navbar.item;

import protune.Init;

import java.io.FileNotFoundException;

public class SearchItem extends NavItem{
    public SearchItem(String iconPath, String content) throws FileNotFoundException {
        super(iconPath, content);
        this.setOnMouseClicked(e -> Init.searchPane.toFront());
    }
}
