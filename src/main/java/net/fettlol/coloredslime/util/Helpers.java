package net.fettlol.coloredslime.util;

import net.fettlol.coloredslime.ColoredSlime;
import net.fettlol.coloredslime.blocks.ColoredHoneyBlock;
import net.fettlol.coloredslime.blocks.ColoredSlimeBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Function;

public class Helpers {

    public static boolean isColoredSlime(BlockState blockState) {
        return isColoredSlime(blockState.getBlock());
    }

    public static boolean isColoredSlime(Block block) {
        return block instanceof ColoredSlimeBlock;
    }

    public static boolean isColoredHoney(BlockState blockState) {
        return isColoredHoney(blockState.getBlock());
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

    public static Map<DyeColor, BlockItem> generateBlockItems(Map<DyeColor, Block> blocks, Function<DyeColor, Identifier> getId) {
        EnumMap<DyeColor, BlockItem> blockItems = new EnumMap<>(DyeColor.class);

        for (DyeColor color : DyeColor.values()) {
            blockItems.put(color, new BlockItem(blocks.get(color),
                new Item.Properties()
                    .stacksTo(64)
                    .setId(ResourceKey.create(Registries.ITEM, getId.apply(color)))
                    .useBlockDescriptionPrefix()
            ));
        }

        return Collections.unmodifiableMap(blockItems);
    }

    public static Identifier getColoredSlimeId(DyeColor color) {
        return ColoredSlime.makeID(color.getName() + "_slime_block");
    }

    public static Identifier getColoredHoneyId(DyeColor color) {
        return ColoredSlime.makeID(color.getName() + "_honey_block");
    }

    public static Identifier getDyeId(DyeColor color) {
        return Identifier.fromNamespaceAndPath("minecraft", color.getName() + "_dye");
    }

}
