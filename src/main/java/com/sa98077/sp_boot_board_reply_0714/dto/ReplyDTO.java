package com.sa98077.sp_boot_board_reply_0714.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") // json 보낼때 포멧~
    private LocalDateTime regDate;

    @JsonIgnore // json 출력시 제외하라 ~
    private LocalDateTime modDate;
}
