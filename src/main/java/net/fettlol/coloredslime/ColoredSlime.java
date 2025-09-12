package net.fettlol.coloredslime;

import net.fabricmc.api.ModInitializer;
import net.fettlol.coloredslime.util.Helpers;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.Map;

import static net.fettlol.coloredslime.util.Helpers.getColoredHoneyId;
import static net.fettlol.coloredslime.util.Helpers.getColoredSlimeId;
import static net.fettlol.coloredslime.util.RegistryHelper.registerBlock;
import static net.fettlol.coloredslime.util.RegistryHelper.registerItem;

public class ColoredSlime implements ModInitializer {

    public static final String MOD_ID = "coloredslime";

    public static Identifier makeID(String path) {
        return Identifier.of(MOD_ID, path);
    }

    public static final Map<DyeColor, Block> SLIME_BLOCKS = Helpers.generateSlimeBlocks();
    public static final Map<DyeColor, Block> HONEY_BLOCKS = Helpers.generateHoneyBlocks();

    public static final Map<DyeColor, BlockItem> SLIME_BLOCK_ITEMS = Helpers.generateBlockItems(SLIME_BLOCKS, Helpers::getColoredSlimeId);
    public static final Map<DyeColor, BlockItem> HONEY_BLOCK_ITEMS = Helpers.generateBlockItems(HONEY_BLOCKS, Helpers::getColoredHoneyId);

    public static final TagKey<Block> SLIMES_BLOCK_TAG = TagKey.of(RegistryKeys.BLOCK, makeID("slimes"));
    public static final TagKey<Block> HONEYS_BLOCK_TAG = TagKey.of(RegistryKeys.BLOCK, makeID("honeys"));

    public static final TagKey<Item>  SLIMES_ITEM_TAG = TagKey.of(RegistryKeys.ITEM, makeID("slimes"));
    public static final TagKey<Item>  HONEYS_ITEM_TAG = TagKey.of(RegistryKeys.ITEM, makeID("honeys"));

    @Override
    public void onInitialize() {
        for (DyeColor color : DyeColor.values()) {
            // Register this color's slime block and item.
            Identifier slimeId = getColoredSlimeId(color);
            registerBlock(slimeId, SLIME_BLOCKS.get(color));
            registerItem(slimeId, SLIME_BLOCK_ITEMS.get(color));

            // Register this color's honey block and item.
            Identifier honeyId = getColoredHoneyId(color);
            registerBlock(honeyId, HONEY_BLOCKS.get(color));
            registerItem(honeyId, HONEY_BLOCK_ITEMS.get(color));
        }
    }
}
