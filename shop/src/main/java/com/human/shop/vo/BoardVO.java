package com.human.shop.vo;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVO {
     // 게시글 번호
    private Long id;
    private String writer;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean deleted;   
    private List<BoardFileVO> flist;
}
