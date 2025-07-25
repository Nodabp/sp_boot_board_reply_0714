package com.sa98077.sp_boot_board_upload_0721.repository;

import com.sa98077.sp_boot_board_upload_0721.domain.Board;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void insert() {
        for (int i = 1; i <= 100; i++) {
            Board board = Board.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer("user" + (i % 10))
                    .build();
            Board result = boardRepository.save(board); // 객체가 반환됨
            log.info("bno : {}", result.getBno());
        }
    }
    @Test
    void selectAll() {
        var board = boardRepository.findAll(); // Optional<T> 타입임.
        board.forEach(log::info);
    }
    @Test
    void selectOne() {
        var board = boardRepository.findById(50L);
        log.info("board : {}",board);
    }

    @Test
    void update () {
        Optional<Board> result = boardRepository.findById(111L);
        Board board = result.orElseThrow();

        board.change("update title","update content","update writer");
        boardRepository.save(board);

    }
    @Test
    void update2 () {

        Board board = Board.builder()
                .content("update2 title")
                .title("update2 title")
                .writer("update2 writer")
                .build();
        boardRepository.save(board);
    }

    @Test
    void delete () {
        boardRepository.deleteById(101L);
    }

    @Test
    public void paging(){
        // page order by desc

        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());
        Page<Board> result = boardRepository.findAll(pageable);
//        result.getContent().forEach(log::info);

        log.info("total pages : {}",result.getTotalPages());
        log.info("total elements : {}",result.getTotalElements());
        log.info("number of elements : {}",result.getNumberOfElements());
        log.info("number of page : {}",result.getNumber());
        log.info("size of page : {}",result.getSize());
        log.info("pageable : {}",result.getPageable());
        log.info("content : {}",result.getContent());
        log.info("is empty : {}",result.isEmpty());
        log.info("has content : {}",result.hasContent());

        log.info("is first page : {}",result.isFirst());
        log.info("is last page : {}",result.isLast());

        log.info("has previous page : {}",result.hasPrevious());
        log.info("has next page : {}",result.hasNext());

        result.forEach(log::info);

    }

    @Test
    void search1() {
        Pageable pageable = PageRequest.of(1,10, Sort.by("bno").descending());
        boardRepository.search1(pageable);
    }

    @Test
    void searchAll() {

        String[] types = new String[]{"t","c"};
        String keyword = "6";
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

       boardRepository.searchAll(types, keyword, pageable);
    }
    @Test
    void searchAll2() {

        String[] types = new String[]{"t","c","w"};
        String keyword = "up";
        Pageable pageable = PageRequest.of(0,10, Sort.by("bno").descending());

        Page<Board> result = boardRepository.searchAll(types, keyword, pageable);


        log.info("total pages : {}",result.getTotalPages());
        log.info("total elements : {}",result.getTotalElements());
        log.info("number of elements : {}",result.getNumberOfElements());
        log.info("number of page : {}",result.getNumber());
        log.info("size of page : {}",result.getSize());
        log.info("pageable : {}",result.getPageable());
        log.info("content : {}",result.getContent());
        log.info("is empty : {}",result.isEmpty());
        log.info("has content : {}",result.hasContent());
        log.info("is first page : {}",result.isFirst());
        log.info("is last page : {}",result.isLast());
        log.info("has previous page : {}",result.hasPrevious());
        log.info("has next page : {}",result.hasNext());

        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------");
        result.forEach(log::info);
    }


    @Test
    void insertWithImage() {

            Board board = Board.builder()
                    .title("title with image")
                    .content("첨부파일 테스트")
                    .writer("user")
                    .build();

            for (int i = 0; i < 3; i++) {
                board.addImage(UUID.randomUUID().toString(),"file"+(i + 1) +".jpg");
            }

           boardRepository.save(board);
    }
    @Test
    @Transactional
    void readWithImage() {
        Long bno = 2L;
        Optional<Board> result = boardRepository.findById(bno);
        Board board1 = result.orElseThrow();

        log.info("board1 : {}",board1);
        log.info("-------image list------");
        log.info(board1.getImageSet());
    }

}