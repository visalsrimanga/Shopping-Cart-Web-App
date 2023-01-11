package lk.ilabs.inventory.service;

import lk.ilabs.inventory.dto.ItemDTO;

public interface ItemService {
    public ItemDTO addNewItem(ItemDTO item);

    ItemDTO getItem(Integer code);
}
