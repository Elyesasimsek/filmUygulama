package com.example.odevfilmlerretrofit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class YorumActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView yorumRv;
    private ArrayList<Yorumlar> yorumlarArrayListe;
    private YorumlarAdapter adapter;
    private Filmler filmler;

    private FirebaseDatabase database;
    private DatabaseReference myRefYorumlar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yorum);

        toolbar = findViewById(R.id.toolbar);
        yorumRv = findViewById(R.id.yorumRv);

        database = FirebaseDatabase.getInstance();
        myRefYorumlar = database.getReference("yorumlar");

        filmler = (Filmler) getIntent().getSerializableExtra("yorumNesne");


        toolbar.setTitle(filmler.getFilm_ad());
        setSupportActionBar(toolbar);

        yorumRv.setHasFixedSize(true);
        yorumRv.setLayoutManager(new LinearLayoutManager(this));

        yorumlarArrayListe = new ArrayList<>();

        adapter = new YorumlarAdapter(this, yorumlarArrayListe);

        yorumRv.setAdapter(adapter);

        yorumGetirByFilmAd();
    }

    public void yorumGetirByFilmAd(){
        Query sorgu = myRefYorumlar.orderByChild("filmAd").equalTo(filmler.getFilm_ad());

        sorgu.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yorumlarArrayListe.clear();

                for (DataSnapshot d:dataSnapshot.getChildren()){
                    Yorumlar yorumlar = d.getValue(Yorumlar.class);
                    yorumlar.setYorumId(d.getKey());

                    yorumlarArrayListe.add(yorumlar);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}