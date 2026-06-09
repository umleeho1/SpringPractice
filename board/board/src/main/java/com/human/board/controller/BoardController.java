package com.human.board.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

import com.human.board.service.BoardService;
import com.human.board.vo.BoardVo;
import com.human.board.vo.SearchVo;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService service;

    @Value("${upload.path}")
    private String uploadPath;

    // 게시판 리스트 화면
    @GetMapping("/boards")
    public String showBoardList(@RequestParam(defaultValue = "") String keyword,
                                @RequestParam(defaultValue = "1") int page,
                                Model model) {
        SearchVo searchVo = new SearchVo();
        searchVo.setKeyword(keyword);
        searchVo.setPage(page);

        model.addAttribute("boards", service.getList(searchVo));
        model.addAttribute("search", searchVo);
        model.addAttribute("keyword", keyword);
        return "board/list";
    }

    // 게시글 작성 화면
    @GetMapping("/boards/write")
    public String writeForm() {
        return "board/write";
    }

    // 게시글 작성 처리
    @PostMapping("/boards/write")
    public String write(BoardVo vo, @RequestParam List<MultipartFile> files) {
        service.write(vo, files);
        return "redirect:/boards";
    }

    // 게시글 자세히보기
    @GetMapping("/boards/{id}")
    public String detail(@PathVariable Long id, Model model) {
        BoardVo board = service.getOne(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    // 게시글 수정 화면
    @GetMapping("/boards/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        BoardVo board = service.getOne(id);
        model.addAttribute("board", board);
        return "board/edit";
    }

    // 게시글 수정 처리
    @PostMapping("/boards/{id}/edit")
    public String edit(@PathVariable Long id, BoardVo vo, @RequestParam List<MultipartFile> files) {
        vo.setId(id);
        service.modify(vo, files);
        return "redirect:/boards/" + id;
    }

    // 게시글 삭제 처리
    @PostMapping("/boards/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.remove(id);
        return "redirect:/boards";
    }

    // 파일 다운로드
    @ResponseBody
    @GetMapping("/download/{filename}")
    public ResponseEntity<Resource> download(@PathVariable String filename) {
        File file = new File(uploadPath, filename);

        if (!file.exists() || !file.isFile()) {
            return ResponseEntity.notFound().build();
        }

        Resource resource = new FileSystemResource(file);

        // UUID_원본파일명 형식에서 원본 파일명 추출
        String originalName = filename.substring(filename.indexOf("_") + 1);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + originalName + "\"")
                .body(resource);
    }
}
