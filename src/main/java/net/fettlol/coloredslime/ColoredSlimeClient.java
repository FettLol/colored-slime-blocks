package net.fettlol.coloredslime;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.world.level.block.Block;

public class ColoredSlimeClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.putBlocks(
            ChunkSectionLayer.TRANSLUCENT,
            ColoredSlime.SLIME_BLOCKS.values().toArray(new Block[0])
        );

        BlockRenderLayerMap.putBlocks(
            ChunkSectionLayer.TRANSLUCENT,
            ColoredSlime.HONEY_BLOCKS.values().toArray(new Block[0])
        );
    }
}
