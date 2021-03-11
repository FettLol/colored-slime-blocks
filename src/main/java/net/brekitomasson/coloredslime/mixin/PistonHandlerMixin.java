package net.brekitomasson.coloredslime.mixin;

import net.brekitomasson.coloredslime.blocks.ColoredSlimeBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PistonHandler.class, priority = 450)
public abstract class PistonHandlerMixin {

    @Inject(method = "isBlockSticky", at = @At("HEAD"), cancellable = true)
    private static void isBlockSticky(Block block, CallbackInfoReturnable<Boolean> ci) {
        // Make the colored slime blocks "sticky".
        if (block instanceof ColoredSlimeBlock) {
            ci.setReturnValue(true);
        }
    }

    @Inject(method = "isAdjacentBlockStuck", at = @At("HEAD"), cancellable = true)
    private static void isAdjacentBlockStuck(Block block1, Block block2, CallbackInfoReturnable<Boolean> ci) {
        boolean block1IsColored = block1 instanceof ColoredSlimeBlock;

        if (block1IsColored) {
            // Colored slime blocks do not stick to slime or honey blocks
            if (block2 == Blocks.SLIME_BLOCK || block2 == Blocks.HONEY_BLOCK) {
                ci.setReturnValue(false);
            }
        }

        boolean block2IsColored = block2 instanceof ColoredSlimeBlock;

        if (block2IsColored) {
            // Slime or honey blocks do not stick to colored slime blocks
            if (block1 == Blocks.SLIME_BLOCK || block1 == Blocks.HONEY_BLOCK) {
                ci.setReturnValue(false);
            }
        }

        // Colored slime blocks do not stick to colored slime blocks of other colors
        if (block1 != block2 && block1IsColored && block2IsColored) {
            ci.setReturnValue(false);
        }
    }
}
