package com.dailyXpGoal;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("dailyXpGoalConfig")
public interface dailyXpGoalConfig extends Config
{
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
			keyName = "goalDefence",
			name = "Defence Goal",
			description = "Daily goal for defence XP"
	)
	default int goalDefence() { return 0; }

	@ConfigItem(
			keyName = "goalHitpoints",
			name = "Hitpoints Goal",
			description = "Daily goal for hitpoints XP"
	)
	default int goalHitpoints() { return 0; }

	@ConfigItem(
			keyName = "goalRanged",
			name = "Ranged Goal",
			description = "Daily goal for ranged XP"
	)
	default int goalRanged() { return 0; }

	@ConfigItem(
			keyName = "goalMagic",
			name = "Magic Goal",
			description = "Daily goal for magic XP"
	)
	default int goalMagic() { return 0; }

	@ConfigItem(
			keyName = "goalPrayer",
			name = "Prayer Goal",
			description = "Daily goal for prayer XP"
	)
	default int goalPrayer() { return 0; }

	@ConfigItem(
			keyName = "goalMining",
			name = "Mining Goal",
			description = "Daily goal for mining XP"
	)
	default int goalMining() { return 0; }

	@ConfigItem(
			keyName = "goalFishing",
			name = "Fishing Goal",
			description = "Daily goal for fishing XP"
	)
	default int goalFishing() { return 0; }

	@ConfigItem(
			keyName = "goalWoodcutting",
			name = "Woodcutting Goal",
			description = "Daily goal for woodcutting XP"
	)
	default int goalWoodcutting() { return 0; }

	@ConfigItem(
			keyName = "goalSmithing",
			name = "Smithing Goal",
			description = "Daily goal for smithing XP"
	)
	default int goalSmithing() { return 0; }

	@ConfigItem(
			keyName = "goalCooking",
			name = "Cooking Goal",
			description = "Daily goal for cooking XP"
	)
	default int goalCooking() { return 0; }

	@ConfigItem(
			keyName = "goalFiremaking",
			name = "Firemaking Goal",
			description = "Daily goal for firemaking XP"
	)
	default int goalFiremaking() { return 0; }

	@ConfigItem(
			keyName = "goalCrafting",
			name = "Crafting Goal",
			description = "Daily goal for crafting XP"
	)
	default int goalCrafting() { return 0; }

	@ConfigItem(
			keyName = "goalFletching",
			name = "Fletching Goal",
			description = "Daily goal for fletching XP"
	)
	default int goalFletching() { return 0; }

	@ConfigItem(
			keyName = "goalRunecrafting",
			name = "Runecrafting Goal",
			description = "Daily goal for runecrafting XP"
	)
	default int goalRunecrafting() { return 0; }

	@ConfigItem(
			keyName = "goalHerblore",
			name = "Herblore Goal",
			description = "Daily goal for herblore XP"
	)
	default int goalHerblore() { return 0; }

	@ConfigItem(
			keyName = "goalConstruction",
			name = "Construction Goal",
			description = "Daily goal for construction XP"
	)
	default int goalConstruction() { return 0; }

	@ConfigItem(
			keyName = "goalAgility",
			name = "Agility Goal",
			description = "Daily goal for agility XP"
	)
	default int goalAgility() { return 0; }

	@ConfigItem(
			keyName = "goalThieving",
			name = "Thieving Goal",
			description = "Daily goal for thieving XP"
	)
	default int goalThieving() { return 0; }

	@ConfigItem(
			keyName = "goalSlayer",
			name = "Slayer Goal",
			description = "Daily goal for slayer XP"
	)
	default int goalSlayer() { return 0; }

	@ConfigItem(
			keyName = "goalFarming",
			name = "Farming Goal",
			description = "Daily goal for farming XP"
	)
	default int goalFarming() { return 0; }

	@ConfigItem(
			keyName = "goalHunter",
			name = "Hunter Goal",
			description = "Daily goal for hunter XP"
	)
	default int goalHunter() { return 0; }


	//-------------------------


	@ConfigItem(
			keyName = "attackXpStartToday",
			hidden = false,
			name = "attackXpStartToday",
			description = "Starting amount of attack XP"
	)
	default int attackXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "strengthXpStartToday",
			hidden = false,
			name = "strengthXpStartToday",
			description = "Starting amount of strength XP"
	)
	default int strengthXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "defenceXpStartToday",
			hidden = false,
			name = "defenceXpStartToday",
			description = "Starting amount of defence XP"
	)
	default int defenceXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "hitpointsXpStartToday",
			hidden = false,
			name = "hitpointsXpStartToday",
			description = "Starting amount of hitpoints XP"
	)
	default int hitpointsXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "rangedXpStartToday",
			hidden = false,
			name = "rangedXpStartToday",
			description = "Starting amount of ranged XP"
	)
	default int rangedXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "magicXpStartToday",
			hidden = false,
			name = "magicXpStartToday",
			description = "Starting amount of magic XP"
	)
	default int magicXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "prayerXpStartToday",
			hidden = false,
			name = "prayerXpStartToday",
			description = "Starting amount of prayer XP"
	)
	default int prayerXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "miningXpStartToday",
			hidden = false,
			name = "miningXpStartToday",
			description = "Starting amount of mining XP"
	)
	default int miningXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "fishingXpStartToday",
			hidden = false,
			name = "fishingXpStartToday",
			description = "Starting amount of fishing XP"
	)
	default int fishingXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "woodcuttingXpStartToday",
			hidden = false,
			name = "woodcuttingXpStartToday",
			description = "Starting amount of woodcutting XP"
	)
	default int woodcuttingXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "smithingXpStartToday",
			hidden = false,
			name = "smithingXpStartToday",
			description = "Starting amount of smithing XP"
	)
	default int smithingXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "cookingXpStartToday",
			hidden = false,
			name = "cookingXpStartToday",
			description = "Starting amount of cooking XP"
	)
	default int cookingXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "firemakingXpStartToday",
			hidden = false,
			name = "firemakingXpStartToday",
			description = "Starting amount of firemaking XP"
	)
	default int firemakingXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "craftingXpStartToday",
			hidden = false,
			name = "craftingXpStartToday",
			description = "Starting amount of crafting XP"
	)
	default int craftingXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "fletchingXpStartToday",
			hidden = false,
			name = "fletchingXpStartToday",
			description = "Starting amount of fletching XP"
	)
	default int fletchingXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "runecraftingXpStartToday",
			hidden = false,
			name = "runecraftingXpStartToday",
			description = "Starting amount of runecrafting XP"
	)
	default int runecraftingXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "herbloreXpStartToday",
			hidden = false,
			name = "herbloreXpStartToday",
			description = "Starting amount of herblore XP"
	)
	default int herbloreXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "constructionXpStartToday",
			hidden = false,
			name = "constructionXpStartToday",
			description = "Starting amount of construction XP"
	)
	default int constructionXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "agilityXpStartToday",
			hidden = false,
			name = "agilityXpStartToday",
			description = "Starting amount of agility XP"
	)
	default int agilityXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "thievingXpStartToday",
			hidden = false,
			name = "thievingXpStartToday",
			description = "Starting amount of thieving XP"
	)
	default int thievingXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "slayerXpStartToday",
			hidden = false,
			name = "slayerXpStartToday",
			description = "Starting amount of slayer XP"
	)
	default int slayerXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "farmingXpStartToday",
			hidden = false,
			name = "farmingXpStartToday",
			description = "Starting amount of farming XP"
	)
	default int farmingXpStartToday() { return 0; }

	@ConfigItem(
			keyName = "hunterXpStartToday",
			hidden = false,
			name = "hunterXpStartToday",
			description = "Starting amount of hunter XP"
	)
	default int hunterXpStartToday() { return 0; }

	//---------------------

	@ConfigItem(
			keyName = "attackXpGottenToday",
			hidden = false,
			name = "attackXpGottenToday",
			description = "How much attack XP has been gained today"
	)
	default int attackXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "strengthXpGottenToday",
			hidden = false,
			name = "strengthXpGottenToday",
			description = "How much strength XP has been gained today"
	)
	default int strengthXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "defenceXpGottenToday",
			hidden = false,
			name = "defenceXpGottenToday",
			description = "How much defence XP has been gained today"
	)
	default int defenceXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "hitpointsXpGottenToday",
			hidden = false,
			name = "hitpointsXpGottenToday",
			description = "How much hitpoints XP has been gained today"
	)
	default int hitpointsXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "rangedXpGottenToday",
			hidden = false,
			name = "rangedXpGottenToday",
			description = "How much ranged XP has been gained today"
	)
	default int rangedXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "magicXpGottenToday",
			hidden = false,
			name = "magicXpGottenToday",
			description = "How much magic XP has been gained today"
	)
	default int magicXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "prayerXpGottenToday",
			hidden = false,
			name = "prayerXpGottenToday",
			description = "How much prayer XP has been gained today"
	)
	default int prayerXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "miningXpGottenToday",
			hidden = false,
			name = "miningXpGottenToday",
			description = "How much mining XP has been gained today"
	)
	default int miningXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "fishingXpGottenToday",
			hidden = false,
			name = "fishingXpGottenToday",
			description = "How much fishing XP has been gained today"
	)
	default int fishingXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "woodcuttingXpGottenToday",
			hidden = false,
			name = "woodcuttingXpGottenToday",
			description = "How much woodcutting XP has been gained today"
	)
	default int woodcuttingXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "smithingXpGottenToday",
			hidden = false,
			name = "smithingXpGottenToday",
			description = "How much smithing XP has been gained today"
	)
	default int smithingXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "cookingXpGottenToday",
			hidden = false,
			name = "cookingXpGottenToday",
			description = "How much cooking XP has been gained today"
	)
	default int cookingXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "firemakingXpGottenToday",
			hidden = false,
			name = "firemakingXpGottenToday",
			description = "How much firemaking XP has been gained today"
	)
	default int firemakingXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "craftingXpGottenToday",
			hidden = false,
			name = "craftingXpGottenToday",
			description = "How much crafting XP has been gained today"
	)
	default int craftingXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "fletchingXpGottenToday",
			hidden = false,
			name = "fletchingXpGottenToday",
			description = "How much fletching XP has been gained today"
	)
	default int fletchingXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "runecraftingXpGottenToday",
			hidden = false,
			name = "runecraftingXpGottenToday",
			description = "How much runecrafting XP has been gained today"
	)
	default int runecraftingXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "herbloreXpGottenToday",
			hidden = false,
			name = "herbloreXpGottenToday",
			description = "How much herblore XP has been gained today"
	)
	default int herbloreXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "constructionXpGottenToday",
			hidden = false,
			name = "constructionXpGottenToday",
			description = "How much construction XP has been gained today"
	)
	default int constructionXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "agilityXpGottenToday",
			hidden = false,
			name = "agilityXpGottenToday",
			description = "How much agility XP has been gained today"
	)
	default int agilityXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "thievingXpGottenToday",
			hidden = false,
			name = "thievingXpGottenToday",
			description = "How much thieving XP has been gained today"
	)
	default int thievingXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "slayerXpGottenToday",
			hidden = false,
			name = "slayerXpGottenToday",
			description = "How much slayer XP has been gained today"
	)
	default int slayerXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "farmingXpGottenToday",
			hidden = false,
			name = "farmingXpGottenToday",
			description = "How much farming XP has been gained today"
	)
	default int farmingXpGottenToday() { return 0; }

	@ConfigItem(
			keyName = "hunterXpGottenToday",
			hidden = false,
			name = "hunterXpGottenToday",
			description = "How much hunter XP has been gained today"
	)
	default int hunterXpGottenToday() { return 0; }






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

	@ConfigItem(
			keyName = "needToFetchStats",
			hidden = false,
			name = "needToFetchStats",
			description = "need to fetch stats again if new day"
	)
	default boolean needToFetchStats() { return false; }

	@ConfigItem(
			keyName = "AAA_DEBUG_ALWAYS_NEW_DAY",
			hidden = false,
			name = "AAA-DEBUG-ALWAYS-NEW-DAY",
			description = "AAA-DEBUG-ALWAYS-NEW-DAY"
	)
	default boolean AAA_DEBUG_ALWAYS_NEW_DAY() { return false; }
}
