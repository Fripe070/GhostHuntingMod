package io.github.fripe070.ghosthunting;

import io.github.fripe070.ghosthunting.block.FluorescentTubeBlock;
import io.github.fripe070.ghosthunting.block.LightSwitchBlock;
import io.github.fripe070.ghosthunting.block.LightswitchBlockEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.logging.Logger;


public class GhostHunting implements ModInitializer {
    public static final String MOD_ID = "ghosthunting";
    public static final Logger LOGGER = Logger.getLogger("GhostHunting");

    public static final Block FLUORESCENT_TUBE = registerBlockWithItem(
            "fluorescent_tube",
            new FluorescentTubeBlock(FabricBlockSettings.copy(Blocks.REDSTONE_LAMP))
    );

    public static final Block LIGHT_SWITCH_BLOCK = registerBlockWithItem(
            "light_switch",
            new LightSwitchBlock(FabricBlockSettings.copyOf(Blocks.LEVER))
    );

    public static final BlockEntityType<LightswitchBlockEntity> LIGHT_SWITCH_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(MOD_ID, "light_switch"),
            FabricBlockEntityTypeBuilder.create(LightswitchBlockEntity::new, LIGHT_SWITCH_BLOCK).build()
    );


    @Override
    public void onInitialize() {

    }

    public static <T extends Block> T registerBlockWithItem(String name, T block) {
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
        return block;
    }
}
