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
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			//client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);


			LocalDate prevLogDateFromConfig = LocalDate.parse(config.lastLoginDate());
			LocalDate currentDate = LocalDate.now();
            log.info("The current date is: {}", currentDate.toString());
			//Check if new day
			if(currentDate.isAfter(prevLogDateFromConfig)) {
				log.info("New day! Resetting and changing prevLogDate");
				setCurrentDayAsLastLoginDay(currentDate.toString());

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
}
