package ise.gameoflife.actions;

import java.util.UUID;
import presage.Input;

/**
 * Used to initiate an action telling the agent to consume an amount of food
 * specified by the environment controller
 * @author christopherfonseka
 */
public class ConsumeFood extends GenericInput
{
	
	private UUID id;
	
	/**
	 * Creates a consume food action which indicates an agent is to consume a food
	 * of type id, which is determined by the environment controller.
	 * @param identification The authentication token of the target agent, to
	 * verify the senders authenticity
	 * @param time  
	 */
	public ConsumeFood(UUID identification, long time) {
		super(time, "consumefood");
		this.id = identification;
	}
	
	/**
	 * Returns the UUID of of the food to be consumed
	 * @return 
	 */
	public UUID getId()
	{
		return id;
	}

}
