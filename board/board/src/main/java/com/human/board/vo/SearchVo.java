package com.human.board.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchVo extends PageVo {

    private String searchType = "all"; // 검색 타입: title(제목), writer(작성자), all(전체)
    private String keyword = "";       // 검색 키워드
}
