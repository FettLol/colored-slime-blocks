package net.fettlol.coloredslime.blocks;

import net.fettlol.coloredslime.util.Helpers;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.SlimeBlock;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.DyeColor;

import static net.minecraft.block.Blocks.HONEY_BLOCK;

public class ColoredHoneyBlock extends SlimeBlock {
    public ColoredHoneyBlock(DyeColor color) {
        super(AbstractBlock.Settings.copy(HONEY_BLOCK)
            .mapColor(color)
            .nonOpaque()
            .registryKey(RegistryKey.of(RegistryKeys.BLOCK, Helpers.getColoredHoneyId(color))));
    }
}
