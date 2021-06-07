package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

public class BoardDTO {

    @Data
    @Builder
    @AllArgsConstructor
    public static class createBoardDTO{
        private String name;
        private Long userId;
    }

    @Data
    @Builder
    public static class boardToUserDTO{
        private Long boardId;
        private Long userId;
        private Long createdBy;
        private Long updatedBy;
    }

    @Data
    @Builder
    public static class updateBoardDTO{
        private Long id;
        private String name;
    }

    @Data
    @Builder
    public static class deleteBoardDTO{
        private Long boardId;
        private Long userId;
    }

    @Data
    @Builder
    public static class inviteUserDTO{
        private Long boardId;
        private Long invitingUserId;
        private Long invitedUserId;
    }

    @Data
    @Builder
    public static class kickUserDTO{
        private Long boardId;
        private Long kickingUserId;
        private String kickedUserName;
    }
}
