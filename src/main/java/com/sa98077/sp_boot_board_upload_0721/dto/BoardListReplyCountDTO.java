package com.sa98077.sp_boot_board_upload_0721.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data

public class BoardListReplyCountDTO {

    private Long bno;
    private String title;
    private String writer;
    private LocalDateTime regDate;

    private Long replyCount; // 리플 갯수
}
