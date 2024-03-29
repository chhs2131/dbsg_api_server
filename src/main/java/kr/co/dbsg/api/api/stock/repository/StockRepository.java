package kr.co.dbsg.api.api.stock.repository;

import java.util.Optional;
import kr.co.dbsg.api.api.stock.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends JpaRepository<StockEntity, Long> {

    Optional<StockEntity> findByCorpName(String corpName);
}
