package lk.ilabs.assignment.repository;

import lk.ilabs.assignment.entity.CartItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CartItemRepository extends CrudRepository<CartItem, String> {
    Optional<CartItem> findCartItemByItemCodeAndUsername(Integer code, String username);
}
