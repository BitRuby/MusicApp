package com.musicapp.serwer.repositories;

import com.musicapp.serwer.model.response.FavoriteRes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfejs dostarczający podstawowe operacje na bazie na tabeli FavoriteRepo
 */
@Repository
public interface FavoriteRepo extends MongoRepository<FavoriteRes,String> {
    FavoriteRes findOneByTrackID(String trackID);
    void deleteByTrackID(String trackID);
}
