package com.Musify.DataRepositories;

import com.Musify.DataTables.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Integer> {
}
