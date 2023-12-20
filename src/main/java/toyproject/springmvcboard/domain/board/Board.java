package toyproject.springmvcboard.domain.board;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private int enabled;

    @Builder
    public Board(long id, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt, int enabled) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.enabled = enabled;
    }

    @Mapper(componentModel = "spring")
    public static interface BoardMapper {
        BoardDTO boardToBoardDTO(Board board);

        Board boardDTOToBoard(BoardDTO boardDTO);
    }
}
