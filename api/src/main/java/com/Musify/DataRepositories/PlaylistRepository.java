package com.Musify.DataRepositories;

import com.Musify.DataTables.Playlists;
import org.springframework.data.repository.CrudRepository;

public interface PlaylistRepository extends CrudRepository<Playlists, Integer> {
}
