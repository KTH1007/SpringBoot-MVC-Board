package toyproject.springmvcboard.mapper;

import org.mapstruct.Mapper;
import toyproject.springmvcboard.domain.Board;
import toyproject.springmvcboard.dto.BoardDTO;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    BoardDTO boardToBoardDTO(Board board);

    Board boardDTOToBoard(BoardDTO boardDTO);
}
