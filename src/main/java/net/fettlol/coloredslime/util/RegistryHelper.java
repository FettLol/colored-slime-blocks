package net.fettlol.coloredslime.util;

import net.fettlol.coloredslime.ColoredSlime;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.registry.Registry;

public class RegistryHelper {
    public static Item registerItem(String name, Block block) {
        return Registry.register(Registry.ITEM, ColoredSlime.makeID(name), new BlockItem(block,
            new Item.Settings().maxCount(64).group(ItemGroup.REDSTONE)
        ));
    }

    public static Block registerBlock(String name, Block block) {
        return Registry.register(Registry.BLOCK, ColoredSlime.makeID(name), block);
    }
}
