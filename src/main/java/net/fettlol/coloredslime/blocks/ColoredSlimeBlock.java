package net.fettlol.coloredslime.blocks;

import net.fettlol.coloredslime.util.Helpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.SlimeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.level.block.Blocks.SLIME_BLOCK;

public class ColoredSlimeBlock extends SlimeBlock {
    public ColoredSlimeBlock(DyeColor color) {
        super(BlockBehaviour.Properties.ofFullCopy(SLIME_BLOCK)
            .mapColor(color)
            .noOcclusion()
            .setId(ResourceKey.create(Registries.BLOCK, Helpers.getColoredSlimeId(color))));
    }
}
