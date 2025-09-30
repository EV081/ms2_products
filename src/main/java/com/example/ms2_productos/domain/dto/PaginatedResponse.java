package com.example.ms2_productos.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PaginatedResponse<T> {
    private List<T> contents;
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
}
