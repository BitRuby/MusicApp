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

    public void addTrack(String track, String artist){
        favoriteRepo.save(new FavoriteRes(track, artist));
    }

    public List<FavoriteRes> getAll(){
        return favoriteRepo.findAll();
    }
}
