package com.sa98077.sp_boot_board_upload_0721.repository;


import com.sa98077.sp_boot_board_upload_0721.domain.Board;
import com.sa98077.sp_boot_board_upload_0721.repository.search.BoardSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board,Long>, BoardSearch {
    //Board 상속, id의 데이터 타입 지정.
    // 인터페이스 까지만 만들면... crud 완성.
}
