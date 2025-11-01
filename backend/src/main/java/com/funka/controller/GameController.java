package com.funka.controller;

import com.funka.dto.domain.GameDetailDTO;
import com.funka.dto.domain.GameListDTO;
import com.funka.dto.domain.ScreenshotDTO;
import com.funka.service.GameService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<GameListDTO> getGames(@RequestParam(defaultValue = "1") Integer page) {
        log.info("GET /api/games - Page: {}", page);
        GameListDTO games = gameService.getGames(page);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/search")
    public ResponseEntity<GameListDTO> searchGames(@RequestParam(required = false) String query, @RequestParam(defaultValue = "1") Integer page) {
        log.info("GET /api/games/search - Query: '{}', Page: {}", query, page);
        GameListDTO games;
        if (query == null || query.trim().isEmpty()) {
            // Si no hay query, devolver todos los juegos (como /api/games)
            games = gameService.getGames(page);
        } else {
            // Si hay query, hacer búsqueda
            games = gameService.searchGames(query, page);
        }
        
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDetailDTO> getGameById(@PathVariable Long id) {
        log.info("GET /api/games/{}", id);
        GameDetailDTO game = gameService.getGameById(id);
        if (game == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(game);
    }

    @GetMapping("/{id}/screenshots")
    public ResponseEntity<List<ScreenshotDTO>> getGameScreenshots(@PathVariable Long id) {
        log.info("GET /api/games/{}/screenshots", id);
        List<ScreenshotDTO> screenshots = gameService.getGameScreenshots(id);
        return ResponseEntity.ok(screenshots);
    }

    @GetMapping("/genre/{genreId}")
    public ResponseEntity<GameListDTO> getGamesByGenre(@PathVariable Long genreId, @RequestParam(defaultValue = "1") Integer page) {
        log.info("GET /api/games/genre/{} - Page: {}", genreId, page);
        GameListDTO games = gameService.getGamesByGenre(genreId, page);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/advanced-search")
    public ResponseEntity<GameListDTO> advancedSearch(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String genres,
            @RequestParam(required = false) String stores,
            @RequestParam(required = false) String ordering,
            @RequestParam(defaultValue = "1") Integer page) {
        log.info("GET /api/games/advanced-search - Search: '{}', Genres: {}, Stores: {}, Ordering: {}, Page: {}", search, genres, stores, ordering, page);
        GameListDTO games = gameService.searchGamesAdvanced(search, genres, stores, ordering, page);
        return ResponseEntity.ok(games);
    }
}