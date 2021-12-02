package net.fettlol.coloredslime.mixin;

import net.fettlol.coloredslime.blocks.ColoredSlimeBlock;
import net.fettlol.coloredslime.util.Helpers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PistonHandler.class, priority = 450)
public abstract class PistonHandlerMixin {

    @Inject(method = "isBlockSticky", at = @At("HEAD"), cancellable = true)
    private static void isBlockSticky(BlockState state, CallbackInfoReturnable cir) {
        if (state.getBlock() instanceof ColoredSlimeBlock) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "isAdjacentBlockStuck", at = @At("HEAD"), cancellable = true)
    private static void isAdjacentBlockStuck(BlockState blockState1, BlockState blockState2, CallbackInfoReturnable<Boolean> ci) {
        Block block1 = blockState1.getBlock();
        Block block2 = blockState2.getBlock();

        if (Helpers.isColoredSlime(block1)) {
            // Colored slime blocks do not stick to slime or honey blocks
            if (block2 == Blocks.SLIME_BLOCK || block2 == Blocks.HONEY_BLOCK) {
                ci.setReturnValue(false);
            }
        }

        if (Helpers.isColoredSlime(block2)) {
            // Slime or honey blocks do not stick to colored slime blocks
            if (block1 == Blocks.SLIME_BLOCK || block1 == Blocks.HONEY_BLOCK) {
                ci.setReturnValue(false);
            }
        }

        // Colored slime blocks do not stick to colored slime blocks of other colors
        if (block1 != block2 && Helpers.isColoredSlime(block1) && Helpers.isColoredSlime(block2)) {
            ci.setReturnValue(false);
        }
    }

}
