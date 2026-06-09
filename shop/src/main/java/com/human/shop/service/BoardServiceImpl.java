package com.human.shop.service;

import com.human.shop.controller.BoardController;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.human.shop.mapper.BoardFileMapper;
import com.human.shop.mapper.BoardMapper;
import com.human.shop.util.FileUploadUtil;
import com.human.shop.vo.BoardFileVO;
import com.human.shop.vo.BoardVO;
import com.human.shop.vo.PageVO;

import lombok.AllArgsConstructor;

//고객의 요청을 처리해 주는 게 역할
//메서드 네이밍시.. 고객의 요청의 냄새가 나게.. 
@Service
@AllArgsConstructor  //final로 정의된 변수에 주소 주입
public class BoardServiceImpl implements BoardService{
   
      // 컨트롤러 부터 요청을 받으면 mapper에게 디비작업 콜
   // boardmapper를 의존합니다.
   // 그래서 boardmapper객체의 주소가 필요
               //BoardMapper 타입의 객체의 주소를 
   //@Autowired  //컨테이너로 부터 주입해 주는 기능
    private final BoardMapper boardmapper;
    private final BoardFileMapper boardfilemapper;


 
  
   
    @Override
    public void inserBoard(BoardVO boardvo,MultipartFile[] files) {
       //아래 코드가 실행되면 게시글이  board테이블에 저장되고
       //저장되기 전에는 id값이 없지만, 저장된 후에는 id값이 생긴다.
        System.out.println("전 :"+boardvo.getId());
        boardmapper.insertBoard(boardvo);
        System.out.println("후 :"+boardvo.getId());
        Long boardId = boardvo.getId();
        String uploadPath = "c:/upload";  //파일을 저장할 위치
        List<BoardFileVO> fileList = FileUploadUtil.saveFiles(files, uploadPath);
        for(BoardFileVO f : fileList){
            f.setBoardId(boardId);  //첨부파일이 어떤 글의 파일인지 정보
            //데이터베이스에 저장
            boardfilemapper.insertFile(f);
        }
    }

    @Override
    public List<BoardVO> getBoardList(PageVO pagevo) {
        //컨트롤러로 부터 리스트달라고 서비스 요청받음
        //데이터베이스 책임이 없다.
        //mapper에게 다시 요청..
        List<BoardVO> boardlist = boardmapper.selectBoardList(pagevo);
        return boardlist;
    }

    @Override
    public void deleteBoard(Long id) {
        System.out.println("서비스단에서 삭제요청 처리");
        //컨트롤러요청 처리, 매퍼에게 요청, 컨트롤러에게 응답(리턴)
        //컨셉을 데이터베이스에 삭제하기 전에 암호를 입력했는가? 체크
        //수업에서는 바로 데이터베이스에 삭제하는 방법으로 진행

        // 매퍼에게 요청
        boardmapper.deleteById(id);
        // 매퍼요청까지 끝나면 .서비스에게 리턴해야 하나? 
        // 안해도 된다고 결정하자. 그래서 리턴타입  void
    }

    @Override
    public BoardVO detailBoard(Long id) {        
        BoardVO boardVO = boardmapper.getBoardById(id);
        //첨부파일도 가져와서 추가하자..
        List<BoardFileVO> flist = boardfilemapper.findByBoardId(id);
        // flist를 BoardVO에 추가하려고 해요.. 그런데 
        // BoardVO에 변수가 없습니다. 그래서 변수 추가를 합니다. 
        boardVO.setFlist(flist);
        return boardVO;  // 리턴시에는 첨부파일 정보까지 포함..
    }

    @Override
    public void updateBoard(BoardVO boardvo) {
        boardmapper.updateById(boardvo);
    }

    @Override
    public int getCount() {
        return boardmapper.getCount();
    }


}
