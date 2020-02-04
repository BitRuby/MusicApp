package com.musicapp.serwer.repositories;

import com.musicapp.serwer.model.response.TrackRes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfejs dostarczający podstawowe operacje na bazie na tabeli TrackRepo
 */
@Repository
public interface TrackRepo extends MongoRepository<TrackRes, String> {
    TrackRes findAllById(String id);
}