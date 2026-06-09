package com.human.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVo {

    private int page = 1;        // 현재 페이지
    private int size = 3;        // 페이지당 게시글 수
    private int totalCount;      // 전체 게시글 수
    private int offset;          // DB 조회 시작 위치
    private int totalPages;      // 전체 페이지 수
    private int startPage;       // 페이지 블록 시작 번호
    private int endPage;         // 페이지 블록 끝 번호

    private boolean prev;        // 이전 블록 존재 여부
    private boolean next;        // 다음 블록 존재 여부

    // OFFSET = (현재 페이지 - 1) * 페이지 크기
    public int getOffset() {
        offset = (page - 1) * size;
        return offset;
    }

    // 페이지 블록 계산 (블록 크기 5 고정)
    public void paginate() {
        int blockSize = 5;
        int pivot = (1 + blockSize) / 2;

        totalPages = (totalCount == 0) ? 0 : (int) Math.ceil((double) totalCount / size);

        if (totalPages == 0) {
            startPage = 1;
            endPage   = 1;
        } else {
            startPage = (page <= pivot) ? 1 : page - (pivot - 1);
            endPage   = startPage + blockSize - 1;

            if (endPage > totalPages) {
                endPage   = totalPages;
                startPage = Math.max(1, endPage - blockSize + 1);
            }
        }

        prev = startPage > 1;
        next = endPage < totalPages;
    }
}
