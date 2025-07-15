package com.sa98077.sp_boot_board_reply_0714.controller;


import com.sa98077.sp_boot_board_reply_0714.dto.BoardDTO;
import com.sa98077.sp_boot_board_reply_0714.dto.PageRequestDTO;
import com.sa98077.sp_boot_board_reply_0714.dto.PageResponseDTO;
import com.sa98077.sp_boot_board_reply_0714.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        PageResponseDTO<BoardDTO> pageResponseDTO = boardService.getList(pageRequestDTO);

        log.info("pageResponseDTO : {}", pageResponseDTO);
        log.info("pageRequestDTO : {}", pageRequestDTO);

        model.addAttribute("responseDTO", pageResponseDTO);
    }

    @GetMapping("/add")
    public void add(Model model) {
        log.info("************** add(get) *************");
        // error FlashAttribute 받아오기
        if (model.containsAttribute("error")) {
            log.info("error attribute present");
        }

    }

    @PostMapping("/add")
    public String add(@Valid BoardDTO boardDTO,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {
        log.info("************** add(post) *************");
        if (bindingResult.hasErrors()) {
            log.info("bindingResult : {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/board/add";
        }
        log.info("boardDTO : {}", boardDTO);

        long bno = boardService.add(boardDTO);
        redirectAttributes.addFlashAttribute("bno", bno);

        return "redirect:/board/list";
    }

    @GetMapping({"/read","/modify"})
    public void read(Long bno, Model model, PageRequestDTO pageRequestDTO) {
        log.info("************** read(Get) *************");
        BoardDTO boardDTO = boardService.searchOne(bno);
        log.info("boardDTO(read) : {}", boardDTO);

        model.addAttribute("dto", boardDTO);
        model.addAttribute("bno", bno);
        model.addAttribute("pageRequestDTO", pageRequestDTO);
    }

    @PostMapping("/modify")
    public String modify(@Valid BoardDTO boardDTO,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         PageRequestDTO pageRequestDTO
    ) {
        log.info("************** modify(Post) *************");
        if (bindingResult.hasErrors()) {
            log.info("bindingResult : {}", bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("error", bindingResult.getAllErrors());
            return "redirect:/board/modify"+pageRequestDTO.getLink()+ "&bno=" + boardDTO.getBno();
        }
        boardService.modify(boardDTO);
        return "redirect:/board/read"+pageRequestDTO.getLink()+ "&bno=" + boardDTO.getBno();
    }
    @PostMapping("/remove")
    public String remove(Long bno,
                         RedirectAttributes redirectAttributes,
                         PageRequestDTO pageRequestDTO) {
        log.info("************** remove(Post) *************");
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("bno", bno);
        return "redirect:/board/list"+pageRequestDTO.getLink() ;
    }
}
