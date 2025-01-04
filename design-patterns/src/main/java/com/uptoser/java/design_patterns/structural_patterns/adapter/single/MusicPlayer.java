package com.uptoser.java.design_patterns.structural_patterns.adapter.single;

public class MusicPlayer implements MediaPlayer{
    @Override
    public void play() {
        System.out.println("播放");
    }

    @Override
    public void stop() {
        System.out.println("停止");
    }

    @Override
    public void previous() {
        System.out.println("不想实现的上一曲");
    }

    @Override
    public void next() {
        System.out.println("不想实现的下一曲");
    }
}
