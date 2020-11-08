package net.coloredslime.mixin;

import net.coloredslime.ColoredSlime;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = PistonHandler.class, priority = 450)
public abstract class PistonHandlerMixin {
    @Inject(method = { "isBlockSticky" }, at = { @At("HEAD") }, cancellable = true)
    private static void isBlockSticky(Block block, CallbackInfoReturnable<Boolean> ci) {
        // Make the colored slime blocks "sticky".
        if (ColoredSlime.isColoredSlimeBlock(block)) {
            ci.setReturnValue(true);
        }
    }

    @Inject(method = { "isAdjacentBlockStuck" }, at = { @At("HEAD") }, cancellable = true)
    private static void isAdjacentBlockStuck(Block block, Block block2, CallbackInfoReturnable<Boolean> ci) {
        // Slimes do not connect to colored blocks.
        if (block == Blocks.SLIME_BLOCK && ColoredSlime.isColoredSlimeBlock(block2)) {
            ci.setReturnValue(false);
        }

        // Colored blocks do not connect to slimes.
        if (ColoredSlime.isColoredSlimeBlock(block) && block2 == Blocks.SLIME_BLOCK) {
            ci.setReturnValue(false);
        }

        // Honey does not connect to colored blocks.
        if (block == Blocks.HONEY_BLOCK && ColoredSlime.isColoredSlimeBlock(block2)) {
            ci.setReturnValue(false);
        }

        // Colored blocks do not connect to Honey blocks.
        if (ColoredSlime.isColoredSlimeBlock(block) && block2 == Blocks.HONEY_BLOCK) {
            ci.setReturnValue(false);
        }

        // Colored blocks do not stick to colored blocks of other colors.
        if (ColoredSlime.isColoredSlimeBlock(block) && ColoredSlime.isColoredSlimeBlock(block2) && block != block2) {
            ci.setReturnValue(false);
        }

    }
}
