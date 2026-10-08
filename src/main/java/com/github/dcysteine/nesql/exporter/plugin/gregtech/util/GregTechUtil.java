package com.github.dcysteine.nesql.exporter.plugin.gregtech.util;

import java.util.List;

import net.minecraft.item.ItemStack;

import gregtech.api.util.GTOreDictUnificator;

public class GregTechUtil {

    // Static class.
    private GregTechUtil() {}

    /** Returns a list of item stacks that unify into the provided item stack. */
    public static List<ItemStack> reverseUnify(ItemStack itemStack) {
        ItemStack unified = GTOreDictUnificator.get(itemStack);
        return GTOreDictUnificator.getNonUnifiedStacks(unified);
    }

    /** Returns a list of item stacks that unify into the provided object. */
    public static List<ItemStack> reverseUnify(Object object) {
        return GTOreDictUnificator.getNonUnifiedStacks(object);
    }
}
