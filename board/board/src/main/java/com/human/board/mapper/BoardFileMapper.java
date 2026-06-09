package com.human.board.mapper;

import com.human.board.vo.BoardFileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardFileMapper {
    List<BoardFileVo> selectByBoardId(Long boardId); // 게시글 번호로 파일 목록 조회
    BoardFileVo selectOne(Long id);                  // 파일 단건 조회
    int insert(BoardFileVo vo);                      // 파일 정보 등록
    int delete(Long id);                             // 파일 단건 soft delete
    int deleteByBoardId(Long boardId);               // 게시글 파일 전체 soft delete
}
