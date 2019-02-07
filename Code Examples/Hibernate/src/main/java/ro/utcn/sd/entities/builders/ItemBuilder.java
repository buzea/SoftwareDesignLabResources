/*************************************************************************
 * ULLINK CONFIDENTIAL INFORMATION
 * _______________________________
 *
 * All Rights Reserved.
 *
 * NOTICE: This file and its content are the property of Ullink. The
 * information included has been classified as Confidential and may
 * not be copied, modified, distributed, or otherwise disseminated, in
 * whole or part, without the express written permission of Ullink.
 ************************************************************************/
package ro.utcn.sd.entities.builders;

import ro.utcn.sd.entities.Item;

import java.util.Objects;

/**
 * Builder Pattern Example
 */
public class ItemBuilder {

    private Item underConstruction;

    private ItemBuilder() {
        underConstruction = new Item();
    }

    public static ItemBuilder createItemBuilder() {
        return new ItemBuilder();
    }

    public ItemBuilder name(String name) {
        underConstruction.setName(name);
        return this;
    }

    public ItemBuilder price(double price) {
        underConstruction.setPrice(price);
        return this;
    }

    public Item build() {
        checkValid(underConstruction);
        return underConstruction;
    }

    private void checkValid(Item underConstruction) {
        Objects.requireNonNull(underConstruction.getName());
        Objects.requireNonNull(underConstruction.getPrice());
    }
}
