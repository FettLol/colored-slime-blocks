package net.fettlol.coloredslime.util;

import net.fettlol.coloredslime.blocks.ColoredHoneyBlock;
import net.fettlol.coloredslime.blocks.ColoredSlimeBlock;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class Helpers {

    public static boolean isColoredSlime(Block block) {
        return block instanceof ColoredSlimeBlock;
    }

    public static boolean isColoredHoney(Block block) {
        return block instanceof ColoredHoneyBlock;
    }

    public static Map<DyeColor, Block> generateSlimeBlocks() {
        EnumMap<DyeColor, Block> slimeBlocks = new EnumMap<>(DyeColor.class);

        for (DyeColor color : DyeColor.values()) {
            slimeBlocks.put(color, new ColoredSlimeBlock(color));
        }

        return Collections.unmodifiableMap(slimeBlocks);
    }

    public static Map<DyeColor, Block> generateHoneyBlocks() {
        EnumMap<DyeColor, Block> honeyBlocks = new EnumMap<>(DyeColor.class);

        for (DyeColor color : DyeColor.values()) {
            honeyBlocks.put(color, new ColoredHoneyBlock(color));
        }

        return Collections.unmodifiableMap(honeyBlocks);
    }

}
