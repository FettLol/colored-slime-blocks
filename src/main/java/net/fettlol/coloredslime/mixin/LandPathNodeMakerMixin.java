package net.fettlol.coloredslime.mixin;

import com.llamalad7.mixinextras.expression.Definition;
import com.llamalad7.mixinextras.expression.Expression;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static net.fettlol.coloredslime.util.Helpers.isColoredHoney;

@Mixin(WalkNodeEvaluator.class)
public abstract class LandPathNodeMakerMixin {
	@Definition(id = "blockState", local = @Local(type = BlockState.class))
	@Definition(id = "is", method = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
	@Definition(id = "HONEY_BLOCK", field = "Lnet/minecraft/world/level/block/Blocks;HONEY_BLOCK:Lnet/minecraft/world/level/block/Block;")
	@Expression("blockState.is(HONEY_BLOCK)")
	@ModifyExpressionValue(method = "getPathTypeFromState", at = @At(value = "MIXINEXTRAS:EXPRESSION"))
	private static boolean isColoredHoneyBlock(boolean original, @Local BlockState blockState) {
		return original || isColoredHoney(blockState);
	}
}
