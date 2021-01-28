package net.brekitomasson.coloredslime.util;

import net.brekitomasson.coloredslime.ColoredSlime;
import net.minecraft.block.Block;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class ItemHelper {

    public static Item registerSlimeItem(String name, Block block) {
        return registerSlimeItem(makeID(name), new BlockItem(block,
            new Item.Settings().maxCount(64).group(ItemGroup.REDSTONE).rarity(Rarity.RARE)
        ));
    }

    public static Item registerSlimeItem(Identifier id, Item item) {
        if (item != Items.AIR) {
            Registry.register(Registry.ITEM, id, item);
        }

        return item;
    }

    public static Identifier makeID(String path) {
        return new Identifier(ColoredSlime.MOD_ID, path);
    }
}
