package com.human.shop.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageVO {
    private int page=1;   // 클라이언트가 요청한 페이지
    private int size=10;  // 가져올 글의 수
    private int totalCount; // 게시판의 전체글의 수

    private int offset;  // 가져올 글의 시작위치

    private int totalPage; // 전체페이지 수
    private int pageBlock=10; // 하나의 블록에 출력할 페이지 수
    //페이지 그룹의 시작과 끝 변수
    private int startPage;
    private int endPage;

    //이전과 다음 여부
    private boolean prev;
    private boolean next;

    public int getOffset(){
        offset = (page-1)*size;
        return offset;
    }

    public void pageInfo(int totalCount){
        this.totalCount=totalCount;
        this.totalPage = (int)Math.ceil((double)totalCount/size);
        this.endPage = (int)Math.ceil((double)page/pageBlock) * pageBlock;
        this.startPage = endPage - pageBlock + 1;

        //현재 125건이 있다.
        //그러면 페이지가 총 13개 입니다.
        //이것을 블록으로 표시한다면 다음과 같다.
        // 1 2 3 4 5 6 7 8 9 10 다음
        // 이전 11 12 13 
        // 만약 페이지가 12라고 가정한다면 위에서 계산한 enaPage는 20이된다
        // 14번 부터는 없는데.. 문제가있다. 보정하는 코드
        if(endPage > totalPage){
            endPage = totalPage;
        }
        this.prev = startPage > 1;
        this.next = endPage< totalPage;
    }
}
