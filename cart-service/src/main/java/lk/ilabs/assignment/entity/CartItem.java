package lk.ilabs.assignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;
    private Integer itemCode;
    private int qty;

    public CartItem(String username, Integer itemCode, int qty) {
        this.username = username;
        this.itemCode = itemCode;
        this.qty = qty;
    }
}
