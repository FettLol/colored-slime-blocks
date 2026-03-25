package net.fettlol.coloredslime.util;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class RegistryHelper {
    public static Item registerItem(Identifier id, Item item) {
        Registry.register(BuiltInRegistries.ITEM, id, item);

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.REDSTONE_BLOCKS).register(entries -> entries.accept(item));

        return item;
    }

    public static Block registerBlock(Identifier id, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, id, block);
    }
}
