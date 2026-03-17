package net.fettlol.coloredslime.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.ai.brain.task.BreezeJumpTask;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.fettlol.coloredslime.util.Helpers.isColoredHoney;

@Mixin(BreezeJumpTask.class)
public abstract class BreezeJumpTaskMixin {
	@ModifyExpressionValue(method = "hasRoomToJump", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"))
	private static boolean isColoredHoneyBlock(boolean original, @Local(argsOnly = true) ServerWorld world, @Local(argsOnly = true) BreezeEntity breeze) {
		return original || isColoredHoney(world.getBlockState(breeze.getBlockPos()));
	}
}
