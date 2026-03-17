package net.fettlol.coloredslime.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.ai.brain.task.LongJumpTask;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.fettlol.coloredslime.util.Helpers.isColoredHoney;

@Mixin(LongJumpTask.class)
public abstract class LongJumpTaskMixin {
	@ModifyExpressionValue(
		method = "shouldRun(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/mob/MobEntity;)Z",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/block/BlockState;isOf(Lnet/minecraft/block/Block;)Z"
		)
	)
	private static boolean isColoredHoneyBlock(boolean original, @Local(argsOnly = true) ServerWorld world, @Local(argsOnly = true) MobEntity mob) {
		return original || isColoredHoney(world.getBlockState(mob.getBlockPos()));
	}
}
