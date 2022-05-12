package com.example.odevfilmlerretrofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FilmlerAdapter extends RecyclerView.Adapter<FilmlerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Filmler> filmlerListe;

    public FilmlerAdapter(Context mContext, List<Filmler> filmlerListe) {
        this.mContext = mContext;
        this.filmlerListe = filmlerListe;
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardView film_card;
        private TextView textViewFilmAd;
        private ImageView ımageViewFilmResim;
        public CardTasarimTutucu(View itemView) {
            super(itemView);
            film_card = itemView.findViewById(R.id.film_card);
            textViewFilmAd = itemView.findViewById(R.id.textViewFilmAd);
            ımageViewFilmResim = itemView.findViewById(R.id.imageViewFilmResim);
        }
    }

    @Override
    public CardTasarimTutucu onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_card_tasarim,parent,false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(CardTasarimTutucu holder, int position) {
        final Filmler film = filmlerListe.get(position);

        holder.textViewFilmAd.setText(film.getFilm_ad());

        holder.ımageViewFilmResim.setImageResource(mContext.getResources().getIdentifier(film.getFilm_resim(),"drawable", mContext.getPackageName()));

        holder.film_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ıntent = new Intent(mContext,DetayActivity.class);

                ıntent.putExtra("nesne",film);

                mContext.startActivity(ıntent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return filmlerListe.size();
    }




}
