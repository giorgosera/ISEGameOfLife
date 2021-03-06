package ise.gameoflife.inputs;

/**
 *
 * @author Benedict
 */
public class VoteResult extends GenericInput
{
	private static final long serialVersionUID = 1L;

	private Proposition p;
	private int votes;
	private double overallMovement;

	public VoteResult(ise.gameoflife.actions.VoteResult v, long timestamp)
	{
		super(timestamp, "voteresult");
		this.p = v.getProposition();
		this.votes = v.getVotes();
		this.overallMovement = v.getOverallMovement();
	}

	public Proposition getProposition()
	{
		return p;
	}

	public int getVotes()
	{
		return votes;
	}

	public double getOverallMovement()
	{
		return overallMovement;
	}

}
