package com.sa98077.sp_boot_board_upload_0721.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController // 레스트 컨트롤러 , JSON 파싱등.
public class SampleJSONController {

    @GetMapping("helloArr")
    public String[] helloArr(){
        log.info("api/helloArr");
        return new String[]{"AAA","BBB","CCC","JSON","HAHA"};
    }
}
