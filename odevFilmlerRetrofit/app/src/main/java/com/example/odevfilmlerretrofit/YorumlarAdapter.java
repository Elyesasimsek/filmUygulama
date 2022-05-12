package com.example.odevfilmlerretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class YorumlarAdapter extends RecyclerView.Adapter<YorumlarAdapter.CardTasarimTutucu>{
    private Context mContext;
    private List<Yorumlar> yorumlarListe;

    public YorumlarAdapter(Context mContext, List<Yorumlar> yorumlarListe) {
        this.mContext = mContext;
        this.yorumlarListe = yorumlarListe;
    }

    @NonNull
    @Override
    public CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yorum_card_tasarim, parent, false);
        return new CardTasarimTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardTasarimTutucu holder, int position) {
        Yorumlar yorumlar = yorumlarListe.get(position);

        holder.textViewYorum.setText("Yorum :\n"+yorumlar.getYorum());

    }

    @Override
    public int getItemCount() {
        return yorumlarListe.size();
    }

    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardView yorumCard;
        private TextView textViewYorum;

        public CardTasarimTutucu(@NonNull View itemView) {
            super(itemView);
            yorumCard = itemView.findViewById(R.id.yorumCard);
            textViewYorum = itemView.findViewById(R.id.textViewYorum);
        }
    }
}
