package com.dailyXpGoal;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class dailyXpGoalPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(dailyXpGoalPlugin.class);
		RuneLite.main(args);
	}
}