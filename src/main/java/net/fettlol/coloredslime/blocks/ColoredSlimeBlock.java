package net.fettlol.coloredslime.blocks;

import net.fettlol.coloredslime.util.Helpers;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SlimeBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.DyeColor;

import static net.minecraft.block.Blocks.SLIME_BLOCK;

public class ColoredSlimeBlock extends SlimeBlock {
    public ColoredSlimeBlock(DyeColor color) {
        super(AbstractBlock.Settings.copy(SLIME_BLOCK)
            .mapColor(color)
            .nonOpaque()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Helpers.getColoredSlimeId(color))));
    }
}
