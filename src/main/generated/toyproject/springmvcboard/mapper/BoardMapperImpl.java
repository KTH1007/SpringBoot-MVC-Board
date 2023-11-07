package toyproject.springmvcboard.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import toyproject.springmvcboard.domain.Board;
import toyproject.springmvcboard.dto.BoardDTO;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-03T16:24:05+0900",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.5 (Azul Systems, Inc.)"
)
@Component
public class BoardMapperImpl implements BoardMapper {

    @Override
    public BoardDTO boardToBoardDTO(Board board) {
        if ( board == null ) {
            return null;
        }

        BoardDTO.BoardDTOBuilder boardDTO = BoardDTO.builder();

        boardDTO.id( board.getId() );
        boardDTO.title( board.getTitle() );
        boardDTO.content( board.getContent() );

        return boardDTO.build();
    }

    @Override
    public Board boardDTOToBoard(BoardDTO boardDTO) {
        if ( boardDTO == null ) {
            return null;
        }

        Board.BoardBuilder board = Board.builder();

        board.id( boardDTO.getId() );
        board.title( boardDTO.getTitle() );
        board.content( boardDTO.getContent() );

        return board.build();
    }
}
