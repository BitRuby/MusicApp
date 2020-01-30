package com.musicapp.serwer.services;

import com.musicapp.serwer.model.response.FavoriteRes;
import com.musicapp.serwer.repositories.FavoriteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepo favoriteRepo;

    public void addTrack(String track) {
        favoriteRepo.save(new FavoriteRes(track));
    }

    public List<FavoriteRes> getAll() {
        return favoriteRepo.findAll();
    }

    public void dropAll() {
        favoriteRepo.deleteAll();
    }

    public List<FavoriteRes> getNtracks(int n) {
        if (favoriteRepo.findAll().size() < n)
            return favoriteRepo.findAll();
        else
            return favoriteRepo.findAll().subList(0, n);
    }
}
