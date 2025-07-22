package com.sa98077.sp_boot_board_upload_0721.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

//일종의 vo 형태

@Entity
@Getter

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "imageSet")

public class Board extends BaseEntity{
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 타입을 identity에 맡긴다.
    private Long bno;

    @Column(length=500 , nullable=false) // length = 테이블의 길이 , nullable 널가능 ? = 안됨
    private String title;

    @Column(length=2000 , nullable=false)
    private String content;

    @Column(length=50 , nullable=false)
    private String writer;

    @OneToMany(mappedBy = "board",
            cascade = CascadeType.ALL ,
            fetch = FetchType.LAZY) // cascade 옵션을 여기다 줄수 있다.
    @Builder.Default
    private Set<BoardImage> imageSet = new HashSet<>();

    public void addImage(String uuid, String fileName){
        BoardImage boardImage = BoardImage.builder()
                .uuid(uuid)
                .fileName(fileName)
                .board(this)
                .ord(imageSet.size()) // imageSet.size() 를 이용해 정렬 가능한 값 지정하기. index 0이면 1이겠지 ?
                .build();
        imageSet.add(boardImage);
    }
    public void clearImage(){
        imageSet.forEach(boardImage -> boardImage.changeBoard(null)); // 내부 값을 모두 null 로 바꾸고
        this.imageSet.clear(); // 클리어.
    }

    public void change(String title, String content ,String writer){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
