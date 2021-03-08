package net.brekitomasson.coloredslime.util;

import net.brekitomasson.coloredslime.ColoredSlime;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class RegistryHelper {
    public static Item registerSlimeItem(String name, Block block) {
        return Registry.register(Registry.ITEM, ColoredSlime.makeID(name), new BlockItem(block,
            new Item.Settings().maxCount(64).group(ItemGroup.REDSTONE).rarity(Rarity.RARE)
        ));
    }

    public static Block registerSlimeBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, ColoredSlime.makeID(name), block);
    }
}
