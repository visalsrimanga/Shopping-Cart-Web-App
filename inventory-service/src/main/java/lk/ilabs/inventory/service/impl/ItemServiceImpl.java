package lk.ilabs.inventory.service.impl;

import lk.ilabs.inventory.dto.ItemDTO;
import lk.ilabs.inventory.entity.Item;
import lk.ilabs.inventory.repository.ItemRepository;
import lk.ilabs.inventory.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ItemServiceImpl implements ItemService {

    private ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDTO addNewItem(ItemDTO item) {
        Item entity = this.itemRepository.save(new Item( item.getDescription(), item.getQty()));
        return new ItemDTO(entity.getCode(), entity.getDescription(), entity.getQty());
    }

    @Override
    public ItemDTO getItem(Integer code) {
        return this.itemRepository.findById(code).map(entity -> new ItemDTO(entity.getCode(), entity.getDescription(), entity.getQty()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
