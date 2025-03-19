package protune.view.in.mainzone.editstage;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldDataInvalidException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;
import protune.Init;
import protune.model.AudioData;
import protune.view.in.mainzone.homepane.audiocard.LocalAudioCard;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class AudioEditStage extends Stage {
    File imgFile;
    ImageView coverImg;
    Label imageZone;
    public AudioEditStage(LocalAudioCard audioCard){
        AudioData audioData = audioCard.getdata();

        AudioFile f;
        try {
            f = AudioFileIO.read(audioData.getAudioFile());
        } catch (CannotReadException | IOException | ReadOnlyFileException | TagException | InvalidAudioFrameException e) {
            throw new RuntimeException(e);
        }
        Tag tag = f.getTag();



        FlowPane mainPain = new FlowPane();
        mainPain.setOrientation(Orientation.VERTICAL);

        Label l1 = new Label("Cover\nimage");
        Label l2 = new Label("Audio title");
        Label l3 = new Label("Artist");

        imageZone = new Label();

        if(audioData.getThumbnail().getUrl() == null){
            coverImg = new ImageView(audioData.getThumbnail());
            imageZone.setGraphic(coverImg);
            coverImg.setFitHeight(150);
            coverImg.setFitWidth(150);
        }
        else imageZone.setText("Null");

        Button chooseImgBtn = new Button("Choose\nimage");
        chooseImgBtn.setOnAction(e -> chooseImg());

        FlowPane imagePane = new FlowPane(l1, imageZone, chooseImgBtn);
        imagePane.setHgap(15);

        TextField title = new TextField(tag.getFirst(FieldKey.TITLE));
        TextField artist = new TextField(tag.getFirst(FieldKey.ARTIST));

        Button saveBtn = new Button("Save");
        saveBtn.setOnAction(e -> {
            if(imgFile != null){
                Artwork artwork;
                try {
                    artwork = Artwork.createArtworkFromFile(imgFile);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                tag.deleteArtworkField();

                try {
                    tag.setField(artwork);
                } catch (FieldDataInvalidException ex) {
                    throw new RuntimeException(ex);
                }
            }

            try {
                tag.setField(FieldKey.TITLE, title.getText());
            } catch (FieldDataInvalidException ex) {
                throw new RuntimeException(ex);
            }

            try {
                tag.setField(FieldKey.ARTIST, artist.getText());
            } catch (FieldDataInvalidException ex) {
                throw new RuntimeException(ex);
            }

            try {
                f.commit();
            } catch (CannotWriteException ex) {
                throw new RuntimeException(ex);
            }

            try {
                audioData.extractMetadata();
            } catch (InvalidAudioFrameException | IOException ex) {
                throw new RuntimeException(ex);
            }

            Init.homePane.editCard(audioData);
            Init.localPane.editCard(audioData);
            Init.searchPane.editCard(audioData);

            this.close();
        });
        saveBtn.setPrefSize(80, 40);

        FlowPane btnPane = new FlowPane(saveBtn);
        btnPane.setAlignment(Pos.BASELINE_RIGHT);

        mainPain.setVgap(15);
        mainPain.setPadding(new Insets(10));
        mainPain.getChildren().addAll(imagePane, l2, title, l3, artist, btnPane);

        Scene scene = new Scene(mainPain, 520, 435);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/stylesheet/editstage.css")).toExternalForm());
        this.setScene(scene);
        this.setResizable(false);
        this.show();
    }

    void chooseImg(){
        FileChooser imgChoose = new FileChooser();
        imgChoose.getExtensionFilters().add(new FileChooser.ExtensionFilter("Img file", "*.jpg", "*.png", "*jpeg"));
        imgFile = imgChoose.showOpenDialog(null);

        if(imgFile != null){
            coverImg = new ImageView(new Image(imgFile.toURI().toString()));
            coverImg.setFitHeight(150);
            coverImg.setFitWidth(150);
            imageZone.setGraphic(coverImg);
            imageZone.setText("");
        }

    }
}
