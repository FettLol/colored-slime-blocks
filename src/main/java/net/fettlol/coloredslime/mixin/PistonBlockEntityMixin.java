package net.fettlol.coloredslime.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.PistonBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import static net.fettlol.coloredslime.util.Helpers.isColoredHoney;
import static net.fettlol.coloredslime.util.Helpers.isColoredSlime;

@Mixin(PistonBlockEntity.class)
public abstract class PistonBlockEntityMixin {
	@Shadow
	private BlockState pushedBlock;

	@ModifyExpressionValue(method = "pushEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	private static boolean isColoredSlimeBlock(boolean original, @Local(argsOnly = true) PistonBlockEntity blockEntity) {
		return original || isColoredSlime(blockEntity.getPushedBlock());
	}

	@ModifyExpressionValue(method = "isPushingHoneyBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	private boolean isColoredHoneyBlock(boolean original) {
		return original || isColoredHoney(pushedBlock);
	}
}
