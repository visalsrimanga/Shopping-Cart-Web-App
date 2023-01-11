package lk.ilabs.assignment.service.impl;

import lk.ilabs.assignment.dto.CartItemDTO;
import lk.ilabs.assignment.dto.ItemDTO;
import lk.ilabs.assignment.entity.CartItem;
import lk.ilabs.assignment.repository.CartItemRepository;
import lk.ilabs.assignment.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private CartItemRepository cartItemRepository;
    private RestTemplate restTemplate;

    public CartServiceImpl(CartItemRepository cartItemRepository, RestTemplate restTemplate) {
        this.cartItemRepository = cartItemRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public void addItemToCard(CartItemDTO cartItem) {
        ItemDTO itemInStock = restTemplate.getForObject("http://INVENTORY-SERVICE/api/v1/items/{code}", ItemDTO.class, cartItem.getItemCode());
        Optional<CartItem> optCartItem = cartItemRepository.findCartItemByItemCodeAndUsername(cartItem.getItemCode(), cartItem.getUsername());

        if (optCartItem.isEmpty()){
            if (itemInStock.getQty() < cartItem.getQty()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock!");
            cartItemRepository.save(new CartItem(cartItem.getUsername(), cartItem.getItemCode(), cartItem.getQty()));
        }else{
            CartItem cartItemEntity = optCartItem.get();
            if (itemInStock.getQty() < (cartItem.getQty() + cartItemEntity.getQty()))throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Insufficient stock!");
            cartItemEntity.setQty(cartItemEntity.getQty() + cartItem.getQty());
            cartItemRepository.save(cartItemEntity);
        }

    }

    @Override
    public void removeItemFromCard(Integer itemCode, String username) {
        CartItem cartItem = cartItemRepository.findCartItemByItemCodeAndUsername(itemCode, username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart item doesn't exist"));
        cartItemRepository.delete(cartItem);
    }
}
