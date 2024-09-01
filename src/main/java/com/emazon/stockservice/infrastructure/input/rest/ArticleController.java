package com.emazon.stockservice.infrastructure.input.rest;

import com.emazon.stockservice.application.dto.article.ArticleRequest;
import com.emazon.stockservice.application.handler.article.ArticleHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleHandler articleHandler;

    @PostMapping("/article")
    public ResponseEntity<Void> createArticle(@RequestBody ArticleRequest articleRequest) {

        articleHandler.handleArticleCreation(articleRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
