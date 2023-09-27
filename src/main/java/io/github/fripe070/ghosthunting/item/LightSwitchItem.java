package io.github.fripe070.ghosthunting.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class LightSwitchItem extends BlockItem {
    public LightSwitchItem(Block block, Settings settings) {
        super(block, settings);
    }

    BlockPos target_block = null;

    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult actionResult;
        if (target_block == null) {
            target_block = context.getBlockPos();
            context.getWorld().setBlockState(target_block, Blocks.GRAY_CONCRETE.getDefaultState());

            actionResult = ActionResult.SUCCESS;
        } else {
            actionResult = super.useOnBlock(context);
            target_block = null;
        }

        return actionResult;
    }
}
