package org.bukkit.craftbukkit.v1_18_R2.inventory;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import org.apache.commons.lang3.Validate;

public class CraftMerchantCustom extends CraftMerchant {

    @Deprecated // Paper - Adventure
    public CraftMerchantCustom(String title) {
        super(new MinecraftMerchant(title));
        getMerchant().craftMerchant = this;
    }
    // Paper start
    public CraftMerchantCustom(net.kyori.adventure.text.Component title) {
        super(new MinecraftMerchant(title));
        getMerchant().craftMerchant = this;
    }
    // Paper end

    @Override
    public String toString() {
        return "CraftMerchantCustom";
    }

    @Override
    public MinecraftMerchant getMerchant() {
        return (MinecraftMerchant) super.getMerchant();
    }

    public static class MinecraftMerchant implements Merchant {

        private final Component title;
        private final MerchantOffers trades = new MerchantOffers();
        private net.minecraft.world.entity.player.Player tradingPlayer;
        private Level tradingWorld;
        protected CraftMerchant craftMerchant;

        @Deprecated // Paper - Adventure
        public MinecraftMerchant(String title) {
            Validate.notNull(title, "Title cannot be null");
            this.title = new TextComponent(title);
        }
        // Paper start
        public MinecraftMerchant(net.kyori.adventure.text.Component title) {
            Validate.notNull(title, "Title cannot be null");
            this.title = io.papermc.paper.adventure.PaperAdventure.asVanilla(title);
        }
        // Paper end

        @Override
        public CraftMerchant getCraftMerchant() {
            return craftMerchant;
        }

        @Override
        public void setTradingPlayer(net.minecraft.world.entity.player.Player entityhuman) {
            this.tradingPlayer = entityhuman;
            if (entityhuman != null) {
                this.tradingWorld = entityhuman.level;
            }
        }

        @Override
        public net.minecraft.world.entity.player.Player getTradingPlayer() {
            return this.tradingPlayer;
        }

        @Override
        public MerchantOffers getOffers() {
            return this.trades;
        }

        @Override
        public void notifyTrade(MerchantOffer merchantrecipe) {
            // increase recipe's uses
            merchantrecipe.increaseUses();
        }

        @Override
        public void notifyTradeUpdated(ItemStack itemstack) {
        }

        public Component getScoreboardDisplayName() {
            return title;
        }

        @Override
        public int getVillagerXp() {
            return 0; // xp
        }

        @Override
        public void overrideXp(int i) {
        }

        @Override
        public boolean showProgressBar() {
            return false; // is-regular-villager flag (hides some gui elements: xp bar, name suffix)
        }

        @Override
        public SoundEvent getNotifyTradeSound() {
            return SoundEvents.VILLAGER_YES;
        }

        @Override
        public void overrideOffers(MerchantOffers merchantrecipelist) {
        }

        @Override
        public boolean isClientSide() {
            return false;
        }
    }
}
