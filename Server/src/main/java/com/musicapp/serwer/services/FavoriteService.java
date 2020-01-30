package com.musicapp.serwer.services;

import com.musicapp.serwer.model.response.FavoriteRes;
import com.musicapp.serwer.repositories.FavoriteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serwis wykozystywany do operacji na ulubionych utworach
 */
@Service
public class FavoriteService {

    @Autowired
    private FavoriteRepo favoriteRepo;

    /**
     * Metoda dodaje utwor do bazy
     * @param track id utworu
     */
    public void addTrack(String track) {
        favoriteRepo.save(new FavoriteRes(track));
    }

    /**
     * Metoda pobiera wszystkie ulubione utwory z bazy
     * @return  Lista ulubionych utworow
     */
    public List<FavoriteRes> getAll() {
        return favoriteRepo.findAll();
    }

    /**
     * Metoda usuwa wszystkie ulubione utwory z bazy
     */
    public void dropAll() {
        favoriteRepo.deleteAll();
    }

    /**
     * Metoda pobiera jeden utwor o podanym id
     * @param id id utworu
     * @return  ulubiony utwor
     */
    public FavoriteRes findOne(String id){
        return favoriteRepo.findOneByTrackID(id);
    }

    /**
     * Metoda pobiera n ulubionych utworów
     * @param n id utworu
     * @return  Zwraca n ulubionych
     */
    public List<FavoriteRes> getNtracks(int n) {
        if (favoriteRepo.findAll().size() < n)
            return favoriteRepo.findAll();
        else
            return favoriteRepo.findAll().subList(0, n);
    }
}
