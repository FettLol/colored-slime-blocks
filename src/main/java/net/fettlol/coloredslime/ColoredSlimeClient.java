package net.fettlol.coloredslime;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.BlockRenderLayer;

public class ColoredSlimeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlocks(
            BlockRenderLayer.TRANSLUCENT,
            ColoredSlime.SLIME_BLOCKS.values().toArray(new Block[0])
        );

        BlockRenderLayerMap.putBlocks(
            BlockRenderLayer.TRANSLUCENT,
            ColoredSlime.HONEY_BLOCKS.values().toArray(new Block[0])
        );
    }
}
