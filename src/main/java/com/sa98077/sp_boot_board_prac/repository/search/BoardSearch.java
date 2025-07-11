package com.sa98077.sp_boot_board_prac.repository.search;

import com.sa98077.sp_boot_board_prac.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardSearch{

    Page<Board> search1(Pageable pageable); // 연습용임
    Page<Board> searchAll(String[] types, String keyword, Pageable pageable);
}
