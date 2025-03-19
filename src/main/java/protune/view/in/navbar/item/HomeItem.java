package protune.view.in.navbar.item;

import protune.Init;

import java.io.FileNotFoundException;

public class HomeItem extends NavItem{
    public HomeItem(String iconPath, String content) throws FileNotFoundException {
        super(iconPath, content);
        this.setOnMouseClicked(e ->  Init.homePane.toFront() );
    }
}
