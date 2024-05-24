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
			if(currentDate.isAfter(prevLogDateFromConfig)) {
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
		final int currentLevel = statChanged.getLevel();
		log.info("onStatChanged called!");
		log.info(skill.toString());
		log.info(String.valueOf(currentXp));
		log.info(String.valueOf(currentLevel));
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
