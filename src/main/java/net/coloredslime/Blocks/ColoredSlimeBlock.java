package net.coloredslime.blocks;

import net.coloredslime.util.BlockHelper;
import net.coloredslime.util.ItemHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.SlimeBlock;
import net.minecraft.item.Item;
import net.minecraft.util.DyeColor;

import static net.minecraft.block.Blocks.SLIME_BLOCK;

public class ColoredSlimeBlock extends SlimeBlock {
    public final Block block;
    public final Item item;

    public ColoredSlimeBlock(String name, DyeColor color) {
        super(FabricBlockSettings.copyOf(SLIME_BLOCK).materialColor(color).nonOpaque());

        block = BlockHelper.registerSlimeBlock(name, this);
        item = ItemHelper.registerSlimeItem(name, this);
    }
}
