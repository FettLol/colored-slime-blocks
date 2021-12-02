package net.fettlol.coloredslime;

import net.fettlol.coloredslime.util.Helpers;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;

import java.util.Map;

import static net.fettlol.coloredslime.util.RegistryHelper.registerBlock;
import static net.fettlol.coloredslime.util.RegistryHelper.registerItem;

public class ColoredSlime implements ModInitializer {

    public static final String MOD_ID = "coloredslime";

    public static Identifier makeID(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static final Map<DyeColor, Block> slimeBlocks = Helpers.generateSlimeBlocks();
    public static final Map<DyeColor, Block> honeyBlocks = Helpers.generateHoneyBlocks();

    @Override
    public void onInitialize() {
        for (DyeColor color : DyeColor.values()) {

            // Register this color's slime block and item.
            String slimeName = color.name().toLowerCase() + "_slime_block";
            Block slimeBlock = slimeBlocks.get(color);
            registerBlock(slimeName, slimeBlock);
            registerItem(slimeName, slimeBlock);

            // Register this color's honey block and item.
            String honeyName = color.name().toLowerCase() + "_honey_block";
            Block honeyBlock = honeyBlocks.get(color);
            registerBlock(honeyName, honeyBlock);
            registerItem(honeyName, honeyBlock);
        }
    }
}
