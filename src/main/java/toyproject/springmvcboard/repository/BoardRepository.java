package toyproject.springmvcboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toyproject.springmvcboard.model.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
