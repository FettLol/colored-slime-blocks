package net.brekitomasson.coloredslime;

import net.brekitomasson.coloredslime.blocks.ColoredSlimeBlock;
import net.brekitomasson.coloredslime.util.Logger;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.util.DyeColor;

import java.util.Arrays;
import java.util.List;

public class ColoredSlime implements ModInitializer {

	public static final String MOD_ID = "coloredslime";
	public static final Logger LOGGER = Logger.get();

	public static final ColoredSlimeBlock BLACK_SLIME_BLOCK = new ColoredSlimeBlock("black_slime_block", DyeColor.BLACK);
	public static final ColoredSlimeBlock BLUE_SLIME_BLOCK = new ColoredSlimeBlock("blue_slime_block", DyeColor.BLUE);
	public static final ColoredSlimeBlock BROWN_SLIME_BLOCK = new ColoredSlimeBlock("brown_slime_block", DyeColor.BROWN);
	public static final ColoredSlimeBlock CYAN_SLIME_BLOCK = new ColoredSlimeBlock("cyan_slime_block", DyeColor.CYAN);
	public static final ColoredSlimeBlock GRAY_SLIME_BLOCK = new ColoredSlimeBlock("gray_slime_block", DyeColor.GRAY);
	public static final ColoredSlimeBlock GREEN_SLIME_BLOCK = new ColoredSlimeBlock("green_slime_block", DyeColor.GREEN);
	public static final ColoredSlimeBlock LIGHT_BLUE_SLIME_BLOCK = new ColoredSlimeBlock("light_blue_slime_block", DyeColor.LIGHT_BLUE);
	public static final ColoredSlimeBlock LIGHT_GRAY_SLIME_BLOCK = new ColoredSlimeBlock("light_gray_slime_block", DyeColor.LIGHT_GRAY);
	public static final ColoredSlimeBlock LIME_SLIME_BLOCK = new ColoredSlimeBlock("lime_slime_block", DyeColor.LIME);
	public static final ColoredSlimeBlock MAGENTA_SLIME_BLOCK = new ColoredSlimeBlock("magenta_slime_block", DyeColor.MAGENTA);
	public static final ColoredSlimeBlock ORANGE_SLIME_BLOCK = new ColoredSlimeBlock("orange_slime_block", DyeColor.ORANGE);
	public static final ColoredSlimeBlock PINK_SLIME_BLOCK = new ColoredSlimeBlock("pink_slime_block", DyeColor.PINK);
	public static final ColoredSlimeBlock PURPLE_SLIME_BLOCK = new ColoredSlimeBlock("purple_slime_block", DyeColor.PURPLE);
	public static final ColoredSlimeBlock RED_SLIME_BLOCK = new ColoredSlimeBlock("red_slime_block", DyeColor.RED);
	public static final ColoredSlimeBlock WHITE_SLIME_BLOCK = new ColoredSlimeBlock("white_slime_block", DyeColor.WHITE);
	public static final ColoredSlimeBlock YELLOW_SLIME_BLOCK = new ColoredSlimeBlock("yellow_slime_block", DyeColor.YELLOW);

	@Override
	public void onInitialize() {
		// There's really nothing to do here.
	}

	public static boolean isColoredSlimeBlock(Block block) {
		Block[] coloredBlocks = new Block[]{
				BLACK_SLIME_BLOCK,
				BLUE_SLIME_BLOCK,
				BROWN_SLIME_BLOCK,
				CYAN_SLIME_BLOCK,
				GRAY_SLIME_BLOCK,
				GREEN_SLIME_BLOCK,
				LIGHT_BLUE_SLIME_BLOCK,
				LIGHT_GRAY_SLIME_BLOCK,
				LIME_SLIME_BLOCK,
				MAGENTA_SLIME_BLOCK,
				ORANGE_SLIME_BLOCK,
				PINK_SLIME_BLOCK,
				PURPLE_SLIME_BLOCK,
				RED_SLIME_BLOCK,
				WHITE_SLIME_BLOCK,
				YELLOW_SLIME_BLOCK,
		};

		List<Block> list = Arrays.asList(coloredBlocks);

		return list.contains(block);
	}

}
