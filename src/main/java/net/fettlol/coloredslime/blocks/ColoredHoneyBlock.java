package net.fettlol.coloredslime.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SlimeBlock;
import net.minecraft.util.DyeColor;

import static net.minecraft.block.Blocks.HONEY_BLOCK;

public class ColoredHoneyBlock extends SlimeBlock {
    public ColoredHoneyBlock(DyeColor color) {
        super(AbstractBlock.Settings.copy(HONEY_BLOCK).mapColor(color).nonOpaque());
    }
}
