package com.musicapp.serwer.services;

import com.musicapp.serwer.model.response.FavoriteRes;
import com.musicapp.serwer.model.response.TrackRes;
import com.musicapp.serwer.repositories.FavoriteRepo;
import com.musicapp.serwer.repositories.TrackRepo;
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
    @Autowired
    private TrackService trackService;
    @Autowired
    private TrackRepo trackRepo;

    /**
     * Metoda dodaje utwor do bazy
     *
     * @param id id utworu
     */
    public void addTrack(String id) {
        if (favoriteRepo.findOneByTrackID(id) == null) {
            TrackRes ts = trackService.searchTrackByID(id);
            if (ts.getTitle() != null)
                favoriteRepo.save(new FavoriteRes(ts.getId(), ts.getTitle(), ts.getAlbum().getImg()));
        }
    }

    /**
     * Metoda usuwa utwor z bazy
     *
     * @param id id utworu
     */
    public void dropTrack(String id) {
        favoriteRepo.deleteByTrackID(id);
    }

    /**
     * Metoda pobiera wszystkie ulubione utwory z bazy
     *
     * @return Lista ulubionych utworow
     */
    public List<FavoriteRes> getAll() {
        return favoriteRepo.findAll();
    }


    /**
     * Metoda pobiera wszystkie ulubione utwory z bazy wraz z dodatkowymi informacjami
     *
     * @return Lista ulubionych utworow
     */
    public List<TrackRes> getAllTrack() {
        StringBuilder url = new StringBuilder("https://api.spotify.com/v1/tracks?ids=");
        if (favoriteRepo.findAll().size() > 0) {
            List<FavoriteRes> list = favoriteRepo.findAll();
            for (int i = 0; i < list.size(); i++) {
                url.append(list.get(i).getTrackID());
                if (i < list.size() - 1) {
                    url.append(",");
                }
            }
            return trackService.searchTracksByIDs(url.toString());
        }
        return null;
    }

    /**
     * Metoda usuwa wszystkie ulubione utwory z bazy
     */
    public void dropAll() {
        favoriteRepo.deleteAll();
    }

    /**
     * Metoda pobiera jeden utwor o podanym id
     *
     * @param id id utworu
     * @return ulubiony utwor
     */
    public TrackRes findOne(String id) {
        FavoriteRes fr = favoriteRepo.findOneByTrackID(id);
        if (fr == null) {
            return null;
        } else {
            return trackService.searchTrackByID(id);
        }
    }

    /**
     * Metoda pobiera n ulubionych utworów
     *
     * @param n id utworu
     * @return Zwraca n ulubionych
     */
    public List<TrackRes> getNtracks(int n) {
        StringBuilder url = new StringBuilder("https://api.spotify.com/v1/tracks?ids=");
        if (favoriteRepo.findAll().size() < n) {
            List<FavoriteRes> list = favoriteRepo.findAll();
            for (int i = 0; i < list.size(); i++) {
                url.append(list.get(i).getTrackID());
                if (i < list.size() - 1) {
                    url.append(",");
                }
            }
            return trackService.searchTracksByIDs(url.toString());
        } else {
            List<FavoriteRes> list = favoriteRepo.findAll().subList(0, n);
            for (int i = 0; i < list.size(); i++) {
                url.append(list.get(i).getTrackID());
                if (i < list.size() - 1) {
                    url.append(",");
                }
            }
            return trackService.searchTracksByIDs(url.toString());
        }
    }
}
