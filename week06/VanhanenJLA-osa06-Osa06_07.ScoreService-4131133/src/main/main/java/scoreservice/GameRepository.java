package scoreservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
    Game deleteByName(String name);
    Game findByName(String name);
}
