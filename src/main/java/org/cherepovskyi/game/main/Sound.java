package org.cherepovskyi.game.main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.net.URL;

public class Sound {
    Clip clip;
    URL[] soundURL = new URL[30];
    public Sound(){
        soundURL[0] = getClass().getResource("angelical-pad-143276.wav");
        soundURL[1] = getClass().getResource("key-get-39925.wav");
        soundURL[2] = getClass().getResource("mortice-door-lock-being-locked-and-unlocked-95884.wav");
        soundURL[3] = getClass().getResource("soft-piano-100-bpm-121529.wav");
        soundURL[4] = getClass().getResource("knocking-on-door-6022.wav");
    }
    public void setFile(int i){
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
    }
}
