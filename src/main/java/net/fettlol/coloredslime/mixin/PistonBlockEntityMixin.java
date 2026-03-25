package net.fettlol.coloredslime.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import static net.fettlol.coloredslime.util.Helpers.isColoredHoney;
import static net.fettlol.coloredslime.util.Helpers.isColoredSlime;

@Mixin(PistonMovingBlockEntity.class)
public abstract class PistonBlockEntityMixin {
	@Shadow
	private BlockState movedState;

	@ModifyExpressionValue(method = "moveCollidedEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z"))
	private static boolean isColoredSlimeBlock(boolean original, @Local(argsOnly = true) PistonMovingBlockEntity blockEntity) {
		return original || isColoredSlime(blockEntity.getMovedState());
	}

	@ModifyExpressionValue(method = "isStickyForEntities", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z"))
	private boolean isColoredHoneyBlock(boolean original) {
		return original || isColoredHoney(movedState);
	}
}
