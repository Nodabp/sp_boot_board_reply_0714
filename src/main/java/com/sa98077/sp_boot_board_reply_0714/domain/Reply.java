package com.sa98077.sp_boot_board_reply_0714.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reply", indexes = {
        @Index(name = "idx_reply_board_bno", columnList = "board_bno")
})
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "board") // ToString 처리시 board 를 제외 할 것.
public class Reply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

//    private Long bno; fk 처리해야됨 -> board 의 bno
    @ManyToOne(fetch = FetchType.LAZY)// 다 대일 패치 명시. , lazy 를 통한 지연 로딩.
    private Board board;

    private String replyText;
    private String replyWriter;
}
