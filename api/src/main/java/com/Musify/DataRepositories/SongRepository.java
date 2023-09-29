package com.Musify.DataRepositories;

import com.Musify.DataTables.Songs;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Songs, Integer> {
}
