package com.sa98077.sp_boot_board_upload_0721.controller.load;

import com.sa98077.sp_boot_board_upload_0721.component.ServerReadyListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/")
    public String redirectToBoard() {
        if (ServerReadyListener.isServerReady()) {
            return "redirect:/board/dash";
        } else {
            return "error/loading"; // 서버 준비 중일 때 보여줄 페이지
        }
    }
}
