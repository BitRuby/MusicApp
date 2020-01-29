package com.musicapp.serwer.repositories;
import com.musicapp.serwer.model.response.TrackRes;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackRepo extends MongoRepository<TrackRes,String> {
}