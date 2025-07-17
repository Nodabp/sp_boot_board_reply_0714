package com.sa98077.sp_boot_board_reply_0714.service;

import com.sa98077.sp_boot_board_reply_0714.domain.Reply;
import com.sa98077.sp_boot_board_reply_0714.dto.BoardDTO;
import com.sa98077.sp_boot_board_reply_0714.dto.PageRequestDTO;
import com.sa98077.sp_boot_board_reply_0714.dto.PageResponseDTO;
import com.sa98077.sp_boot_board_reply_0714.dto.ReplyDTO;
import com.sa98077.sp_boot_board_reply_0714.repository.ReplyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Log4j2
@Service
@RequiredArgsConstructor // 생성자를 자동생성~ 주입방식.
@Transactional

public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long register(ReplyDTO replyDTO) {
        log.info("replyDTO : {}", replyDTO);
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        return replyRepository.save(reply).getRno();
    }

    @Override
    public ReplyDTO read(Long rno) {
        Optional<Reply> replyOptional = replyRepository.findById(rno);
        return modelMapper.map(replyOptional.orElseThrow(), ReplyDTO.class);
    }

    @Override
    public void modify(ReplyDTO replyDTO) {

        Optional<Reply> replyOptional = replyRepository.findById(replyDTO.getRno());
        Reply reply = replyOptional.orElseThrow();
        reply.changeText(replyDTO.getReplyText());

        replyRepository.save(reply);

    }

    @Override
    public void remove(Long rno) {
        replyRepository.deleteById(rno);
    }

    @Override
    public PageResponseDTO<ReplyDTO> getList(Long bno, PageRequestDTO pageRequestDTO) {

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage() - 1, pageRequestDTO.getSize());

        Page<Reply> result = replyRepository.listOfBoard(bno,pageable);

        // 더 간략화
        List<ReplyDTO> dtoList = result
                .map(reply -> modelMapper.map(reply, ReplyDTO.class))
                .getContent();

        // 3. PageResponseDTO 생성
        PageResponseDTO<ReplyDTO> pageResponseDTO = PageResponseDTO.<ReplyDTO>withAll()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(dtoList)
                .total((int) result.getTotalElements()) // 전체 개수 기준으로 총 페이지 계산
                .build();

        return pageResponseDTO;
    }
}
