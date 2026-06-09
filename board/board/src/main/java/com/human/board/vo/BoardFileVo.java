package com.human.board.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardFileVo {
    private Long id;              // 파일 번호
    private Long boardId;         // 게시글 번호 (board.id 참조)
    private String originalName;  // 원본 파일명
    private String savedName;     // 저장 파일명 (UUID_원본명)
    private String filePath;      // 저장 경로
    private Long fileSize;        // 파일 크기 (byte)
    private LocalDateTime createdAt;  // 업로드 일시
    private int deleted;          // 활성 여부 (1: 활성, 0: 삭제)
}
