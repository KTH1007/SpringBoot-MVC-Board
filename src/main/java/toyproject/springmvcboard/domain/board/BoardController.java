package toyproject.springmvcboard.domain.board;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@Log4j2
@Validated
public class BoardController {

    private final BoardService boardService;

    @Autowired
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }
    //게시글 목록
    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDTO> boardDTOs = boardService.findAll();
        model.addAttribute("boards", boardDTOs);
        return "board/list";
    }
    //게시글 작성 페이지
    @GetMapping("/form")
    public String form() {
        return "board/form";
    }
    
    @PostMapping("/form")
    public String saveBoard(@Valid @ModelAttribute BoardDTO boardDTO, BindingResult bindingResult) {
        // 검증 실패시 다시 입력폼으로
        if (bindingResult.hasErrors()) {
            log.info("bindingResult = {}", bindingResult.toString());
            return "board/form";
        }
        boardService.save(boardDTO);
        return "redirect:/board/list";
    }

    //게시글 상세 페이지
    @GetMapping("/post")
    public String post(Model model, @RequestParam(required = false) Long id) {
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("boardDTO", boardDTO);
        return "board/post";
    }
}
