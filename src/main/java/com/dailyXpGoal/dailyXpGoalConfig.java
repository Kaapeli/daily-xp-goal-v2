package com.dailyXpGoal;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("dailyXpGoalConfig")
public interface dailyXpGoalConfig extends Config
{
	@ConfigItem(
		keyName = "greeting",
		name = "Welcome Greeting",
		description = "The message to show to the user when they login"
	)
	default String greeting()
	{
		return "Hello";
	}

	@ConfigItem(
			keyName = "goalAttack",
			name = "Attack Goal",
			description = "Daily goal for attack XP"
	)
	default int goalAttack() { return 0; }

	@ConfigItem(
			keyName = "goalStrength",
			name = "Strength Goal",
			description = "Daily goal for strength XP"
	)
	default int goalStrength() { return 0; }

	@ConfigItem(
			keyName = "lastLoginDate",
			hidden = false,
			name = "lastLoginDate",
			description = "Date of last login"
	)
	default String lastLoginDate() { return "1900-01-01"; }

	@ConfigItem(
			keyName = "thisDayCompleted",
			hidden = false,
			name = "thisDayCompleted",
			description = "Has this days goals been met"
	)
	default boolean thisDayCompleted() { return false; }

	@ConfigItem(
			keyName = "currentStreak",
			hidden = false,
			name = "currentStreak",
			description = "How long the current streak is"
	)
	default int currentStreak() { return 0; }
}
