package com.example.odevfilmlerretrofit;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class DetayActivity extends AppCompatActivity {
    private ImageView imageViewFilmResim;
    private TextView textViewFilmAd,textViewYil,textViewYonetmen;
    private Filmler film;
    private Button button, buttonYorum;
    private NotificationCompat.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detay);

        imageViewFilmResim = findViewById(R.id.imageViewResim);
        textViewFilmAd = findViewById(R.id.textViewFilmAd);
        textViewYil = findViewById(R.id.textViewYil);
        textViewYonetmen = findViewById(R.id.textViewYonetmen);
        button = findViewById(R.id.buttonFragman);
        buttonYorum = findViewById(R.id.buttonYorum);

        film = (Filmler) getIntent().getSerializableExtra("nesne");

        textViewFilmAd.setText(film.getFilm_ad());
        textViewYil.setText(String.valueOf(film.getFilm_yil()));
        textViewYonetmen.setText(film.getYonetmen_ad());

        imageViewFilmResim.setImageResource(getResources().getIdentifier(film.getFilm_resim(),"drawable",getPackageName()));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fragman=new Intent(DetayActivity.this,FragmanActivity.class);
                fragman.putExtra("fragman",film);
                startActivity(fragman);
                durumaBagli(film.getFilm_ad(),film.getKategori_ad());
            }
        });

        buttonYorum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yorum = new Intent(DetayActivity.this, YorumActivity.class);
                yorum.putExtra("yorumNesne", film);
                startActivity(yorum);
            }
        });
    }
    public void durumaBagli(String filmIsim,String filmTur){
        NotificationManager bildirimYoneticisi = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent =new Intent(DetayActivity.this, DetayActivity.class);

        PendingIntent gidilecekIntent = PendingIntent.getActivity(this, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String kanalId="kanalID";
            String kanalAd="kanalAd";
            String kanalTanım="kanalTanım";
            int kanalOnceligi = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel kanal = bildirimYoneticisi.getNotificationChannel(kanalId);

            if (kanal == null){
                kanal = new NotificationChannel(kanalId,kanalAd,kanalOnceligi);
                kanal.setDescription(kanalTanım);
                bildirimYoneticisi.createNotificationChannel(kanal);
            }

            builder = new NotificationCompat.Builder(this, kanalId);
            builder.setContentTitle(filmIsim);
            builder.setContentText(filmTur+" filmi izliyorsunuz:)");
            builder.setSmallIcon(R.drawable.resim);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekIntent);
        }else{
            builder = new NotificationCompat.Builder(this);
            builder.setContentTitle(filmIsim);
            builder.setContentText(filmTur+" filmi izliyorsunuz:)");
            builder.setSmallIcon(R.drawable.resim);
            builder.setAutoCancel(true);
            builder.setContentIntent(gidilecekIntent);
            builder.setPriority(Notification.PRIORITY_HIGH);
        }
        bildirimYoneticisi.notify(1, builder.build());
    }
}
