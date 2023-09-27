package com.Musify.DataRepositories;

import com.Musify.DataTables.Artists;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artists, Integer> {
}
