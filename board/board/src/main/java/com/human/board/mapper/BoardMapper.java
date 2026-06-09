package com.human.board.mapper;

import com.human.board.vo.BoardVo;
import com.human.board.vo.SearchVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<BoardVo> selectAll();                  // 전체 목록 조회
    List<BoardVo> selectBySearch(SearchVo vo);  // 검색 + 페이징 목록 조회
    int countBySearch(SearchVo vo);             // 검색 조건 총 게시글 수
    BoardVo selectOne(Long id);                 // 단건 조회
    int insert(BoardVo vo);                     // 등록
    int update(BoardVo vo);                     // 수정
    int delete(Long id);                        // soft delete
}
