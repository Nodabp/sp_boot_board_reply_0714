package com.sa98077.sp_boot_board_prac.controller;


import com.sa98077.sp_boot_board_prac.dto.PageRequestDTO;
import com.sa98077.sp_boot_board_prac.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // 생성자 주입방식
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO,
                     Model model) {
        log.info("************** list *************");

    }
}
