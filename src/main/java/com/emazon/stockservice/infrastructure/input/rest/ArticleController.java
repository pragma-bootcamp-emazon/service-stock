package com.emazon.stockservice.infrastructure.input.rest;

import com.emazon.stockservice.application.dto.article.ArticleRequest;
import com.emazon.stockservice.application.handler.article.ArticleHandler;
import com.emazon.stockservice.infrastructure.output.jpa.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stock")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleHandler articleHandler;
    private final ArticleRepository articleRepository;

    @PostMapping("/article")
    public ResponseEntity<Void> createArticle(@RequestBody ArticleRequest articleRequest) {
        articleHandler.createArticle(articleRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
