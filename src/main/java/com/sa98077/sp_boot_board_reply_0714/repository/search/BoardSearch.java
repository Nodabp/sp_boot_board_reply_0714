package com.sa98077.sp_boot_board_reply_0714.repository.search;

import com.sa98077.sp_boot_board_reply_0714.domain.Board;
import com.sa98077.sp_boot_board_reply_0714.dto.BoardListReplyCountDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch{

    Page<Board> search1(Pageable pageable); // 연습용임
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);

    Page<BoardListReplyCountDTO> searchWithReplyCount(String[] types, String keyword, Pageable pageable);
}
