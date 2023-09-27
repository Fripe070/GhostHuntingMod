package io.github.fripe070.ghosthunting.block;

import io.github.fripe070.ghosthunting.GhostHunting;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

public class LightswitchBlockEntity extends BlockEntity implements BlockEntityProvider {
    public LightswitchBlockEntity(BlockPos pos, BlockState state) {
        super(GhostHunting.LIGHT_SWITCH_BLOCK_ENTITY, pos, state);
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LightswitchBlockEntity(pos, state);
    }

    public int[] activate_position = new int[3];

    @Override
    public void writeNbt(NbtCompound nbt) {
        nbt.putIntArray("activate_position", activate_position);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        activate_position = nbt.getIntArray("activate_position");
    }
}
