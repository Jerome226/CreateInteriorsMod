package systems.alexander.interiors.item;

import org.jetbrains.annotations.Nullable;

import com.simibubi.create.foundation.utility.CreateRegistry;

import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.registries.ForgeRegistries;

public interface TooltipModifier {
	CreateRegistry<Item, TooltipModifier> REGISTRY = new CreateRegistry<>(ForgeRegistries.ITEMS);

	TooltipModifier EMPTY = new TooltipModifier() {
		@Override
		public void modify(ItemTooltipEvent context) {
		}

		@Override
		public TooltipModifier andThen(TooltipModifier after) {
			return after;
		}
	};

	void modify(ItemTooltipEvent context);

	default TooltipModifier andThen(TooltipModifier after) {
		if (after == EMPTY) {
			return this;
		}
		return tooltip -> {
			modify(tooltip);
			after.modify(tooltip);
		};
	}

	static TooltipModifier mapNull(@Nullable TooltipModifier modifier) {
		if (modifier == null) {
			return EMPTY;
		}
		return modifier;
	}
}