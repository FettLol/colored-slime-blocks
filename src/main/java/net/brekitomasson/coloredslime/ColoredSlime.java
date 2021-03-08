package net.brekitomasson.coloredslime;

import net.brekitomasson.coloredslime.blocks.ColoredSlimeBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

import static net.brekitomasson.coloredslime.util.RegistryHelper.registerSlimeBlock;
import static net.brekitomasson.coloredslime.util.RegistryHelper.registerSlimeItem;

public class ColoredSlime implements ModInitializer {

    public static final String MOD_ID = "coloredslime";

    public static Identifier makeID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final ArrayList<Block> slimes = new ArrayList<>(DyeColor.values().length);

    @Override
    public void onInitialize() {
        for (DyeColor color : DyeColor.values()) {
            ColoredSlimeBlock block = new ColoredSlimeBlock(color);
            slimes.add(block);

            String name = color.name().toLowerCase() + "_slime_block";
            registerSlimeBlock(name, block);
            registerSlimeItem(name, block);
        }
    }
}
