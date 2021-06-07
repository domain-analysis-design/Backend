package com.example.demo.dto;

import lombok.Builder;
import lombok.Data;

public class CardDTO {

    @Data
    @Builder
    public static class createCardDTO{
        private String name;
        private String description;
        private Long listId;
        private Long userId;
    }

    @Data
    @Builder
    public static class createCommentDTO{
        private Long cardId;
        private String message;
        private Long userId;
    }

    @Data
    @Builder
    public static class createItemDTO{
        private Long cardId;
        private String contents;
        private String state;
        private Long userId;
    }

    @Data
    @Builder
    public static class updateListCardDTO{
        private Long listId;
        private Long cardId;
        private Long userId;
    }

    @Data
    @Builder
    public static class updateBoardCardDTO{
        private Long sourceBoardId;
        private Long targetBoardId;
        private Long cardId;
        private String status;
        private Long userId;
    }

    @Data
    @Builder
    public static class updateCardDTO{
        private Long cardId;
        private String cardName;
        private String description;
        private Long userId;
    }

    @Data
    @Builder
    public static class updateCommentDTO {
        private Long commentId;
        private String message;
        public Long userId;
    }

    @Data
    @Builder
    public static class updateItemDTO {
        private Long itemId;
        private String contents;
        private String state;
        public Long userId;
    }
}
