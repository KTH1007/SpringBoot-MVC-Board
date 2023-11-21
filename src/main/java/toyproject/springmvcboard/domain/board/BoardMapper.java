package toyproject.springmvcboard.domain.board;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    BoardMapper INSTANCE = Mappers.getMapper(BoardMapper.class);
    BoardDTO boardToBoardDTO(Board board);

    Board boardDTOToBoard(BoardDTO boardDTO);
}
