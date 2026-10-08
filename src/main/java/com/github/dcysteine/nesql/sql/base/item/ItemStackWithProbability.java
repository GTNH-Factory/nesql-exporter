package com.github.dcysteine.nesql.sql.base.item;

import java.util.Comparator;

import org.jetbrains.annotations.NotNull;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * A container class consisting of an {@link Item}, a stack size, and a probability.
 * Used for defining recipe outputs.
 */
@Embeddable
@EqualsAndHashCode
@Getter
@ToString
public class ItemStackWithProbability implements Comparable<ItemStackWithProbability> {

    @ManyToOne
    private Item item;

    private int stackSize;

    private double probability;

    /** Needed by Hibernate. */
    protected ItemStackWithProbability() {}

    public ItemStackWithProbability(Item item, int stackSize, double probability) {
        this.item = item;
        this.stackSize = stackSize;
        this.probability = probability;
    }

    public ItemStack withoutProbability() {
        return new ItemStack(item, stackSize);
    }

    @Override
    public int compareTo(@NotNull ItemStackWithProbability other) {
        return Comparator.comparing(ItemStackWithProbability::getItem)
            .thenComparing(ItemStackWithProbability::getStackSize)
            .thenComparing(ItemStackWithProbability::getProbability)
            .compare(this, other);
    }
}
