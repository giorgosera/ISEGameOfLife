package ise.gameoflife.agents;

import ise.gameoflife.participants.AbstractAgent;
import ise.gameoflife.models.Food;
import presage.EnvironmentConnector;

/**
 * Test agent of joy!
 * @author Benedict
 */
public class TestAgent extends AbstractAgent
{
	private static final long serialVersionUID = 1L;

	@Deprecated
	public TestAgent()
	{
		super();
	}

	/**
	 * Creates a new agent with the two primary properties
	 * @param initialFood The initial amount of food
	 * @param consumption The amount consumed per turn
	 */
	public TestAgent(double initialFood, double consumption)
	{
		super("<hunter>", 0, initialFood, consumption);
	}
/**
	 * Uses the user's magically specified heuristics to determine which food type
	 * the player wishes to hunt for. 
	 * @return 
	 */
	@Override
	protected Food chooseFood()
	{
		Food bestSoFar = null;

		for (Food noms : ec.availableFoods())
		{
			if (noms.getHuntersRequired() <= 1)
			{
				if (bestSoFar == null)
				{
					bestSoFar = noms;
				}
			}
				if (noms.getNutrition() > bestSoFar.getNutrition())
				{
					bestSoFar = noms;
				}
		}
		return bestSoFar;
	}

	@Override
	protected void onInit(EnvironmentConnector ec)
	{
		// Nothing to see here. Move along, citizen!
	}

	@Override
	protected void onActivate()
	{
		// Nothing to see here. Move along, citizen!
	}
	
}
