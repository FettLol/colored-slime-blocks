package net.fettlol.coloredslime.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SlimeBlock;
import net.minecraft.util.DyeColor;

import static net.minecraft.block.Blocks.SLIME_BLOCK;

public class ColoredSlimeBlock extends SlimeBlock {
    public ColoredSlimeBlock(DyeColor color) {
        super(AbstractBlock.Settings.copy(SLIME_BLOCK).mapColor(color).nonOpaque());
    }
}
