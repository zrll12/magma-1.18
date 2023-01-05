package org.bukkit.craftbukkit.v1_18_R2.inventory.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Container;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.*;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftInventory;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftInventoryBrewer;
import org.bukkit.craftbukkit.v1_18_R2.inventory.CraftInventoryFurnace;
import org.bukkit.craftbukkit.v1_18_R2.util.CraftChatMessage;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public abstract class CraftTileInventoryConverter implements CraftInventoryCreator.InventoryConverter {

    public abstract Container getTileEntity();

    @Override
    public Inventory createInventory(InventoryHolder holder, InventoryType type) {
        return getInventory(getTileEntity());
    }

    // Paper start
    @Override
    public Inventory createInventory(InventoryHolder owner, InventoryType type, net.kyori.adventure.text.Component title) {
        Container te = getTileEntity();
        if (te instanceof RandomizableContainerBlockEntity) {
            ((RandomizableContainerBlockEntity) te).setCustomName(io.papermc.paper.adventure.PaperAdventure.asVanilla(title));
        }
        return getInventory(te);
    }
    // Paper end

    @Override
    public Inventory createInventory(InventoryHolder holder, InventoryType type, String title) {
        Container te = getTileEntity();
        if (te instanceof RandomizableContainerBlockEntity) {
            ((RandomizableContainerBlockEntity) te).setCustomName(CraftChatMessage.fromStringOrNull(title));
        }

        return getInventory(te);
    }

    public Inventory getInventory(Container tileEntity) {
        return new CraftInventory(tileEntity);
    }

    public static class Furnace extends CraftTileInventoryConverter {

        @Override
        public Container getTileEntity() {
            AbstractFurnaceBlockEntity furnace = new FurnaceBlockEntity(BlockPos.ZERO, Blocks.FURNACE.defaultBlockState()); // TODO: customize this if required
            return furnace;
        }

        // Paper start
        @Override
        public Inventory createInventory(InventoryHolder owner, InventoryType type, net.kyori.adventure.text.Component title) {
            Container tileEntity = getTileEntity();
            ((AbstractFurnaceBlockEntity) tileEntity).setCustomName(io.papermc.paper.adventure.PaperAdventure.asVanilla(title));
            return getInventory(tileEntity);
        }
        // Paper end

        @Override
        public Inventory createInventory(InventoryHolder owner, InventoryType type, String title) {
            Container tileEntity = getTileEntity();
            ((AbstractFurnaceBlockEntity) tileEntity).setCustomName(CraftChatMessage.fromStringOrNull(title));
            return getInventory(tileEntity);
        }

        @Override
        public Inventory getInventory(Container tileEntity) {
            return new CraftInventoryFurnace((AbstractFurnaceBlockEntity) tileEntity);
        }
    }

    public static class BrewingStand extends CraftTileInventoryConverter {

        @Override
        public Container getTileEntity() {
            return new BrewingStandBlockEntity(BlockPos.ZERO, Blocks.BREWING_STAND.defaultBlockState());
        }

        // Paper start
        @Override
        public Inventory createInventory(InventoryHolder owner, InventoryType type, net.kyori.adventure.text.Component title) {
            // BrewingStand does not extend TileEntityLootable
            Container tileEntity = getTileEntity();
            if (tileEntity instanceof BrewingStandBlockEntity) {
                ((BrewingStandBlockEntity) tileEntity).setCustomName(io.papermc.paper.adventure.PaperAdventure.asVanilla(title));
            }
            return getInventory(tileEntity);
        }
        // Paper end

        @Override
        public Inventory createInventory(InventoryHolder holder, InventoryType type, String title) {
            // BrewingStand does not extend RandomizableContainerBlockEntity
            Container tileEntity = getTileEntity();
            if (tileEntity instanceof BrewingStandBlockEntity) {
                ((BrewingStandBlockEntity) tileEntity).setCustomName(CraftChatMessage.fromStringOrNull(title));
            }
            return getInventory(tileEntity);
        }

        @Override
        public Inventory getInventory(Container tileEntity) {
            return new CraftInventoryBrewer(tileEntity);
        }
    }

    public static class Dispenser extends CraftTileInventoryConverter {

        @Override
        public Container getTileEntity() {
            return new DispenserBlockEntity(BlockPos.ZERO, Blocks.DISPENSER.defaultBlockState());
        }
    }

    public static class Dropper extends CraftTileInventoryConverter {

        @Override
        public Container getTileEntity() {
            return new DropperBlockEntity(BlockPos.ZERO, Blocks.DROPPER.defaultBlockState());
        }
    }

    public static class Hopper extends CraftTileInventoryConverter {

        @Override
        public Container getTileEntity() {
            return new HopperBlockEntity(BlockPos.ZERO, Blocks.HOPPER.defaultBlockState());
        }
    }

    public static class BlastFurnace extends CraftTileInventoryConverter {

        @Override
        public Container getTileEntity() {
            return new BlastFurnaceBlockEntity(BlockPos.ZERO, Blocks.BLAST_FURNACE.defaultBlockState());
        }
    }

    public static class Lectern extends CraftTileInventoryConverter {

        @Override
        public Container getTileEntity() {
            return new LecternBlockEntity(BlockPos.ZERO, Blocks.LECTERN.defaultBlockState()).bookAccess;
        }
    }

    public static class Smoker extends CraftTileInventoryConverter {

        @Override
        public Container getTileEntity() {
            return new SmokerBlockEntity(BlockPos.ZERO, Blocks.SMOKER.defaultBlockState());
        }
    }
}
