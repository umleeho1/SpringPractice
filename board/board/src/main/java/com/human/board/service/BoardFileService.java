package com.human.board.service;

import com.human.board.vo.BoardFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardFileService {

    List<BoardFileVo> getFileList(Long boardId); // 게시글 번호로 파일 목록 조회
    BoardFileVo getFile(Long id);                // 파일 단건 조회
    void saveFiles(Long boardId, List<MultipartFile> files); // 파일 저장 (디스크 + DB)
    void removeFile(Long id);                    // 파일 단건 삭제
    void removeFilesByBoardId(Long boardId);     // 게시글 전체 파일 삭제
}
