package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

public class ListDTO {

    @Data
    @Builder
    public static class createListDTO{
        private String name;
        private Long boardId;
        private Long userId;
    }

    @Data
    @Builder
    public static class updateListDTO{
        Long id;
        String name;
        Long userId;
    }

    @Data
    @Builder
    public static class deleteListDTO{
        Long boardId;
        Long listId;
    }
}
