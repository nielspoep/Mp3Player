package mp3player;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.File;
import java.io.FileFilter;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;



public class MainClass {

    FileInputStream FIS;
    BufferedInputStream BIS;

    public Player player;
    
    public long pauseLocation;
    public long songTotalLength;
    public String fileLocation;
    
    public void Stop() 
    {
        if (player != null) 
        {
            player.close();
            
            pauseLocation = 0;
            songTotalLength = 0;
        }
    }
    
    public void Pause() 
    {
        if (player != null) 
        {
            try {
               pauseLocation = FIS.available();
               player.close();
            } catch (IOException ex) {
                
            }
        }
    }
    
    public void Play(String path)
    {
        try {
           
            FIS = new FileInputStream(path);
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
            songTotalLength = FIS.available();
            fileLocation = path + "";
            
        } catch (FileNotFoundException | JavaLayerException ex) {

        } catch (IOException ex) {

        }
        new Thread(){
            @Override
            public void run(){
                try {
                    player.play();
                } catch (JavaLayerException ex) {
                }
            }
        }.start();
    }
 public void Resume()
    {
        try {
            FIS = new FileInputStream(fileLocation);
            BIS = new BufferedInputStream(FIS);
            
            player = new Player(BIS);
            FIS.skip(songTotalLength - pauseLocation);
        } catch (FileNotFoundException | JavaLayerException ex) {

        } catch (IOException ex) {

        }
        new Thread(){
            @Override
            public void run(){
                try {
                    player.play();
                } catch (JavaLayerException ex) {
                }
            }
        }.start();
    }

    void Play(File myFile) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
}
