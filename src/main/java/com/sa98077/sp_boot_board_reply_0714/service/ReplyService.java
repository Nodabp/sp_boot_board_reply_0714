package com.sa98077.sp_boot_board_reply_0714.service;

import com.sa98077.sp_boot_board_reply_0714.dto.PageRequestDTO;
import com.sa98077.sp_boot_board_reply_0714.dto.PageResponseDTO;
import com.sa98077.sp_boot_board_reply_0714.dto.ReplyDTO;


public interface ReplyService {

    Long register(ReplyDTO replyDTO);
    ReplyDTO read(Long rno);
    void modify(ReplyDTO replyDTO);
    void remove(Long rno);
    PageResponseDTO<ReplyDTO> getList(Long bno, PageRequestDTO pageRequestDTO);
}
