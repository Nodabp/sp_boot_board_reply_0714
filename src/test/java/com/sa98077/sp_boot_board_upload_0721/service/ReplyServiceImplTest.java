package com.sa98077.sp_boot_board_upload_0721.service;

import com.sa98077.sp_boot_board_upload_0721.dto.PageRequestDTO;
import com.sa98077.sp_boot_board_upload_0721.dto.ReplyDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Log4j2
@SpringBootTest
class ReplyServiceImplTest {

    @Autowired
    private ReplyService replyService;

    @Test
    void register() {

        var bno = 99L;

        ReplyDTO replyDTO = ReplyDTO.builder()
                .replyText("title99_reply2")
                .replyWriter("title99_reply2")
                .bno(bno)
                .build();

        log.info(replyService.register(replyDTO));
    }
    @Test
    void read() {
        var rno = 6L;
        ReplyDTO replyDTO = replyService.read(rno);
        log.info(replyDTO);
    }
    @Test
    void modify() {
        // 1. rno 선언
        var rno = 6L;

        // 2. dto 빌드
        ReplyDTO replyDTO = ReplyDTO.builder()
                .rno(rno)
                .replyText("modify title")
                .build();

        // 3. 서비스 호출
        replyService.modify(replyDTO);

        // 4. read(rno) 로 확인.
        log.info("read rno : {}",replyService.read(rno));
    }
    @Test
    void remove() {
        // 1. rno 선언
        var rno = 6L;

        // 2. 서비스 호출
        replyService.remove(rno);

        // 3. DB 에서 확인 OR READ 로 확인 ( 에러뜰것. )
    }
    @Test
    void getList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .page(1)
                .size(10)
                .build();

        var bno = 100L;
        log.info("pageRequestDTO : {}",pageRequestDTO);
        replyService.getList(bno,pageRequestDTO).getDtoList().forEach(log::info);
    }
}