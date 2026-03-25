package net.fettlol.coloredslime.mixin;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.HoneyBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(HoneyBlock.class)
public abstract class HoneyBlockMixin extends HalfTransparentBlock {
	public HoneyBlockMixin(Properties settings) {
		super(settings);
	}

	@WrapWithCondition(
		method = "fallOn",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;broadcastEntityEvent(Lnet/minecraft/world/entity/Entity;B)V")
	)
	public boolean spawnRichColoredHoneyParticles(Level instance, Entity entity, byte status) {
		if (instance instanceof ServerLevel serverWorld) {
			serverWorld.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, defaultBlockState()), entity.getX(), entity.getY(), entity.getZ(), 10, 0, 0, 0, 0);
			return false;
		} else {
			return true;
		}
	}

	@WrapWithCondition(
		method = "maybeDoSlideEffects",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;broadcastEntityEvent(Lnet/minecraft/world/entity/Entity;B)V")
	)
	public boolean spawnRegularColoredHoneyParticles(Level instance, Entity entity, byte status) {
		if (instance instanceof ServerLevel serverWorld) {
			serverWorld.sendParticles(new BlockParticleOption(ParticleTypes.BLOCK, defaultBlockState()), entity.getX(), entity.getY(), entity.getZ(), 5, 0, 0, 0, 0);
			return false;
		} else {
			return true;
		}
	}
}
