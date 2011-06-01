package ise.gameoflife.enviroment;

import ise.gameoflife.models.Food;
import ise.gameoflife.models.GroupDataModel;
import ise.gameoflife.models.PublicAgentDataModel;
import java.util.Set;
import java.util.UUID;

/**
 * TODO: Documentation
 * @author Benedict
 */
public class PublicEnvironmentConnection
{
	private static PublicEnvironmentConnection inst;
	
	public static PublicEnvironmentConnection getInstance()
	{
		return inst;
	}

	private final EnvConnector ec;
	
	PublicEnvironmentConnection(EnvConnector ec)
	{
		this.ec = ec;
		inst = this;
	}
	
	/**
	 * Gets the group object associated with a particular id
	 * @param id The id to search for
	 * @return The group object, or null if not found
	 */
	public GroupDataModel getGroupById(UUID id)
	{
		return ec.getGroupById(id);
	}

	/**
	 * Gets the agent data object associated with a particular id, which is safe
	 * for being passed to other agents without giving them too much information
	 * @param id The id to search for
	 * @return The agent object, or null if not found
	 */
	public PublicAgentDataModel getAgentById(String id)
	{
		return ec.getAgentById(id);
	}

	/**
	 * TODO: Documentation
	 * @return
	 */
	public Set<Food> availableFoods()
	{
		return ec.availableFoods();
	}
	
	// TODO: Offer interfaces to other members of the enviroment connector
	// TODO: that sub-classes should be allowed to use
	// - Group lookup
	// - Food lookup?
	// - Agent lookup
	// - Group type list

}