package com.lakemonsters2635.util;

public class OneShot<T>
{
	T lowState;
	T previousState;
	public OneShot(T lowState)
	{
		this.lowState = lowState;
	}
	public T oneShot(T currentState)
	{
		if(previousState == null)
		{
			previousState = currentState;
			return currentState;
		}
		if(previousState == currentState)
		{
			return lowState;
		}
		return currentState;
	}
}
