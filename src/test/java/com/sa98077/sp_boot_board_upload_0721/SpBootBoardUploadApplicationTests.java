package com.sa98077.sp_boot_board_upload_0721;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Log4j2
@SpringBootTest
class SpBootBoardUploadApplicationTests {
    @Autowired
    private DataSource dataSource;


    @Test
    void connectionTest() throws SQLException {
        @Cleanup Connection connection = dataSource.getConnection();
        log.info("conn : {}",connection);
        log.info("한글테스트");
        Assertions.assertNotNull(connection);
    }

}
