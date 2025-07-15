package com.sa98077.sp_boot_board_reply_0714.repository;

import com.sa98077.sp_boot_board_reply_0714.domain.Board;
import com.sa98077.sp_boot_board_reply_0714.domain.Reply;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringBootTest
class ReplyRepositoryTest {

    @Autowired
    ReplyRepository replyRepository;

    @Test
    public void insertReply() {
        //실제 db에 있는 bno 사용 해야함
        Long bno = 100L;
        Board board = Board.builder()
                .bno(bno)
                .build();
        Reply reply = Reply.builder()
                .board(board)
                .replyText("댓글테스트")
                .replyWriter("작성자테스트")
                .build();
        replyRepository.save(reply);
    }

}