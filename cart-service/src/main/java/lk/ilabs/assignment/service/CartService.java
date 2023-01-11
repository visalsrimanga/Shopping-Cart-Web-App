package lk.ilabs.assignment.service;

import lk.ilabs.assignment.dto.CartItemDTO;

public interface CartService {
    public void addItemToCard(CartItemDTO cartItem);
    public void removeItemFromCard(Integer itemCode, String username);
}
