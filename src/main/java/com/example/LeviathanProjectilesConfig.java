package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(LeviathanProjectilesConfig.GROUP)
public interface LeviathanProjectilesConfig extends Config
{
	String GROUP = "Leviathan-projectiles";

	@Getter
	@AllArgsConstructor
	enum Style
	{
		Inferno(1378, 1380, 1375),
		CoX(1343, 1341, 1345),
		ToB(1607, 1606, 1604),
		ToA(2241, 2224, 12696); // need correct toa melee orb id

		private final int range;
		private final int magic;
		private final int melee;
	}

	@ConfigItem(
			keyName = "style",
			name = "Projectile Style",
			description = "The projectile style to use."
	)
	default Style style()
	{
		return Style.CoX;
	}
}
