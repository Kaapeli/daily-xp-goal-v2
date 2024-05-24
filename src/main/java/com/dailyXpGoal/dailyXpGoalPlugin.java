package com.dailyXpGoal;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.StatChanged;
import net.runelite.api.Skill;
import net.runelite.api.events.GameTick;

import java.time.LocalDate;

@Slf4j
@PluginDescriptor(
	name = "Daily Xp Goal"
)
public class dailyXpGoalPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private dailyXpGoalConfig config;

	@Inject
	private ConfigManager configManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Example started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGING_IN)
		{
			//client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);


			LocalDate prevLogDateFromConfig = LocalDate.parse(config.lastLoginDate());
			LocalDate currentDate = LocalDate.now();
            log.info("The current date is: {}", currentDate.toString());
			//Check if new day
			if(currentDate.isAfter(prevLogDateFromConfig) || config.AAA_DEBUG_ALWAYS_NEW_DAY()) {
				log.info("New day! Resetting and changing prevLogDate");
				setCurrentDayAsLastLoginDay(currentDate.toString());
				setNeedToFetchStats(true);

				if(config.thisDayCompleted()) {
					log.info("this day completed true on reset -- resetting thisdaycompleted and incrementing streak");
					setCurrentStreak(config.currentStreak() + 1);
				} else {
					log.info("this day completed false on reset -- resetting thisdaycompleted and resetting streak");
					setCurrentStreak(0);
				}
				setThisDayCompleted(false);
			}
		}
	}
	@Subscribe
	public void onGameTick(GameTick event)
	{
		if(config.needToFetchStats()){
			log.info("need to fetch stats! fetching...");
			setNeedToFetchStats(false);
			for(Skill skill : Skill.values()){
				log.info(skill.toString());
				int currentXp = client.getSkillExperience(skill);
				log.info(String.valueOf(currentXp));
				switch (skill){
					case ATTACK: 	configManager.setConfiguration("dailyXpGoalConfig", "attackXpStartToday", currentXp);
									break;
					case STRENGTH: 	configManager.setConfiguration("dailyXpGoalConfig", "strengthXpStartToday", currentXp);
									break;
					case DEFENCE:	configManager.setConfiguration("dailyXpGoalConfig", "defenceXpStartToday", currentXp);
									break;
					case HITPOINTS: configManager.setConfiguration("dailyXpGoalConfig", "hitpointsXpStartToday", currentXp);
									break;
					case RANGED: configManager.setConfiguration("dailyXpGoalConfig", "rangedXpStartToday", currentXp);
									break;
					case MAGIC: configManager.setConfiguration("dailyXpGoalConfig", "magicXpStartToday", currentXp);
									break;
					case PRAYER: configManager.setConfiguration("dailyXpGoalConfig", "prayerXpStartToday", currentXp);
									break;
					case MINING: configManager.setConfiguration("dailyXpGoalConfig", "miningXpStartToday", currentXp);
									break;
					case FISHING: configManager.setConfiguration("dailyXpGoalConfig", "fishingXpStartToday", currentXp);
									break;
					case WOODCUTTING: configManager.setConfiguration("dailyXpGoalConfig", "woodcuttingXpStartToday", currentXp);
									break;
					case SMITHING: configManager.setConfiguration("dailyXpGoalConfig", "smithingXpStartToday", currentXp);
									break;
					case COOKING: configManager.setConfiguration("dailyXpGoalConfig", "cookingXpStartToday", currentXp);
									break;
					case FIREMAKING: configManager.setConfiguration("dailyXpGoalConfig", "firemakingXpStartToday", currentXp);
									break;
					case CRAFTING: configManager.setConfiguration("dailyXpGoalConfig", "craftingXpStartToday", currentXp);
									break;
					case FLETCHING: configManager.setConfiguration("dailyXpGoalConfig", "fletchingXpStartToday", currentXp);
									break;
					case RUNECRAFT: configManager.setConfiguration("dailyXpGoalConfig", "runecraftingXpStartToday", currentXp);
									break;
					case HERBLORE: configManager.setConfiguration("dailyXpGoalConfig", "herbloreXpStartToday", currentXp);
									break;
					case CONSTRUCTION: configManager.setConfiguration("dailyXpGoalConfig", "constructionXpStartToday", currentXp);
									break;
					case AGILITY: configManager.setConfiguration("dailyXpGoalConfig", "agilityXpStartToday", currentXp);
									break;
					case THIEVING: configManager.setConfiguration("dailyXpGoalConfig", "thievingXpStartToday", currentXp);
									break;
					case SLAYER: configManager.setConfiguration("dailyXpGoalConfig", "slayerXpStartToday", currentXp);
									break;
					case FARMING: configManager.setConfiguration("dailyXpGoalConfig", "farmingXpStartToday", currentXp);
									break;
					case HUNTER: configManager.setConfiguration("dailyXpGoalConfig", "hunterXpStartToday", currentXp);
									break;
					default: log.info("Unknown skill");
				}
			}
		}
	}

	@Subscribe
	public void onStatChanged(StatChanged statChanged)
	{

		final Skill skill = statChanged.getSkill();
		final int currentXp = statChanged.getXp();
		log.info("onStatChanged called!");
		log.info(skill.toString());
		log.info(String.valueOf(currentXp));

		int todayEarnedXp = 0;

		switch (skill) {
			case ATTACK:
				todayEarnedXp = currentXp - config.attackXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "attackXpGottenToday", todayEarnedXp);
				break;
			case STRENGTH:
				todayEarnedXp = currentXp - config.strengthXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "strengthXpGottenToday", todayEarnedXp);
				break;
			case DEFENCE:
				todayEarnedXp = currentXp - config.defenceXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "defenceXpGottenToday", todayEarnedXp);
				break;
			case HITPOINTS:
				todayEarnedXp = currentXp - config.hitpointsXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "hitpointsXpGottenToday", todayEarnedXp);
				break;
			case RANGED:
				todayEarnedXp = currentXp - config.rangedXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "rangedXpGottenToday", todayEarnedXp);
				break;
			case MAGIC:
				todayEarnedXp = currentXp - config.magicXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "magicXpGottenToday", todayEarnedXp);
				break;
			case PRAYER:
				todayEarnedXp = currentXp - config.prayerXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "prayerXpGottenToday", todayEarnedXp);
				break;
			case MINING:
				todayEarnedXp = currentXp - config.miningXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "miningXpGottenToday", todayEarnedXp);
				break;
			case FISHING:
				todayEarnedXp = currentXp - config.fishingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "fishingXpGottenToday", todayEarnedXp);
				break;
			case WOODCUTTING:
				todayEarnedXp = currentXp - config.woodcuttingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "woodcuttingXpGottenToday", todayEarnedXp);
				break;
			case SMITHING:
				todayEarnedXp = currentXp - config.smithingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "smithingXpGottenToday", todayEarnedXp);
				break;
			case COOKING:
				todayEarnedXp = currentXp - config.cookingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "cookingXpGottenToday", todayEarnedXp);
				break;
			case FIREMAKING:
				todayEarnedXp = currentXp - config.firemakingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "firemakingXpGottenToday", todayEarnedXp);
				break;
			case CRAFTING:
				todayEarnedXp = currentXp - config.craftingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "craftingXpGottenToday", todayEarnedXp);
				break;
			case FLETCHING:
				todayEarnedXp = currentXp - config.fletchingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "fletchingXpGottenToday", todayEarnedXp);
				break;
			case RUNECRAFT:
				todayEarnedXp = currentXp - config.runecraftingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "runecraftingXpGottenToday", todayEarnedXp);
				break;
			case HERBLORE:
				todayEarnedXp = currentXp - config.herbloreXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "herbloreXpGottenToday", todayEarnedXp);
				break;
			case CONSTRUCTION:
				todayEarnedXp = currentXp - config.constructionXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "constructionXpGottenToday", todayEarnedXp);
				break;
			case AGILITY:
				todayEarnedXp = currentXp - config.strengthXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "agilityXpGottenToday", todayEarnedXp);
				break;
			case THIEVING:
				todayEarnedXp = currentXp - config.thievingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "thievingXpGottenToday", todayEarnedXp);
				break;
			case SLAYER:
				todayEarnedXp = currentXp - config.slayerXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "slayerXpGottenToday", todayEarnedXp);
				break;
			case FARMING:
				todayEarnedXp = currentXp - config.farmingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "farmingXpGottenToday", todayEarnedXp);
				break;
			case HUNTER:
				todayEarnedXp = currentXp - config.hunterXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "hunterXpGottenToday", todayEarnedXp);
				break;
			default:
				log.info("Unknown skill - onStatChange");
		}
	}

	@Provides
	dailyXpGoalConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(dailyXpGoalConfig.class);
	}

	void setCurrentDayAsLastLoginDay(String currentDayString){
		configManager.setConfiguration("dailyXpGoalConfig", "lastLoginDate", currentDayString);
	}

	void setCurrentStreak(int newCurrentStreak){
		configManager.setConfiguration("dailyXpGoalConfig", "currentStreak", newCurrentStreak);
	}

	void setThisDayCompleted(boolean newThisDayCompleted){
		configManager.setConfiguration("dailyXpGoalConfig", "thisDayCompleted", newThisDayCompleted);
	}
	void setNeedToFetchStats(boolean newNeedToFetchStats){
		configManager.setConfiguration("dailyXpGoalConfig", "needToFetchStats", newNeedToFetchStats);
	}
}
