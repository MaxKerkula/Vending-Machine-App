package com.techelevator;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer {

    //Method to play the sound file
    public static void playSound(File soundFile) {
        try {
            //Get audio input stream from the sound file
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            //Get the format of the audio
            AudioFormat format = audioInputStream.getFormat();
            //Create a data line for the clip
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            //Create a clip from the audio input stream
            Clip clip = (Clip) AudioSystem.getLine(info);
            //Open the clip
            clip.open(audioInputStream);
            //Start playing the clip
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            //Print the stack trace if an exception occurs
            System.out.println(e.getMessage());
        }
    }

    public void playVendingMachine() {
        File soundFile = new File("sounds/vendingMachine.wav");
        //Call the playSound method with the sound file
        playSound(soundFile);
    }

    public void playBuzz() {
        File soundFile = new File("sounds/buzz.wav");
        //Call the playSound method with the sound file
        playSound(soundFile);
    }

    public void playKaching() {
        File soundFile = new File("sounds/kaching.wav");
        //Call the playSound method with the sound file
        playSound(soundFile);
    }

    public void playNoSound() {
        //Create a file object for the sound file
        File soundFile = new File("sounds/no.wav");
        //Call the playSound method with the sound file
        playSound(soundFile);
    }

    public void playWompWomp() {
        File soundFile = new File("sounds/wahwah.wav");
        //Call the playSound method with the sound file
        playSound(soundFile);
    }

    public void playMenuClick() {
        File soundFile = new File("sounds/menuclick.wav");
        //Call the playSound method with the sound file
        playSound(soundFile);
    }
    public void playProductSound(String path) {
        File soundFile = new File(path);
        //Call the playSound method with the sound file
        playSound(soundFile);
    }
    public void playDrink() {
        File soundFile = new File("sounds/drink.wav");
        //Call the playSound method with the sound file
        playSound(soundFile);
    }
    public void playGum() {
        File soundFile = new File("sounds/gum.wav");
        //Call the playSound method with the sound file
        playSound(soundFile);
    }
    public void playCandy() {
        File soundFile = new File("sounds/candy.wav");
        //Call the playSound method with the sound file
        playSound(soundFile);
    }
    public void playChip() {
        File soundFile = new File("sounds/chip.wav");
        //Call the playSound method with the sound file
        playSound(soundFile);
    }
}
