package com.uptoser.design_patterns.structural_patterns.adapter;


/**
 * @author Share 2017.9.11
 */
public class Application {
	/*
	 * 适配器模式：
	 */
	public static void main(String[] args) {
		AudioPlayer audioPlayer = new AudioPlayer();

		audioPlayer.play("mp3", "beyond the horizon.mp3");
		audioPlayer.play("mp4", "alone.mp4");
		audioPlayer.play("vlc", "far far away.vlc");
		audioPlayer.play("avi", "mind me.avi");
	}

}
