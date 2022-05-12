package com.example.odevfilmlerretrofit;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class FragmanActivity extends AppCompatActivity {
    VideoView videoView;
    ProgressDialog progressDialog;
    MediaController mediaController;
    Filmler film;
    int position=0;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fragman);

        film = (Filmler) getIntent().getSerializableExtra("fragman");


        if(mediaController==null) {
            mediaController = new MediaController(this);
        }
        videoView=(VideoView) findViewById(R.id.videoViewFragman);
        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Android VideoView Örneği");
        progressDialog.setMessage("Yükleniyor.");
        progressDialog.setCancelable(false);
        progressDialog.show();


        videoView.setMediaController(mediaController);
        VideoUri(film.getFilm_resim());
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                progressDialog.dismiss();
                videoView.seekTo(position);
                if(position==0){
                    videoView.start();
                }else{
                    videoView.pause();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("positionnn",videoView.getCurrentPosition());
        videoView.pause();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        position=savedInstanceState.getInt("positionnn");
        videoView.seekTo(position);
    }
    private void VideoUri(String filmIsim){
        switch (filmIsim){
            case "interstellar":
                videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.interstellar));
                break;
            case "inception":
                videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.inception));
                break;
            case "thepianist":
                videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.thepianist));
                break;
            case "birzamanlaranadoluda":
                videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.birzamanlaranadoluda));
                break;
            case "thehatefuleight":
                videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.thehatefuleight));
                break;
            case "django":
                    videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.django));
                break;

        }
    }
}
