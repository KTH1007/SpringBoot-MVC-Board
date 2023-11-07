package toyproject.springmvcboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDTO {
    private long id;
    private String title;
    private String content;

    @Builder
    public BoardDTO(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

}
