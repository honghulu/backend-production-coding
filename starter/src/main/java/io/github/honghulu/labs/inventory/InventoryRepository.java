package io.github.honghulu.labs.inventory;
import org.springframework.data.jpa.repository.JpaRepository;
public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {}
