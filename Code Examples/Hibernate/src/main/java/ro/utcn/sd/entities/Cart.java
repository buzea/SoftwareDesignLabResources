package ro.utcn.sd.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CART")
public class Cart {

    @Id
    // This is a small trick to make Hibernate 5 generate ID properly for MySql. Normally just @GeneratedValue is enough
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(name = "cart_id")
    private long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "cart_item",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id"))
    private Set<Item> items = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean add(Item item) {
        item.getCarts().add(this);
        return items.add(item);
    }

    public boolean remove(Item item) {
        item.getCarts().remove(this);
        return items.remove(item);
    }

    public Set<Item> getItems() {
        return items;
    }
}
