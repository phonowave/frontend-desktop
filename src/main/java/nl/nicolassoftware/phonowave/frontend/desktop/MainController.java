package nl.nicolassoftware.phonowave.frontend.desktop;

import java.io.File;
import java.util.Formatter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;

public class MainController
{
    @FXML
    private MediaView mediaView;
    @FXML
    private Button    selectFileButton;
    @FXML
    private Button    playButton;

    private FileChooser fileChooser;

    @FXML
    private void initialize()
    {
        playButton.setDisable(true);
        fileChooser = new FileChooser();
    }

    /**
     * Handles a select file request
     */
    @FXML
    private void selectFile()
    {
        File file = fileChooser.showOpenDialog(
                        selectFileButton.getScene().getWindow());
        if(file != null)
        {
            String location = toFileUri(file);
            mediaView.setMediaPlayer(new MediaPlayer(new Media(location)));
            playButton.setDisable(false);
        }
    }

    /**
     * Handles a play request
     */
    @FXML
    private void play()
    {
        MediaPlayer player = mediaView.getMediaPlayer();
        if(player != null)
        {
            Status status = player.getStatus();
            switch(status)
            {
                case READY:
                case PAUSED:
                case STOPPED:
                    player.play();
                    break;
                case PLAYING:
                    player.pause();
                    break;
                case DISPOSED:
                case HALTED:
                case STALLED:
                case UNKNOWN:
                default:
                    break;
            }
        }
    }

    /**
     * Creates a file URI string for the given file. Note: spaces will be
     * replaced by %20.
     * 
     * @param file
     *            to retrieve the URI string from.
     * @return the path of the given file in file:// format.
     */
    private static String toFileUri(File file)
    {
        StringBuilder builder = new StringBuilder();
        try(Formatter formatter = new Formatter(builder))
        {
            formatter.format("file://%s", file.getAbsolutePath());
        }

        // Replace spaces with %20. (Using String builder instead of
        // String::replace)
        int spaceIndex = builder.indexOf(" ");
        while(spaceIndex > -1)
        {
            builder.replace(spaceIndex, spaceIndex + 1, "%20");
            spaceIndex = builder.indexOf(" ");
        }

        return builder.toString();
    }
}
