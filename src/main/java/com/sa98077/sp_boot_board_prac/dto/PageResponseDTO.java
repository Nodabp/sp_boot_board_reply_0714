package com.sa98077.sp_boot_board_prac.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class PageResponseDTO<E> {

    private final int page;
    private final int size;
    private final int total;

    // 시작 페이지 번호
    private final int start;

    // 끝 페이지 번호
    private final int end;

    // 이전 페이지의 존재 여부
    private final boolean prev;

    // 다음페이지의 존재 여부
    private final boolean next;

    // 리스트를 어떤 타입으로 오더라도 사용 가능하도록 하는 제네릭 리스트.
    private final List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.dtoList = dtoList;
        this.total = total;

        // 기존 계산식 1부터 시작하는.
//        this.end = (int) (Math.ceil(this.page / 10.0)) * 10; // ceil 올림.
//        this.start = this.end - 9;
//
//        int last = (int) Math.ceil(total / (double) size);
//
//        this.end = Math.min(end,last); // 둘 중 작은 값으로 this.end 초기화.
//        this.prev = this.start > 1; // 이전 페이지의 존재 여부
//        this.next = total > this.end * this.size; // 다음 페이지의 존재 여부.

        // ai가 선정한 기존 페이지 네이션.
//        int pageBlock = 10; // 한 번에 보여줄 페이지 번호 수
//        int last = (int) Math.ceil((double) total / size);
//
//        int tempEnd = (int) (Math.ceil(page / (double) pageBlock)) * pageBlock;
//        this.start = tempEnd - (pageBlock - 1);
//        this.end = Math.min(tempEnd, last);
//
//        this.prev = this.start > 1;
//        this.next = this.end < last;

     // 중앙으로 오도록 하는 페이지 네이션.

        int pageBlock = 10; // 한 번에 보여줄 페이지 번호 수
        int last = (int) Math.ceil((double) total / size); // 전체 페이지 수

        // 중앙 정렬 계산
        int half = pageBlock / 2;
        int tempStart = Math.max(1, page - half); // 시작값을 현제값의 절반으로 반환. 1이 더 크다면 1 반환.
        int tempEnd = tempStart + pageBlock - 1; // 끝 값

        // 끝 페이지가 전체 페이지 수를 넘지 않도록 조정
        if (tempEnd > last) {
            tempEnd = last;
            tempStart = Math.max(1, tempEnd - pageBlock + 1);
        }

        this.start = tempStart;
        this.end = tempEnd;

        this.prev = this.start > 1;
        this.next = this.end < last;

    }
}
