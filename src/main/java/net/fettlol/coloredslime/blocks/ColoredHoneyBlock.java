package net.fettlol.coloredslime.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.SlimeBlock;
import net.minecraft.util.DyeColor;

import static net.minecraft.block.Blocks.HONEY_BLOCK;

public class ColoredHoneyBlock extends SlimeBlock {
    public ColoredHoneyBlock(DyeColor color) {
        super(FabricBlockSettings.copyOf(HONEY_BLOCK).mapColor(color).nonOpaque());
    }
}
