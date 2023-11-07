package toyproject.springmvcboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import toyproject.springmvcboard.domain.Board;
import toyproject.springmvcboard.dto.BoardDTO;
import toyproject.springmvcboard.mapper.BoardMapper;
import toyproject.springmvcboard.repository.BoardRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardMapper boardMapper;

    @Autowired
    public BoardService(BoardRepository boardRepository, BoardMapper boardMapper) {
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    public List<BoardDTO> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream()
                .map(board -> boardMapper.boardToBoardDTO(board))
                .collect(Collectors.toList());
    }


}
