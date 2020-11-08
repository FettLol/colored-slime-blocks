package net.coloredslime.util;

import net.coloredslime.ColoredSlime;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ItemHelper {

    public static Item registerSlimeItem(String name, Block block) {
        return registerSlimeItem(makeID(name), new BlockItem(block, new Item.Settings().maxCount(64).group(ItemGroup.REDSTONE).rarity(Rarity.RARE)));
    }

    public static Item registerSlimeItem(Identifier id, Item item) {
        if (item != Items.AIR) {
            net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.ITEM, id, item);
        }
        return item;
    }

    public static Identifier makeID(String path) {
        return new Identifier(ColoredSlime.MOD_ID, path);
    }
}
