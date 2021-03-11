package net.brekitomasson.coloredslime;

import net.brekitomasson.coloredslime.blocks.ColoredSlimeBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

import static net.brekitomasson.coloredslime.util.RegistryHelper.registerSlimeBlock;
import static net.brekitomasson.coloredslime.util.RegistryHelper.registerSlimeItem;

public class ColoredSlime implements ModInitializer {

    public static final String MOD_ID = "coloredslime";

    public static Identifier makeID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final Map<DyeColor, Block> slimeBlocks;

    static {
        EnumMap<DyeColor, Block> temp = new EnumMap<>(DyeColor.class);

        for (DyeColor color : DyeColor.values()) {
            temp.put(color, new ColoredSlimeBlock(color));
        }

        slimeBlocks = Collections.unmodifiableMap(temp);
    }

    @Override
    public void onInitialize() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.name().toLowerCase() + "_slime_block";
            Block block = slimeBlocks.get(color);

            registerSlimeBlock(name, block);
            registerSlimeItem(name, block);
        }
    }
}
