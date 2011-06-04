package ise.gameoflife;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Allows classes to easily store historical values in a type safe, controlled way
 * @param <T> The type of things being recorded historically
 * @author Benedict
 */
public class UnmodifableHistory<T extends Serializable> extends History<T>
{
	private static final long serialVersionUID = 1L;

	UnmodifableHistory(LinkedList<T> d, int size)
	{
		super(d, size);
	}

	@Override
	public T setValue(T value)
	{
		throw new UnsupportedOperationException("The Histroy is not modifyable");
	}

	@Override
	public void newEntry(boolean cloneOld)
	{
		throw new UnsupportedOperationException("The Histroy is not modifyable");
	}

	@Override
	public void newEntry()
	{
		throw new UnsupportedOperationException("The Histroy is not modifyable");
	}

	@Override
	public void newEntry(T value)
	{
		throw new UnsupportedOperationException("The Histroy is not modifyable");
	}

	@Override
	public void setMaxSize(int maxSize)
	{
		throw new UnsupportedOperationException("The Histroy is not modifyable");
	}

	@Override
	public UnmodifableHistory<T> getUnmodifableHistory()
	{
		return this;
	}

}
