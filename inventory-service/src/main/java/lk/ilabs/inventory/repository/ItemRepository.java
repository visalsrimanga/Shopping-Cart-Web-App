package lk.ilabs.inventory.repository;

import lk.ilabs.inventory.entity.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Integer> {
}
