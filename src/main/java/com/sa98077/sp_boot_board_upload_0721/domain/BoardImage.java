package com.sa98077.sp_boot_board_upload_0721.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class BoardImage implements Comparable<BoardImage> { // 정렬을 위해 comparable 가져오기.

    @Id
    private String uuid;

    private String fileName;

    private int ord; //order

    @ManyToOne // one two many 하려면 양방향 다 맵핑.
    private Board board;

    @Override
    public int compareTo(BoardImage o) {
        return this.ord - o.ord;
    }

    public void changeBoard(Board board){
        this.board = board;
    }
}
