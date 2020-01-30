package com.musicapp.serwer.repositories;

import com.musicapp.serwer.model.response.PlaylistRes;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaylistRepo extends MongoRepository<PlaylistRes,String> {
}
