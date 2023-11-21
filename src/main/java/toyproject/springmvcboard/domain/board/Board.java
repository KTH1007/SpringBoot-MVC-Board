package toyproject.springmvcboard.domain.board;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

@Entity
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;

    @Builder
    public Board(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    @Mapper(componentModel = "spring")
    public static interface BoardMapper {
        BoardDTO boardToBoardDTO(Board board);

        Board boardDTOToBoard(BoardDTO boardDTO);
    }
}
