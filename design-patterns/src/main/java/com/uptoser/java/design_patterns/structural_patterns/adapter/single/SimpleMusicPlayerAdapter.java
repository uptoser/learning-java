package com.uptoser.java.design_patterns.structural_patterns.adapter.single;

public class SimpleMusicPlayerAdapter implements SimpleMediaPlayer{
    private MediaPlayer mediaPlayer;

    public SimpleMusicPlayerAdapter(MediaPlayer mediaPlayer){
        this.mediaPlayer = mediaPlayer;
    }

    @Override
    public void play(){
        mediaPlayer.play();
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }

}
