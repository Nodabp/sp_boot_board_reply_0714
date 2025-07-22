package com.sa98077.sp_boot_board_upload_0721.controller;


import com.sa98077.sp_boot_board_upload_0721.dto.upload.UploadFileDTO;
import com.sa98077.sp_boot_board_upload_0721.dto.upload.UploadResultDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Log4j2
@RestController
public class UpDownController {
    @Value("${com.sa98077.upload.path}")
    private String uploadPath;

    @Operation(summary = "POST 방식으로 파일 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<UploadResultDTO> upload(UploadFileDTO uploadFileDTO) {
        log.info("************ UPLOAD ************");
        log.info("uploadFileDTO : {}", uploadFileDTO);
        // 파일의 업로드 하는 방법 , 이름뒤에 + (2)... 또는 UUID 로 작업. 이경우는 UUID 작업할것.

        if (uploadFileDTO.getUploadFile() != null) {
            // 업로드 된 파일들을 저장할 리스트
            List<UploadResultDTO> list = new ArrayList<>();

            for (MultipartFile file : uploadFileDTO.getUploadFile()) {

                // 원 파일명 로깅.
                String originalName = file.getOriginalFilename();
                log.info("originalName : {}", originalName);

                // UUID 생성 및 파일 네임 세팅.
                String uuid = UUID.randomUUID().toString();

                // 저장할 경로를 가져옴. 파일이름 포함. , Path 타입.
                Path path = Paths.get(uploadPath, uuid + "_" + originalName);

                log.info("path : {}", path);

                boolean isImage = false;

                // 패스로 저장하기. transferTo.
                try {
                    file.transferTo(path); // 파일이 저장됨. path에 이름으로.

                    // 이미지 파일일 경우 썸네일 생성 하기.
                    if (Files.probeContentType(path).startsWith("image")) { // 첨부된 파일이 image 인지 체크.
                        log.info(Files.probeContentType(path)); // 로그로 찍어보고...

                        isImage = true;

                        //썸네일 생성
                        File thumbnail = new File(uploadPath, "s_" + uuid + "_" + originalName);
                        Thumbnailator.createThumbnail(path.toFile(), thumbnail, 200, 200);
                    }

                } catch (IOException e) {
                    log.error(e.getMessage());
                }
                // for 문이 끝나는 지점에서 list 에 업로드
                list.add(UploadResultDTO.builder()
                        .uuid(uuid)
                        .isImage(isImage)
                        .fileName(originalName)
                        .build());
            }
            return list;
        }
        return null;
    }

    @Operation(summary = "GET 방식으로 첨부파일 조회")
    @GetMapping(value = "/view/{fileName}")
    public ResponseEntity<Resource> view(@PathVariable("fileName") String fileName) {
        log.info("************ VIEW ************");

        // 파일경로로 리소스를 만들어옴  // 요청된 파일명을 기반으로 파일 시스템 리소스 생성
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        // 헤더 불러오기    // HTTP 응답 헤더 객체 생성
        HttpHeaders headers = new HttpHeaders();

        try {
            // 파일의 경로에 있는 타입 값확인 // 파일의 MIME 타입을 헤더에 설정
            headers.add("Content-type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) { // 에러가 뜨면 에러코드 리턴 // 파일 타입 확인 중 IOException 발생 시 500 오류 응답 반환

            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource); // 파일 리소스를 HTTP 응답으로 반환 (상태 코드: 200)

    }

    @Operation(summary = "DELETE 방식으로 삭제")
    @DeleteMapping(value = "/remove/{fileName}")
    public Map<String, Boolean> delete(@PathVariable String fileName) {
        log.info("************ DELETE ************");

        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        Map<String, Boolean> resultMap = new HashMap<>(); // 결과를 저장할 맵
        boolean isRemoved = false; // 파일의 삭제 결과를 저장할 변수

        // 삭제처리 (이미지일 경우 섬네일도 같이 삭제 되어야 함 )
        try {
            // 순서가 중요함. 삭제 전에 타입을 가져 와야함
            String contentType = Files.probeContentType(resource.getFile().toPath()); // 이미지인지 확인.
            isRemoved = resource.getFile().delete();

            // 이미지 라면 -> 썸네일이 존재 ,
            if(contentType.startsWith("image")){
                File thumbnailFile = new File(uploadPath, File.separator + "s_"  + fileName);
                thumbnailFile.delete();
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        resultMap.put("result", isRemoved);
        return resultMap;
    }

}
