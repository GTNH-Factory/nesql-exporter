package com.github.dcysteine.nesql.exporter.plugin.quest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.github.dcysteine.nesql.exporter.plugin.base.factory.FluidFactory;
import com.github.dcysteine.nesql.exporter.plugin.base.factory.ItemGroupFactory;
import com.github.dcysteine.nesql.sql.base.fluid.FluidStack;
import com.github.dcysteine.nesql.sql.base.item.ItemGroup;

import betterquesting.api.utils.BigItemStack;

public class QuestUtil {

    // Static class.
    private QuestUtil() {}

    public static List<ItemGroup> buildItems(ItemGroupFactory itemGroupFactory, List<BigItemStack> items) {
        return items.stream()
            .map(bigItemStack -> itemGroupFactory.get(getItemStacks(bigItemStack), bigItemStack.stackSize))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    private static List<ItemStack> getItemStacks(BigItemStack bigItemStack) {
        List<ItemStack> itemStacks = new ArrayList<>();
        itemStacks.add(bigItemStack.getBaseStack());

        if (bigItemStack.hasOreDict()) {
            itemStacks.addAll(OreDictionary.getOres(bigItemStack.getOreDict(), false));
        }

        return itemStacks;
    }

    public static List<FluidStack> buildFluids(FluidFactory fluidFactory,
        List<net.minecraftforge.fluids.FluidStack> fluids) {
        return fluids.stream()
            .map(fluidStack -> new FluidStack(fluidFactory.get(fluidStack), fluidStack.amount))
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
