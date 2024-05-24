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
import java.util.ArrayList;

import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import java.awt.image.BufferedImage;

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

	@Inject
	private ClientToolbar clientToolbar;

	private NavigationButton navButton;
	private DailyXpGoalPanel panel;

	@Override
	protected void startUp() throws Exception
	{
		panel = new DailyXpGoalPanel();

		final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "/daily_xp_goal_icon.png");

		navButton = NavigationButton.builder()
				.tooltip("Daily Xp Goal")
				.icon(icon)
				.priority(7)
				.panel(panel)
				.build();

		clientToolbar.addNavigation(navButton);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Example stopped!");
	}

	private ArrayList<Skill> skillsWhichHaveGoalsArrayList;
	private ArrayList<Skill> todayCompletedSkills = new ArrayList<Skill>();
	int skipFirstLoginCounter;

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGING_IN)
		{
			//client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);

			skipFirstLoginCounter = 0;
			LocalDate prevLogDateFromConfig = LocalDate.parse(config.lastLoginDate());
			LocalDate currentDate = LocalDate.now();
            log.info("The current date is: {}", currentDate.toString());
			//Check if new day
			if(currentDate.isAfter(prevLogDateFromConfig) || config.AAA_DEBUG_ALWAYS_NEW_DAY()) {
				log.info("New day! Resetting and changing prevLogDate");
				setCurrentDayAsLastLoginDay(currentDate.toString());
				resetAllDailyStats();
				setNeedToFetchStats(true);

				if(config.thisDayCompleted()) {
					log.info("this day completed true on reset -- resetting thisdaycompleted and incrementing streak");
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
				//log.info(skill.toString());
				int currentXp = client.getSkillExperience(skill);
				//log.info(String.valueOf(currentXp));
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
		//Check which skills have > 0 xp as goal
		ArrayList<Skill> tempWhichSkillsHaveGoals = new ArrayList<Skill>();
		for(Skill checkSkill : Skill.values()){
			switch (checkSkill){
				case ATTACK: if(config.goalAttack() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case STRENGTH: if(config.goalStrength() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case DEFENCE: if(config.goalDefence() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case HITPOINTS: if(config.goalHitpoints() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case RANGED: if(config.goalRanged() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case MAGIC: if(config.goalMagic() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case PRAYER: if(config.goalPrayer() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case MINING: if(config.goalMining() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case FISHING: if(config.goalFishing() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case WOODCUTTING: if(config.goalWoodcutting() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case SMITHING: if(config.goalSmithing() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case COOKING: if(config.goalCooking() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case FIREMAKING: if(config.goalFiremaking() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case CRAFTING: if(config.goalCrafting() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case FLETCHING: if(config.goalFletching() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case RUNECRAFT: if(config.goalRunecrafting() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case HERBLORE: if(config.goalHerblore() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case CONSTRUCTION: if(config.goalConstruction() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case AGILITY: if(config.goalAgility() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case THIEVING: if(config.goalThieving() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case SLAYER: if(config.goalSlayer() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case FARMING: if(config.goalFarming() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				case HUNTER: if(config.goalHunter() > 0) { tempWhichSkillsHaveGoals.add(checkSkill); };
					break;
				default: log.info("Unknown skill - checking which skills have goals set");
			}
		}
		skillsWhichHaveGoalsArrayList = tempWhichSkillsHaveGoals;

		//Skip getting of xp on login
		if(skipFirstLoginCounter < Skill.values().length){
			skipFirstLoginCounter++;
			return;
		}
		final Skill skill = statChanged.getSkill();
		final int currentXp = statChanged.getXp();
		log.info("onStatChanged called!");
		//log.info(skill.toString());
		//log.info(String.valueOf(currentXp));

		//Add xp earned today to skill
		int todayEarnedXp = 0;
		switch (skill) {
			case ATTACK:
				todayEarnedXp = currentXp - config.attackXpStartToday();
				//log.info(String.valueOf(config.goalAttack()));
				configManager.setConfiguration("dailyXpGoalConfig", "attackXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalAttack()){
					log.info("attack goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case STRENGTH:
				todayEarnedXp = currentXp - config.strengthXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "strengthXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalStrength()){
					log.info("strength goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case DEFENCE:
				todayEarnedXp = currentXp - config.defenceXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "defenceXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalDefence()){
					log.info("defence goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case HITPOINTS:
				todayEarnedXp = currentXp - config.hitpointsXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "hitpointsXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalHitpoints()){
					log.info("hitpoints goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case RANGED:
				todayEarnedXp = currentXp - config.rangedXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "rangedXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalRanged()){
					log.info("ranged goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case MAGIC:
				todayEarnedXp = currentXp - config.magicXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "magicXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalMagic()){
					log.info("magic goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case PRAYER:
				todayEarnedXp = currentXp - config.prayerXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "prayerXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalPrayer()){
					log.info("prayer goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case MINING:
				todayEarnedXp = currentXp - config.miningXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "miningXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalMining()){
					log.info("mining goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case FISHING:
				todayEarnedXp = currentXp - config.fishingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "fishingXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalFishing()){
					log.info("fishing goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case WOODCUTTING:
				todayEarnedXp = currentXp - config.woodcuttingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "woodcuttingXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalWoodcutting()){
					log.info("woodcutting goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case SMITHING:
				todayEarnedXp = currentXp - config.smithingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "smithingXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalSmithing()){
					log.info("smithing goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case COOKING:
				todayEarnedXp = currentXp - config.cookingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "cookingXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalCooking()){
					log.info("cooking goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case FIREMAKING:
				todayEarnedXp = currentXp - config.firemakingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "firemakingXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalFiremaking()){
					log.info("firemaking goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case CRAFTING:
				todayEarnedXp = currentXp - config.craftingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "craftingXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalCrafting()){
					log.info("crafting goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case FLETCHING:
				todayEarnedXp = currentXp - config.fletchingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "fletchingXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalFletching()){
					log.info("flecthing goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case RUNECRAFT:
				todayEarnedXp = currentXp - config.runecraftingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "runecraftingXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalRunecrafting()){
					log.info("runecrafting goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case HERBLORE:
				todayEarnedXp = currentXp - config.herbloreXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "herbloreXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalHerblore()){
					log.info("herblore goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case CONSTRUCTION:
				todayEarnedXp = currentXp - config.constructionXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "constructionXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalConstruction()){
					log.info("contruction goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case AGILITY:
				todayEarnedXp = currentXp - config.strengthXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "agilityXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalAgility()){
					log.info("agility goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case THIEVING:
				todayEarnedXp = currentXp - config.thievingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "thievingXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalThieving()){
					log.info("thieving goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case SLAYER:
				todayEarnedXp = currentXp - config.slayerXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "slayerXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalSlayer()){
					log.info("slayer goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case FARMING:
				todayEarnedXp = currentXp - config.farmingXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "farmingXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalFarming()){
					log.info("farming goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			case HUNTER:
				todayEarnedXp = currentXp - config.hunterXpStartToday();
				configManager.setConfiguration("dailyXpGoalConfig", "hunterXpGottenToday", todayEarnedXp);
				if(todayEarnedXp >= config.goalHunter()){
					log.info("hunter goal met! adding to list...");
					if(!todayCompletedSkills.contains(skill)){
						todayCompletedSkills.add(skill);
					}
				}
				break;
			default:
				log.info("Unknown skill - onStatChange");
		}
		log.info("COMPLETED SKILLS:");
		for(int i = 0; i < todayCompletedSkills.size(); i++){
			log.info(todayCompletedSkills.toArray()[i].toString());
		}

		//Check if all goals have been met
		if(todayCompletedSkills.size() == skillsWhichHaveGoalsArrayList.size() && !skillsWhichHaveGoalsArrayList.isEmpty()){
			setCurrentStreak(config.currentStreak()+1);
			setThisDayCompleted(true);
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

	void resetAllDailyStats(){
		configManager.setConfiguration("dailyXpGoalConfig", "attackXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "strengthXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "defenceXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "hitpointsXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "rangedXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "magicXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "prayerXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "miningXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "fishingXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "woodcuttingXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "smithingXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "cookingXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "firemakingXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "craftingXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "fletchingXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "runecraftingXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "herbloreXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "constructionXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "agilityXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "thievingXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "slayerXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "farmingXpGottenToday", 0);
		configManager.setConfiguration("dailyXpGoalConfig", "hunterXpGottenToday", 0);
	}
}
