package com.sa98077.sp_boot_board_upload_0721.service;

import com.sa98077.sp_boot_board_upload_0721.dto.BoardDTO;
import com.sa98077.sp_boot_board_upload_0721.dto.BoardListReplyCountDTO;
import com.sa98077.sp_boot_board_upload_0721.dto.PageRequestDTO;
import com.sa98077.sp_boot_board_upload_0721.dto.PageResponseDTO;

public interface BoardService {
    Long add(BoardDTO boardDTO); // 번호를 반환하도록 메서드 설정해보자.
    BoardDTO searchOne(Long bno);
    void modify(BoardDTO boardDTO);
    void remove(Long bno);
    PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);

    // 댓글의 숫자까지 처리
    PageResponseDTO<BoardListReplyCountDTO> getListWithReplyCount(PageRequestDTO pageRequestDTO);
}
