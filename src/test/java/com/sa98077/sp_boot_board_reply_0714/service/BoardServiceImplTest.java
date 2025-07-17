package com.sa98077.sp_boot_board_reply_0714.service;

import com.sa98077.sp_boot_board_reply_0714.dto.BoardDTO;
import com.sa98077.sp_boot_board_reply_0714.dto.BoardListReplyCountDTO;
import com.sa98077.sp_boot_board_reply_0714.dto.PageRequestDTO;
import com.sa98077.sp_boot_board_reply_0714.dto.PageResponseDTO;
import com.sa98077.sp_boot_board_reply_0714.repository.BoardRepository;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@Log4j2
@SpringBootTest

class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void add() {
        BoardDTO boardDTO = BoardDTO.builder()
                .title("service add title")
                .content("service add content")
                .writer("service add writer")
                .build();
        log.info("add service test {}",boardService.add(boardDTO));
    }
    @Test
    void searchOne() {
        BoardDTO boardDTO = boardService.searchOne(103L);
        log.info("searchOne service test {}",boardDTO);
    }
    @Test
    void modify() {
        BoardDTO boardDTO = BoardDTO.builder()
                .bno(103L)
                .title("modify title")
                .content("modify content")
                .build();
        boardService.modify(boardDTO);
    }
    @Test
    void remove() {
        boardService.remove(103L);
    }

    @Test
    void getList() {

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .keyword("7")
                .type("tc")
                .build();
        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.getList(pageRequestDTO);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        log.info("pageResponseDTO : {}",pageResponseDTO);
        pageResponseDTO.getDtoList().forEach(log::info);

    }
    @Test
    void searchWithReplyCount(){
        String[] types = new String[]{"t","c","w"};
        String keyword = "0";
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<BoardListReplyCountDTO> result = boardRepository.searchWithReplyCount(types, keyword, pageable);

        log.info("total getTotalElements : {}",result.getTotalElements());

        List<BoardListReplyCountDTO> dtoList = result.getContent();
        dtoList.forEach(log::info);
    }
    @Test
    void getListWithReplyCount() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .keyword("0")
                .type("tcw")
                .build();
        PageResponseDTO<BoardListReplyCountDTO> pageResponseDTO = boardService.getListWithReplyCount(pageRequestDTO);
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        log.info("pageResponseDTO : {}",pageResponseDTO);
        pageResponseDTO.getDtoList().forEach(log::info);
    }
}