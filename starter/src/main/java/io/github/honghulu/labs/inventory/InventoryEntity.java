package io.github.honghulu.labs.inventory;

import jakarta.persistence.*;

@Entity
@Table(name="inventory")
public class InventoryEntity {
    @Id private Long skuId;
    @Column(nullable=false) private Long available;
    @Version private Long version;
    protected InventoryEntity() {}
    public Long getSkuId(){return skuId;} public Long getAvailable(){return available;} public void setAvailable(Long available){this.available=available;}
}
