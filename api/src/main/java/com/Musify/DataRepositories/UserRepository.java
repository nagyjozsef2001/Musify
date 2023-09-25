package com.Musify.DataRepositories;

import com.Musify.DataTables.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {
    @Query("SELECT u FROM Users u WHERE u.id = :id AND u.email = :email")
    Users findByUserId(Integer id, String email);

    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Users findByUserName(String email);
}
