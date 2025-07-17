package com.sa98077.sp_boot_board_reply_0714.component;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ServerReadyListener {

    private static boolean isReady = false;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        isReady = true;
        log.info(" 서버가 완전히 로딩되었습니다! ");
        // 여기에 초기화 작업 또는 리다이렉트 조건 설정 가능
    }

    public static boolean isServerReady() {
        return isReady;
    }
}
