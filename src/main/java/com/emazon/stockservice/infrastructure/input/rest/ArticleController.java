package com.emazon.stockservice.infrastructure.input.rest;

import com.emazon.stockservice.application.dto.article.ArticleRequest;
import com.emazon.stockservice.application.dto.article.ArticleResponse;
import com.emazon.stockservice.application.handler.article.ArticleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.emazon.stockservice.infrastructure.output.jpa.repository.ArticleRepository;
import com.emazon.stockservice.infrastructure.output.jpa.entities.ArticleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
@Tag(name = "Articles", description = "Operations related to Articles management")
public class ArticleController {

    private final ArticleHandler articleHandler;

    @PostMapping("/article")
    @Operation(summary = "Create a new article", description = "Creates a new article with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Article created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input, object invalid"),
            @ApiResponse(responseCode = "409", description = "Article already exists")
    })
    public ResponseEntity<ArticleResponse> createArticle( @Valid @RequestBody ArticleRequest articleRequest) {
        ArticleResponse response = articleHandler.createArticle(articleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
