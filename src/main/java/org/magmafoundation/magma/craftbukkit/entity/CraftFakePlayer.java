package org.magmafoundation.magma.craftbukkit.entity;

import net.minecraftforge.common.util.FakePlayer;
import org.bukkit.craftbukkit.v1_18_R2.CraftServer;
import org.bukkit.craftbukkit.v1_18_R2.entity.CraftPlayer;
import org.bukkit.permissions.Permission;
import org.magmafoundation.magma.configuration.MagmaConfig;

public class CraftFakePlayer extends CraftPlayer {

    public CraftFakePlayer(CraftServer server, FakePlayer entity) {
        super(server, entity);
    }

    @Override
    public boolean hasPermission(String name) {
        return MagmaConfig.instance.fakePlayerPermissions.contains(name) || super.hasPermission(name);
    }

    @Override
    public boolean hasPermission(Permission perm) {
        return MagmaConfig.instance.fakePlayerPermissions.contains(perm.getName()) || super.hasPermission(perm);
    }

    @Override
    public boolean isPermissionSet(String name) {
        return MagmaConfig.instance.fakePlayerPermissions.contains(name) || super.isPermissionSet(name);
    }

    @Override
    public boolean isPermissionSet(Permission perm) {
        return MagmaConfig.instance.fakePlayerPermissions.contains(perm.getName()) || super.isPermissionSet(perm);
    }

    public String toString() {
        return "CraftFakePlayer{" + "name=" + getName() + '}';
    }
}
