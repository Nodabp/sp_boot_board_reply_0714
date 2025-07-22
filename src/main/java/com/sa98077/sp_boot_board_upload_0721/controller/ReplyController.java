package com.sa98077.sp_boot_board_upload_0721.controller;


import com.sa98077.sp_boot_board_upload_0721.dto.PageRequestDTO;
import com.sa98077.sp_boot_board_upload_0721.dto.PageResponseDTO;
import com.sa98077.sp_boot_board_upload_0721.dto.ReplyDTO;
import com.sa98077.sp_boot_board_upload_0721.service.ReplyService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/replies")
@RequiredArgsConstructor

public class ReplyController {

    private final ReplyService replyService;

    @Operation(summary = "Replies Post", description = "POST 방식으로 댓글 등록") // operation 어노테이션 -> swagger 에 표시하기 위함.
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    // json 타입으로 리턴 받을 것. , 미디어타입은  스프링 프레임워크 사용함.(json) consume = 소비
    public Map<String, Long> register(@Valid @RequestBody ReplyDTO replyDTO,
                                      BindingResult bindingResult) throws BindException { //{'rno':11} 와 비슷한 타입인 Map 사용.
        log.info("replyDTO : {}", replyDTO);

        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult); // 에러발생시 advice로 던지기 위해 ..
        }

        // 서비스 호출
        Long rno = replyService.register(replyDTO);

        // 응답 처리 (rest 확인하기)
        Map<String, Long> map = new HashMap<>();
        map.put("rno", rno);

        return map; // swagger에 response body에 출력됨 !
    }

    @Operation(summary = "Replies of Board GET", description = "GET 방식으로 리플 확인")
    @GetMapping(value = "/list/{bno}")
    public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Long bno,
                                             PageRequestDTO pageRequestDTO) {
        return replyService.getList(bno, pageRequestDTO);
    }

    @Operation(summary = "Read Reply GET", description = "GET 방식으로 댓글 조회")
    @GetMapping(value = "/{rno}")
    public ReplyDTO getOne(@PathVariable("rno") Long rno) {
        return replyService.read(rno);
    }
    @Operation(summary = "remove Reply Delete", description = "Delete 방식으로 댓글 삭제")
    @DeleteMapping(value = "/{rno}")
    public Map<String, Long> removeReply(@PathVariable("rno") Long rno) {

        // 1. 서비스 호출
        replyService.remove(rno);

        // 2. 맵방식으로 rno 저장 ( json 형식으로 하기 위함 )
        Map<String, Long> map = new HashMap<>();
        map.put("rno", rno);

        // 3. map 리턴함.
        return map;
    }
    @Operation(summary = "modify Reply Delete", description = "put 방식으로 댓글 수정")
    @PutMapping(value = "/{rno}")
    public Map<String, Long> modifyReply(@PathVariable("rno") Long rno,
                                         @RequestBody  ReplyDTO replyDTO){

        // 0. replyDTO 에 rno 셋팅.
        replyDTO.setRno(rno);

        // 1. 서비스 호출
        replyService.modify(replyDTO);

        // 2. 맵방식으로 rno 저장 ( json 형식으로 하기 위함 )
        Map<String, Long> map = new HashMap<>();
        map.put("rno", rno);

        // 3. map 리턴함.
        return map;
    }
}
