package net.fettlol.coloredslime.blocks;

import net.fettlol.coloredslime.util.Helpers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;

import static net.minecraft.world.level.block.Blocks.HONEY_BLOCK;

public class ColoredHoneyBlock extends HoneyBlock {
    public ColoredHoneyBlock(DyeColor color) {
        super(BlockBehaviour.Properties.ofFullCopy(HONEY_BLOCK)
            .mapColor(color)
            .noOcclusion()
            .setId(ResourceKey.create(Registries.BLOCK, Helpers.getColoredHoneyId(color))));
    }
}
