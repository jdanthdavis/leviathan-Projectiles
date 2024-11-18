package com.example;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Projectile;
import net.runelite.api.events.ProjectileMoved;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
		name = "Leviathan Projectiles",
		description = "Alter the Projectiles for The Leviathan's Attacks",
		tags = {"Leviathan", "Levi", "projectiles"}
)
public class LeviathanProjectilesPlugin extends Plugin
{
	private static final int MAGIC_PROJECTILE = 2489;
	private static final int RANGE_PROJECTILE = 2487;

	@Inject
	private Client client;

	@Inject
	private LeviathanProjectilesConfig config;

	@Provides
	LeviathanProjectilesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(LeviathanProjectilesConfig.class);
	}

	@Subscribe
	public void onProjectileMoved(ProjectileMoved projectileMoved)
	{
		Projectile projectile = projectileMoved.getProjectile();
		if (projectile.getId() == RANGE_PROJECTILE)
		{
			replaceProjectile(projectile, config.style().getRange());
		}
		else if (projectile.getId() == MAGIC_PROJECTILE)
		{
			replaceProjectile(projectile, config.style().getMagic());
		}
	}

	private void replaceProjectile(Projectile projectile, int projectileId)
	{
		Projectile p = client.createProjectile(projectileId,
				projectile.getFloor(),
				projectile.getX1(), projectile.getY1(),
				projectile.getHeight(),
				projectile.getStartCycle(), projectile.getEndCycle(),
				projectile.getSlope(),
				projectile.getStartHeight(), projectile.getEndHeight(),
				projectile.getInteracting(),
				projectile.getTarget().getX(), projectile.getTarget().getY());

		client.getProjectiles().addLast(p);
		projectile.setEndCycle(0);
	}
}
