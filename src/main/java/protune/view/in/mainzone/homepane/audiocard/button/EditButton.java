package protune.view.in.mainzone.homepane.audiocard.button;

import protune.view.in.mainzone.editstage.AudioEditStage;
import protune.view.in.mainzone.homepane.audiocard.LocalAudioCard;

public class EditButton extends CardButton{
    public EditButton(String iconPath, LocalAudioCard audioCard) {
        super(iconPath);
        this.setLayoutX(125);
        this.setLayoutY(5);
        this.setOnMouseClicked(e -> {
            AudioEditStage audioEditStage = new AudioEditStage(audioCard);
        });
    }
}
