package toyproject.springmvcboard.domain.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {
    private long id;
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String content;

    @Builder
    public BoardDTO(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

}
