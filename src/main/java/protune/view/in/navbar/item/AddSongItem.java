package protune.view.in.navbar.item;

import protune.Init;

import java.io.FileNotFoundException;

public class AddSongItem extends NavItem{
    public AddSongItem(String iconPath, String content) throws FileNotFoundException {
        super(iconPath, content);
        this.setOnMouseClicked(e -> Init.localFileAdd.addAudio());
    }
}
