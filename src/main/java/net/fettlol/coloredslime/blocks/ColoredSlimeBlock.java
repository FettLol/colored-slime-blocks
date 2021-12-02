package net.fettlol.coloredslime.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.SlimeBlock;
import net.minecraft.util.DyeColor;

import static net.minecraft.block.Blocks.SLIME_BLOCK;

public class ColoredSlimeBlock extends SlimeBlock {
    public ColoredSlimeBlock(DyeColor color) {
        super(FabricBlockSettings.copyOf(SLIME_BLOCK).mapColor(color).nonOpaque());
    }
}
