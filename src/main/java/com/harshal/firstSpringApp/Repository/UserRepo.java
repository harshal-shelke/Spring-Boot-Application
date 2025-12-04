package com.harshal.firstSpringApp.Repository;

import com.harshal.firstSpringApp.entity.JournalEntry;
import com.harshal.firstSpringApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);

    void deleteByUsername(String name);
}
