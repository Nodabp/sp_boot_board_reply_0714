package com.sa98077.sp_boot_board_upload_0721.repository;

import com.sa98077.sp_boot_board_upload_0721.domain.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply,Long> {
    @Query("select r from Reply r where r.board.bno = :bno") //JPQL문
    Page<Reply> listOfBoard(@Param("bno") Long bno, Pageable pageable);
}
