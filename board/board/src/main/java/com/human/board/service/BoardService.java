package com.human.board.service;

import com.human.board.vo.BoardVo;
import com.human.board.vo.SearchVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardService {

    List<BoardVo> getList(SearchVo searchVo); // 검색 + 페이징 목록 조회
    BoardVo getOne(Long id);                  // 단건 조회
    void write(BoardVo vo, List<MultipartFile> files);  // 게시글 등록
    void modify(BoardVo vo, List<MultipartFile> files); // 게시글 수정
    void remove(Long id);                     // 게시글 삭제
}
