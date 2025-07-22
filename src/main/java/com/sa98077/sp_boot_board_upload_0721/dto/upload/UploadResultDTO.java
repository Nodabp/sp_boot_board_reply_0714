package com.sa98077.sp_boot_board_upload_0721.dto.upload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
    private String uuid;

    private String fileName;

    private boolean isImage; // 이미지 파일 여부 확인.

    public String getLink(){
        if(isImage){
            return "s_"+uuid+"_"+fileName;
        }
        return uuid+"_"+fileName;
    }
}
