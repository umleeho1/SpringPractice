package com.human.board.service;

import com.human.board.mapper.BoardMapper;
import com.human.board.vo.BoardVo;
import com.human.board.vo.SearchVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;
    private final BoardFileService boardFileService;

    // 검색 조건으로 총 게시글 수 조회 후 페이징 계산, 목록 반환
    @Override
    public List<BoardVo> getList(SearchVo searchVo) {
        int totalCount = boardMapper.countBySearch(searchVo);
        searchVo.setTotalCount(totalCount);
        searchVo.paginate();
        return boardMapper.selectBySearch(searchVo);
    }

    // 게시글 단건 조회
    @Override
    public BoardVo getOne(Long id) {
        return boardMapper.selectOne(id);
    }

    // 게시글 등록 후 첨부파일 저장
    @Override
    public void write(BoardVo vo, List<MultipartFile> files) {
        boardMapper.insert(vo);
        if (files != null && !files.isEmpty()) {
            boardFileService.saveFiles(vo.getId(), files);
        }
    }

    // 게시글 수정 후 첨부파일 추가 저장
    @Override
    public void modify(BoardVo vo, List<MultipartFile> files) {
        boardMapper.update(vo);
        if (files != null && !files.isEmpty()) {
            boardFileService.saveFiles(vo.getId(), files);
        }
    }

    // 게시글 삭제(본인게시글)
    @Override
    public void remove(Long id) {
        boardFileService.removeFilesByBoardId(id);
        boardMapper.delete(id);
    }
}
