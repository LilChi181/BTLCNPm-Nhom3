package protune.model;

import javafx.scene.image.Image;
import javafx.scene.media.Media;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;
import protune.controller.inapp.RandomIDGenerator;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class AudioData implements Serializable {
    private transient Media media;

    private transient Image thumbnail;

    private File audioFile;

    private String title;

    private String artist = "null";

    private URL url;

    private String ID;

    private boolean local = false;

    public String playlist;

    public void setPlaylist(String playlist){ this.playlist = playlist; }

    public String getPlaylist(){ return playlist; }

    public String getID(){ return ID; }

    public boolean isLocal(){ return local; }

    public AudioData(File file){
        local = true;
        this.audioFile = file;
        try {
            this.extractMetadata();
        } catch (InvalidAudioFrameException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AudioData(String url){
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            System.out.println("URL not found");
        }
    }

    public Image getThumbnail(){ return thumbnail; }

    public String getTitle(){ return title; }

    public String getTitleWithoutVietAccent(){
        String nfdNormalizedString = Normalizer.normalize(title, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(nfdNormalizedString).replaceAll("");
    }

    public void setTitle(String title){
        int i = title.length() - 1;
        for(; i >= 0; --i){
            if(title.charAt(i) == '.') break;
        }
        this.title = title.substring(0, i);
    }

    public void prepareToPlay(){
        if(this.audioFile.exists()){
            System.out.println(this.audioFile.getPath());
            this.media = new Media(this.audioFile.toURI().toString());
        }
        else{
            System.out.println("url");
            this.media = new Media(url.toString());
        }
    }

    public void init() throws InvalidAudioFrameException, IOException{
        extractMetadata();
    }

    public void extractMetadata() throws InvalidAudioFrameException, IOException{
        AudioFile f;
        try{
            if(this.audioFile == null){
                InputStream inputStream = url.openStream();
                this.audioFile = File.createTempFile("temp", ".mp3");
                FileOutputStream outputStream = new FileOutputStream(this.audioFile);
                byte[] buffer = new byte[4096];
                int bytesRead;
                for(int i = 0; i < 80; ++i){
                    bytesRead = inputStream.read(buffer);
                    outputStream.write(buffer, 0, bytesRead);
                }
                f = AudioFileIO.read(this.audioFile);
                outputStream.close();
                inputStream.close();
                while(!this.audioFile.delete());
            }
            else f = AudioFileIO.read(this.audioFile);
            Tag tag = f.getTag();
            this.title = tag.getFirst(FieldKey.TITLE);
            if(this.title.isEmpty()) setTitle(audioFile.getName());
            this.artist = tag.getFirst(FieldKey.ARTIST);

            /*
                I used Comment tag of file as ID for the file :>
                That was a little tricky, but I hadn't found any better solution
             */
            if(this.url == null && tag.getFirst(FieldKey.COMMENT).length() != Constant.AudioIDLength){
                this.ID = RandomIDGenerator.gen();
                tag.deleteField(FieldKey.COMMENT);
                tag.setField(FieldKey.COMMENT, this.ID);
                f.commit();
            }
            else this.ID = tag.getFirst(FieldKey.COMMENT);
            Artwork artwork = tag.getFirstArtwork();
            if (artwork != null) {
                byte[] imageData = artwork.getBinaryData();
                ByteArrayInputStream bai = new ByteArrayInputStream(imageData);
                this.thumbnail = new Image(bai);
            } else {
                this.thumbnail = new Image(new File(Constant.defaultSongThumbnailPath).toURI().toString());
            }
        }
        catch (TagException | CannotReadException | ReadOnlyFileException e) {
            System.out.println("loi");
            throw new RuntimeException(e);
        } catch (CannotWriteException e) {
            throw new RuntimeException(e);
        }
    }

    public String getArtist(){ return artist; }

    public Media getMedia(){ return media; }

    public File getAudioFile(){ return audioFile; }

    public boolean equal(AudioData audioData){
        return this.getID().equals(audioData.getID());
    }
}
