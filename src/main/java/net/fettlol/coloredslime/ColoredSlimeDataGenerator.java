package net.fettlol.coloredslime;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fettlol.coloredslime.util.Helpers;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ColoredSlimeDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		BlockTagsProvider blockTagsProvider = pack.addProvider(BlockTagsProvider::new);
		pack.addProvider((output, registriesFuture) -> new ItemTagsProvider(output, registriesFuture, blockTagsProvider));
		pack.addProvider(RecipeProvider::new);
		pack.addProvider(BlockLootSubProvider::new);
		pack.addProvider(ModelProvider::new);
	}

	private static class BlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
		public BlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture) {
			super(output, completableFuture);
		}

		@Override
		protected void addTags(HolderLookup.Provider arg) {
			var slimeBuilder = getOrCreateRawBuilder(ColoredSlime.SLIMES_BLOCK_TAG);
			slimeBuilder.addElement(BuiltInRegistries.BLOCK.getKey(Blocks.SLIME_BLOCK));
			for (DyeColor color : DyeColor.values()) {
				slimeBuilder.addElement(Helpers.getColoredSlimeId(color));
			}

			var honeyBuilder = getOrCreateRawBuilder(ColoredSlime.HONEYS_BLOCK_TAG);
			honeyBuilder.addElement(BuiltInRegistries.BLOCK.getKey(Blocks.HONEY_BLOCK));
			for (DyeColor color : DyeColor.values()) {
				honeyBuilder.addElement(Helpers.getColoredHoneyId(color));
			}
		}
	}

	private static class ItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
		public ItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> completableFuture, @Nullable FabricTagsProvider.BlockTagsProvider blockTagsProvider) {
			super(output, completableFuture, blockTagsProvider);
		}

		@Override
		protected void addTags(HolderLookup.Provider arg) {
			copy(ColoredSlime.SLIMES_BLOCK_TAG, ColoredSlime.SLIMES_ITEM_TAG);
			copy(ColoredSlime.HONEYS_BLOCK_TAG, ColoredSlime.HONEYS_ITEM_TAG);
		}
	}

	private static class RecipeProvider extends FabricRecipeProvider {
		public RecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected net.minecraft.data.recipes.RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput exporter) {
			return new net.minecraft.data.recipes.RecipeProvider(wrapperLookup, exporter) {
				@Override
				public void buildRecipes() {
					for (DyeColor color : DyeColor.values()) {
						ShapedRecipeBuilder.shaped(BuiltInRegistries.ITEM, RecipeCategory.REDSTONE, ColoredSlime.SLIME_BLOCKS.get(color), 8)
							.group("slimes")
							.pattern("SSS")
							.pattern("SDS")
							.pattern("SSS")
							.define('S', tag(ColoredSlime.SLIMES_ITEM_TAG))
							.define('D', BuiltInRegistries.ITEM.getValue(Helpers.getDyeId(color)))
							.unlockedBy("has_slime", has(ColoredSlime.SLIMES_ITEM_TAG))
							.save(output);

						ShapedRecipeBuilder.shaped(BuiltInRegistries.ITEM, RecipeCategory.REDSTONE, ColoredSlime.HONEY_BLOCKS.get(color), 8)
							.group("honeys")
							.pattern("HHH")
							.pattern("HDH")
							.pattern("HHH")
							.define('H', tag(ColoredSlime.HONEYS_ITEM_TAG))
							.define('D', BuiltInRegistries.ITEM.getValue(Helpers.getDyeId(color)))
							.unlockedBy("has_honey", has(ColoredSlime.HONEYS_ITEM_TAG))
							.save(output);
					}
				}
			};
		}

		@Override
		public String getName() {
			return "Colored Slime Recipes";
		}
	}

	private static class BlockLootSubProvider extends FabricBlockLootSubProvider {
		protected BlockLootSubProvider(FabricPackOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
			super(dataOutput, registryLookup);
		}

		@Override
		public void generate() {
			for (DyeColor color : DyeColor.values()) {
				dropOther(ColoredSlime.SLIME_BLOCKS.get(color), ColoredSlime.SLIME_BLOCK_ITEMS.get(color));
				dropOther(ColoredSlime.HONEY_BLOCKS.get(color), ColoredSlime.HONEY_BLOCK_ITEMS.get(color));
			}
		}
	}

	private static class ModelProvider extends FabricModelProvider {
		public ModelProvider(FabricPackOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(BlockModelGenerators generators) {

		}

		@Override
		public void generateItemModels(ItemModelGenerators generators) {
			for (DyeColor color : DyeColor.values()) {
				generators.declareCustomModelItem(ColoredSlime.SLIME_BLOCK_ITEMS.get(color));
				generators.declareCustomModelItem(ColoredSlime.HONEY_BLOCK_ITEMS.get(color));
			}
		}
	}
}
