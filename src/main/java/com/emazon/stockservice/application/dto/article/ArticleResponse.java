package com.emazon.stockservice.application.dto.article;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArticleResponse {

    private Long id;
    private String name;
    private String description;
 }
