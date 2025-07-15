package com.sa98077.sp_boot_board_reply_0714.controller;


import com.sa98077.sp_boot_board_reply_0714.dto.ReplyDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/replies")
public class ReplyController {

    @Operation(summary = "Replies Post", description = "POST 방식으로 댓글 등록") // operation 어노테이션 -> swagger 에 표시하기 위함.
    @PostMapping(value = "/",consumes = MediaType.APPLICATION_JSON_VALUE)
    // json 타입으로 리턴 받을 것. , 미디어타입은  스프링 프레임워크 사용함.(json) consume = 소비
    public Map<String,Long> register(@Valid @RequestBody ReplyDTO replyDTO,
                                     BindingResult bindingResult) throws BindException { //{'rno':11} 와 비슷한 타입인 Map 사용.
        log.info("replyDTO : {}",replyDTO);

        if(bindingResult.hasErrors()){
            throw new BindException(bindingResult); // 에러발생시 advice로 던지기 위해 ..
        }


        Map<String,Long> map = new HashMap<>();
        map.put("rno",111L);

        return map;
    }


}
