package protune.view.in.playbar;

import javafx.scene.layout.FlowPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import protune.controller.inapp.PlaylistTrackManager;
import protune.controller.inapp.SongListManager;
import protune.model.AudioData;
import protune.view.in.playbar.nowplaying.NowPlayingSong;
import protune.view.in.playbar.player.PlayerZone;
import protune.view.in.playbar.volume.VolumeBar;

import java.io.FileNotFoundException;

public class PlayBar extends FlowPane {
    NowPlayingSong nowPlayingSong = new NowPlayingSong();
    PlayerZone playerZone = new PlayerZone();
    VolumeBar volumeBar = new VolumeBar();
    Media media;
    MediaPlayer mediaPlayer;
    AudioData playingSong;
    boolean shuffle = false;
    int repeat = 0;
    int repeatCount = 0;
    private boolean playing = false;
    private boolean haveSong = false;
    public PlayBar(){
        this.setPrefSize(980, 90);
        this.setLayoutX(10);
        this.setLayoutY(500);
        this.getStyleClass().add("bg");

        this.getStylesheets().add(getClass().getResource("/stylesheet/inapp.css").toExternalForm());
        this.getChildren().addAll(nowPlayingSong, playerZone, volumeBar);
    }

    public void setSongPlay(AudioData audioData) throws FileNotFoundException {
        System.out.println(repeatCount);
        if(repeatCount > 0 && !shuffle){
            --repeatCount;
            this.mediaPlayer.seek(Duration.ZERO);
            return;
        }
        if(this.playingSong != null && this.playingSong == audioData) return;
        if(this.mediaPlayer != null) this.mediaPlayer.dispose();

        audioData.prepareToPlay();
        this.playingSong = audioData;
        this.media = audioData.getMedia();
        this.playing = true;
        if(this.mediaPlayer != null) this.mediaPlayer.dispose();
        this.mediaPlayer = new MediaPlayer(this.media);
        nowPlayingSong.setSong(audioData);
        playerZone.setSongPlay(this.mediaPlayer);
        volumeBar.setMediaPlayer(this.mediaPlayer);
        this.haveSong = true;
    }

    public boolean isHaveSong(){ return haveSong; }

    public boolean isPlaying(){ return playing; }

    public void setPlaying(boolean state){ this.playing = state; }

    public void pause(){
        this.mediaPlayer.pause();
    }

    public void play(){
        this.mediaPlayer.play();
    }

    public AudioData getPlayingSong(){ return playingSong; }

    public void setShuffle(boolean shuffle){ this.shuffle = shuffle; }

    public void setRepeat(int repeat){
        this.repeat = repeat;
        repeatCount = repeat;
    }

    public void processWhenEndAudio() throws FileNotFoundException {
        if(shuffle){
            playRandom();
        }
        else{
            if(repeatCount > 0){
                 setSongPlay(this.playingSong);
            }
            else{
                playNext();
                repeatCount = repeat;
            }
        }
    }

    public void playRandom(){
        AudioData nextSong;

        if(playingSong.getPlaylist() == null) nextSong = SongListManager.getRandomAudio(this.playingSong);
        else nextSong = PlaylistTrackManager.getRandAudio(playingSong, playingSong.getPlaylist());

        try {
            setSongPlay(nextSong);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void playNext(){
        if(shuffle) playRandom();
        else{
            AudioData nextSong;

            if(playingSong.getPlaylist() == null) nextSong = SongListManager.getNextSong(this.playingSong);
            else nextSong = PlaylistTrackManager.getNextAudio(playingSong, playingSong.getPlaylist());
            try {
                setSongPlay(nextSong);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void playPrevious(){
        if(shuffle) playRandom();
        else{
            AudioData prevSong;

            if(playingSong.getPlaylist() == null) prevSong = SongListManager.getPrevSong(this.playingSong);
            else prevSong = PlaylistTrackManager.getPrevAudio(playingSong, playingSong.getPlaylist());
            try {
                setSongPlay(prevSong);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void reset(){
        if(this.haveSong){
            this.mediaPlayer.dispose();
            this.nowPlayingSong.reset();
            this.playerZone.reset();
        }
    }
}
