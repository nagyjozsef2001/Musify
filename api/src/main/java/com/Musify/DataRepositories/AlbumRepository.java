package com.Musify.DataRepositories;

import com.Musify.DataTables.Albums;
import org.springframework.data.repository.CrudRepository;

public interface AlbumRepository extends CrudRepository<Albums, Integer> {
}
