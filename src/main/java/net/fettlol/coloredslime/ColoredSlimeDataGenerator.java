package net.fettlol.coloredslime;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fettlol.coloredslime.util.Helpers;
import net.minecraft.block.Blocks;
import net.minecraft.client.data.BlockStateModelGenerator;
import net.minecraft.client.data.ItemModelGenerator;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.DyeColor;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ColoredSlimeDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		BlockTagGenerator blockTagGenerator = pack.addProvider(BlockTagGenerator::new);
		pack.addProvider((output, registriesFuture) -> new ItemTagGenerator(output, registriesFuture, blockTagGenerator));
		pack.addProvider(RecipeProvider::new);
		pack.addProvider(BlockLootTableProvider::new);
		pack.addProvider(ModelGenerator::new);
	}

	private static class BlockTagGenerator extends FabricTagProvider.BlockTagProvider {
		public BlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
			super(output, completableFuture);
		}

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			var slimeBuilder = getOrCreateTagBuilder(ColoredSlime.SLIMES_BLOCK_TAG);
			slimeBuilder.add(Blocks.SLIME_BLOCK);
			for (DyeColor color : DyeColor.values()) {
				slimeBuilder.add(Helpers.getColoredSlimeId(color));
			}

			var honeyBuilder = getOrCreateTagBuilder(ColoredSlime.HONEYS_BLOCK_TAG);
			honeyBuilder.add(Blocks.HONEY_BLOCK);
			for (DyeColor color : DyeColor.values()) {
				honeyBuilder.add(Helpers.getColoredHoneyId(color));
			}
		}
	}

	private static class ItemTagGenerator extends FabricTagProvider.ItemTagProvider {
		public ItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable FabricTagProvider.BlockTagProvider blockTagProvider) {
			super(output, completableFuture, blockTagProvider);
		}

		@Override
		protected void configure(RegistryWrapper.WrapperLookup arg) {
			copy(ColoredSlime.SLIMES_BLOCK_TAG, ColoredSlime.SLIMES_ITEM_TAG);
			copy(ColoredSlime.HONEYS_BLOCK_TAG, ColoredSlime.HONEYS_ITEM_TAG);
		}
	}

	private static class RecipeProvider extends FabricRecipeProvider {
		public RecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter exporter) {
			return new RecipeGenerator(wrapperLookup, exporter) {
				@Override
				public void generate() {
					for (DyeColor color : DyeColor.values()) {
						ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.REDSTONE, ColoredSlime.SLIME_BLOCKS.get(color), 8)
							.group("slimes")
							.pattern("SSS")
							.pattern("SDS")
							.pattern("SSS")
							.input('S', ingredientFromTag(ColoredSlime.SLIMES_ITEM_TAG))
							.input('D', Registries.ITEM.get(Helpers.getDyeId(color)))
							.criterion("has_slime", conditionsFromTag(ColoredSlime.SLIMES_ITEM_TAG))
							.offerTo(exporter);

						ShapedRecipeJsonBuilder.create(Registries.ITEM, RecipeCategory.REDSTONE, ColoredSlime.HONEY_BLOCKS.get(color), 8)
							.group("honeys")
							.pattern("HHH")
							.pattern("HDH")
							.pattern("HHH")
							.input('H', ingredientFromTag(ColoredSlime.HONEYS_ITEM_TAG))
							.input('D', Registries.ITEM.get(Helpers.getDyeId(color)))
							.criterion("has_honey", conditionsFromTag(ColoredSlime.HONEYS_ITEM_TAG))
							.offerTo(exporter);
					}
				}
			};
		}

		@Override
		public String getName() {
			return "Colored Slime Recipes";
		}
	}

	private static class BlockLootTableProvider extends FabricBlockLootTableProvider {
		protected BlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
			super(dataOutput, registryLookup);
		}

		@Override
		public void generate() {
			for (DyeColor color : DyeColor.values()) {
				addDrop(ColoredSlime.SLIME_BLOCKS.get(color), ColoredSlime.SLIME_BLOCK_ITEMS.get(color));
				addDrop(ColoredSlime.HONEY_BLOCKS.get(color), ColoredSlime.HONEY_BLOCK_ITEMS.get(color));
			}
		}
	}

	private static class ModelGenerator extends FabricModelProvider {
		public ModelGenerator(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator generator) {

		}

		@Override
		public void generateItemModels(ItemModelGenerator generator) {
			for (DyeColor color : DyeColor.values()) {
				generator.register(ColoredSlime.SLIME_BLOCK_ITEMS.get(color));
				generator.register(ColoredSlime.HONEY_BLOCK_ITEMS.get(color));
			}
		}
	}
}
