package com.example.odevfilmlerretrofit;

import java.io.Serializable;

public class Yorumlar implements Serializable {
    private String yorumId;
    private String filmAd;
    private String yorum;

    public Yorumlar() {
    }

    public Yorumlar(String yorumId, String filmAd, String yorum) {
        this.yorumId = yorumId;
        this.filmAd = filmAd;
        this.yorum = yorum;
    }

    public String getYorumId() {
        return yorumId;
    }

    public void setYorumId(String yorumId) {
        this.yorumId = yorumId;
    }

    public String getFilmAd() {
        return filmAd;
    }

    public void setFilmAd(String filmAd) {
        this.filmAd = filmAd;
    }

    public String getYorum() {
        return yorum;
    }

    public void setYorum(String yorum) {
        this.yorum = yorum;
    }
}
