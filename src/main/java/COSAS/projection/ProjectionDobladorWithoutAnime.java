package COSAS.projection;

import java.util.Set;
import java.util.UUID;

public interface ProjectionDobladorWithoutAnime {
    UUID getAnimeid();
    String getName();
    Set<ProjectionAnimeWithoutDoblador> getAnime();
}
