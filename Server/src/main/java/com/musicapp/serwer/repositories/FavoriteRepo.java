package com.musicapp.serwer.repositories;

import com.musicapp.serwer.model.response.FavoriteRes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepo extends MongoRepository<FavoriteRes,String> {
}
