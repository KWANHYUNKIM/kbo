package io.security.corespringsecurity.repository.kbo.crawl;

import io.security.corespringsecurity.domain.entity.kbo.crawl.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player,Long> {

    @Query("SELECT p FROM Player p " +
            "WHERE (:team = 'All' OR p.team = :team) " +
            "AND (:position = 'All' OR p.position = :position) " +
            "AND (:name = 'All' OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) ")
    List<Player> findByQuery(@Param("team") String team, @Param("position") String position, @Param("name") String name);

    default Player editByPlayer(Long playerId, Player updatedBoard){
        Optional<Player> existingPlayerOptional = findById(playerId);
        if(existingPlayerOptional.isPresent()){
            Player existingPlayer = existingPlayerOptional.get();

            existingPlayer.setDraftRank(updatedBoard.getDraftRank());
            existingPlayer.setSigningBonus(updatedBoard.getSigningBonus());
            existingPlayer.setPosition(updatedBoard.getPosition());
            existingPlayer.setFilename(updatedBoard.getFilename());
            existingPlayer.setFilepath(updatedBoard.getFilepath());
            existingPlayer.setJerseyNumber(updatedBoard.getJerseyNumber());
            existingPlayer.setDebutYear(updatedBoard.getDebutYear());
            existingPlayer.setWeight(updatedBoard.getWeight());
            existingPlayer.setCareerHistory(updatedBoard.getCareerHistory());
            existingPlayer.setHeight(updatedBoard.getHeight());
            existingPlayer.setAnnualSalary(updatedBoard.getAnnualSalary());
            existingPlayer.setBirthDate(updatedBoard.getBirthDate());
            existingPlayer.setDebutYear(updatedBoard.getDebutYear());

            return save(existingPlayer);
        }
        return null; // 해당 ID에 해당하는 게시물이 없을 경우
    }
}
