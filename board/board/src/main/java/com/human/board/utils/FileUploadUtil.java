package com.human.board.utils;

import com.human.board.vo.BoardFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUploadUtil {

    // 파일을 uploadPath에 저장하고 BoardFileVo 리스트 반환 (boardId 미포함, 서비스에서 세팅)
    public static List<BoardFileVo> saveFiles(MultipartFile[] files, String uploadPath) {
        List<BoardFileVo> fileList = new ArrayList<>();

        // 업로드 폴더 없으면 생성
        File folder = new File(uploadPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            try {
                String originalName = file.getOriginalFilename();
                // UUID로 파일명 중복 방지
                String savedName = UUID.randomUUID() + "_" + originalName;
                File saveFile = new File(uploadPath + File.separator + savedName);
                file.transferTo(saveFile);

                BoardFileVo fileVo = BoardFileVo.builder()
                        .originalName(originalName)
                        .savedName(savedName)
                        .filePath(uploadPath)
                        .fileSize(file.getSize())
                        .build();

                fileList.add(fileVo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileList;
    }
}
