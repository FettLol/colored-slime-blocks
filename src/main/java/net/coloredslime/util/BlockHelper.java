package net.coloredslime.util;

import net.coloredslime.ColoredSlime;
import net.minecraft.block.Block;
import net.minecraft.util.Identifier;

public class BlockHelper {
    public static Block registerSlimeBlock(String name, Block block) {
        return registerSlimeBlock(makeID(name), block);
    }

    public static Block registerSlimeBlock(Identifier id, Block block) {
        net.minecraft.util.registry.Registry.register(net.minecraft.util.registry.Registry.BLOCK, id, block);
        return block;
    }

    public static Identifier makeID(String path) {
        return new Identifier(ColoredSlime.MOD_ID, path);
    }
}
