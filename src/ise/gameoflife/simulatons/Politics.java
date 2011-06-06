package ise.gameoflife.simulatons;

import ise.gameoflife.agents.TestPoliticalAgent;
import ise.gameoflife.groups.TestPoliticalGroup;
import ise.gameoflife.groups.freeagentgroups.BasicFreeAgentGroup;
import ise.gameoflife.participants.AbstractFreeAgentGroup;
import ise.gameoflife.plugins.ErrorLog;
import ise.gameoflife.plugins.HuntersAlivePlugin;
import ise.gameoflife.plugins.DatabasePlugin;
import ise.gameoflife.plugins.DebugSwitchPlugin;
import ise.gameoflife.plugins.HunterListPlugin;
import ise.gameoflife.plugins.PoliticalCompassPlugin;
import ise.gameoflife.tokens.AgentType;

/**
 *
 * @author george
 */
public class Politics extends GenericSimulation
{

	public Politics()
	{
		super("Basic Politics Testing Bed", 200, 0, 0.1);
	}

	@Override
	protected void agents()
	{
		for (int i = 0; i < 10; i++)
		{
			addAgent(new TestPoliticalAgent(20, 2, AgentType.AC, 0.4));
			addAgent(new TestPoliticalAgent(20, 2, AgentType.TFT, 0.5));
			addAgent(new TestPoliticalAgent(20, 2, AgentType.AD, 0.6));
			addAgent(new TestPoliticalAgent(20, 2, AgentType.R, 0.7));
		}
	}

	@Override
	protected void foods()
	{
		addFood("Rabbit", 2, 1);
		addFood("Stag", 5, 2);
	}

	@Override
	protected void groups()
	{
              addGroup(TestPoliticalGroup.class);
	}

	@Override
	protected Class<? extends AbstractFreeAgentGroup> chooseFreeAgentHandler()
	{
		return BasicFreeAgentGroup.class;
	}

	@Override
	protected void plugins()
	{
		addPlugin(new DebugSwitchPlugin());
		addPlugin(new HuntersAlivePlugin(getPath() + "/population.png", 1500, 1200));
		addPlugin(new ErrorLog());
		addPlugin(new DatabasePlugin(1,"Simulation comment",false));
		addPlugin(new HunterListPlugin());
		addPlugin(new PoliticalCompassPlugin());
	}

	@Override
	protected void events()
	{
	}

}
