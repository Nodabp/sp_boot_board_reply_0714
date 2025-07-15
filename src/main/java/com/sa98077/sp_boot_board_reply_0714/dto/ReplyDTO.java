package com.sa98077.sp_boot_board_reply_0714.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ReplyDTO {
    private Long rno; // 리플 고유번호

    @NotNull
    private Long bno; // 게시판의 글 고유번호.

    @NotEmpty
    private String replyText; // 리플내용

    @NotEmpty
    private String replyWriter; // 리플 작성자

    private LocalDateTime regDate, modDate;
}
