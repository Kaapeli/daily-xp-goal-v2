package com.dailyXpGoal;

import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;

import javax.swing.border.EmptyBorder;
import java.awt.*;

class DailyXpGoalPanel extends PluginPanel
{

    DailyXpGoalPanel()
    {
        super();

        setBorder(new EmptyBorder(10, 10, 10, 10));
        setBackground(ColorScheme.DARKER_GRAY_HOVER_COLOR);
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.gridx = 0;
        c.gridy = 0;

        /*

        inputArea.setBorder(new EmptyBorder(12, 0, 12, 0));
        inputArea.setBackground(ColorScheme.DARK_GRAY_COLOR);

        add(inputArea, c);
        c.gridy++;
        add(outputArea, c);
        c.gridy++;
        */
    }



}