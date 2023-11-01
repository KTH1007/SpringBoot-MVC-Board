package toyproject.springmvcboard.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import toyproject.springmvcboard.model.Board;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class BoardRepositoryTest {
    @Autowired
    private BoardRepository boardRepository;

    @BeforeEach
    void beforeEach() {
        boardRepository.deleteAll();
    }

    @AfterEach
    void afterEach() {
        boardRepository.deleteAll();
    }

    @Test
    void 게시글_저장확인() {
        //given
        String title = "제목";
        String content = "내용";

        boardRepository.save(Board.builder()
                        .title(title)
                        .content(content)
                        .build());

        //when
        List<Board> boardList = boardRepository.findAll();

        //then
        Board board = boardList.get(0);
        assertThat(board.getTitle()).isEqualTo(title);
        assertThat(board.getContent()).isEqualTo(content);

        
    }

}
