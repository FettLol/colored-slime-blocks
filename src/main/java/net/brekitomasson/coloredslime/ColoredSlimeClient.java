package net.brekitomasson.coloredslime;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

public class ColoredSlimeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(
                RenderLayer.getTranslucent(),
                ColoredSlime.BLACK_SLIME_BLOCK,
                ColoredSlime.BLUE_SLIME_BLOCK,
                ColoredSlime.BROWN_SLIME_BLOCK,
                ColoredSlime.CYAN_SLIME_BLOCK,
                ColoredSlime.GRAY_SLIME_BLOCK,
                ColoredSlime.GREEN_SLIME_BLOCK,
                ColoredSlime.LIGHT_BLUE_SLIME_BLOCK,
                ColoredSlime.LIGHT_GRAY_SLIME_BLOCK,
                ColoredSlime.LIME_SLIME_BLOCK,
                ColoredSlime.MAGENTA_SLIME_BLOCK,
                ColoredSlime.ORANGE_SLIME_BLOCK,
                ColoredSlime.PINK_SLIME_BLOCK,
                ColoredSlime.PURPLE_SLIME_BLOCK,
                ColoredSlime.RED_SLIME_BLOCK,
                ColoredSlime.WHITE_SLIME_BLOCK,
                ColoredSlime.YELLOW_SLIME_BLOCK
            );
    }
}
