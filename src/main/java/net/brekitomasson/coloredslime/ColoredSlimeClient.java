package net.brekitomasson.coloredslime;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class ColoredSlimeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlocks(
            RenderLayer.getTranslucent(),
            ColoredSlime.slimes.toArray(new Block[0])
        );
    }
}
