package COSAS.projection;

import java.util.Set;
import java.util.UUID;

public interface ProjectionAnimeWithoutDoblador {
    UUID getAnimeid();
    String getName();
    Set<ProjectionDobladorWithoutAnime> getDoblador();
}
