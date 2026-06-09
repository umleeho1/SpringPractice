package com.human.board.service;

import com.human.board.mapper.BoardFileMapper;
import com.human.board.utils.FileUploadUtil;
import com.human.board.vo.BoardFileVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFileServiceImpl implements BoardFileService {

    private final BoardFileMapper boardFileMapper;

    @Value("${upload.path}")
    private String UPLOAD_PATH;

    // 게시글 번호로 파일 목록 조회
    @Override
    public List<BoardFileVo> getFileList(Long boardId) {
        return boardFileMapper.selectByBoardId(boardId);
    }

    // 파일 단건 조회
    @Override
    public BoardFileVo getFile(Long id) {
        return boardFileMapper.selectOne(id);
    }

    // 파일 저장 
    @Override
    public void saveFiles(Long boardId, List<MultipartFile> files) {
        MultipartFile[] fileArr = files.toArray(new MultipartFile[0]);
        List<BoardFileVo> fileList = FileUploadUtil.saveFiles(fileArr, UPLOAD_PATH);

        for (BoardFileVo fileVo : fileList) {
            fileVo.setBoardId(boardId);
            boardFileMapper.insert(fileVo);
        }
    }

    // 파일 단건 삭제 
    @Override
    public void removeFile(Long id) {
        BoardFileVo file = boardFileMapper.selectOne(id);
        if (file != null) {
            new File(file.getFilePath() + File.separator + file.getSavedName()).delete();
            boardFileMapper.delete(id);
        }
    }

    // 게시글 전체 파일 삭제 
    @Override
    public void removeFilesByBoardId(Long boardId) {
        List<BoardFileVo> files = boardFileMapper.selectByBoardId(boardId);
        for (BoardFileVo file : files) {
            new File(file.getFilePath() + File.separator + file.getSavedName()).delete();
        }
        boardFileMapper.deleteByBoardId(boardId);
    }
}
