package com.grouprx.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.nationaldrugcard.qlinkwireless.R;

public class PlayVideoActivity extends Activity implements OnCompletionListener {

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_video);
		VideoView v = (VideoView) findViewById(R.id.videoView);
		String url = null;
		if (getIntent().getExtras() != null) {
			url = getIntent().getExtras().getString("url");

			if (url != null) {
				v.setMediaController(new MediaController(this));
				v.setOnCompletionListener(this);
				v.setVideoURI(Uri.parse(url));
				v.start();
			}
		}
		if (url == null) {
			throw new IllegalArgumentException(
					"Must set url extra paremeter in intent.");
		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
	}

	@Override
	public void onCompletion(MediaPlayer arg0) {
		finish();
	}

	// Convenience method to show a video
	public static void showRemoteVideo(Context ctx, String url) {
		Intent i = new Intent(ctx, PlayVideoActivity.class);
		i.putExtra("url", url);
		ctx.startActivity(i);
	}

}
