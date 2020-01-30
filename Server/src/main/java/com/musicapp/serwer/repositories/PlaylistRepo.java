package com.musicapp.serwer.repositories;

import com.musicapp.serwer.model.response.PlaylistRes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfejs dostarczający podstawowe operacje na bazie na tabeli PlaylistRepo
 */
@Repository
public interface PlaylistRepo extends MongoRepository<PlaylistRes,String> {
}
