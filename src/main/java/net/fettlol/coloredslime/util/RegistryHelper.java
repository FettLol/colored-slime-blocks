package net.fettlol.coloredslime.util;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fettlol.coloredslime.ColoredSlime;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class RegistryHelper {
    public static Item registerItem(String name, Block block) {
        BlockItem item = Registry.register(Registries.ITEM, ColoredSlime.makeID(name), new BlockItem(block,
            new Item.Settings().maxCount(64)
        ));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> entries.add(item));

        return item;
    }

    public static Block registerBlock(String name, Block block) {
        return Registry.register(Registries.BLOCK, ColoredSlime.makeID(name), block);
    }
}
