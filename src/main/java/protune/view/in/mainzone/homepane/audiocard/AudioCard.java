package protune.view.in.mainzone.homepane.audiocard;

import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import protune.model.AudioData;
import protune.model.Constant;
import protune.view.in.mainzone.homepane.audiocard.button.CardButtonManage;
import protune.view.in.mainzone.homepane.audiocard.button.PLAddButton;
import protune.view.in.mainzone.homepane.audiocard.button.PlayButton;

public class AudioCard extends FlowPane {

    protected String ID;
    private final AudioData audioData;
    protected AnchorPane imageZone = new AnchorPane();
    protected CardButtonManage btnManager = new CardButtonManage();
    protected PlayButton playButton;
    protected ImageView imageView;
    protected PLAddButton plAddButton;
    public AudioCard(AudioData audioData){
        plAddButton = new PLAddButton(Constant.addIconPath, this);
        playButton = new PlayButton(Constant.cardPlayIconPath, this);
        btnManager.add(playButton);
        btnManager.add(plAddButton);
        this.ID = audioData.getID();
        this.audioData = audioData;
        imageView = new ImageView(audioData.getThumbnail());
        this.getStyleClass().add("song-card");
        imageView.setFitHeight(180);
        imageView.setFitWidth(180);

        Rectangle rectangle = new Rectangle(180, 180);
        rectangle.setArcHeight(20);
        rectangle.setArcWidth(20);
        imageView.setClip(rectangle);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imageView.snapshot(parameters, null);

        imageView.setClip(null);
        imageView.setEffect(new DropShadow(20, Color.BLACK));
        imageView.setImage(image);

        imageZone.getChildren().add(imageView);
        imageZone.getChildren().addAll(btnManager.getBtnList());

        Label songName = new Label(audioData.getTitle());
        Label singer = new Label(audioData.getArtist());
        songName.setId("song-name");

        this.setOnMouseEntered(e -> {
            this.getStyleClass().add("card-mousein");
            btnManager.setVisible(true);
        });
        this.setOnMouseExited(e -> {
            this.getStyleClass().remove("card-mousein");
            btnManager.setVisible(false);
        });
        this.getStylesheets().add(getClass().getResource("/stylesheet/songcard.css").toExternalForm());
        this.getChildren().addAll(imageZone, songName, singer);

    }

    public AudioData getdata(){ return audioData; }

    public String getID(){ return ID; }

    public boolean equal(AudioCard other){
        return this.ID.equals(other.ID);
    }
}
