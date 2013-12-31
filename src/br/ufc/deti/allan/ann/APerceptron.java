/**
 * 
 */
package br.ufc.deti.allan.ann;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * @author Allan
 *
 */
public class APerceptron extends Thread 
{
	private ArrayList<Double> input;
	private Double output;
	private Semaphore semaphore;
	private boolean processed;
	
	public APerceptron()
	{
		super();
		input = new ArrayList<Double>();
		semaphore = null;
		processed=false;
	}

	public APerceptron(APerceptron perceptron)
	{
		super();
		for(int i=0; i<perceptron.GetNInputs(); i++)
		{
			input.add(perceptron.GetInput(i));
		}
		semaphore = new Semaphore(semaphore.availablePermits());
		processed = perceptron.IsProcessed();
	}
	
	public boolean IsProcessed()
	{
		return processed;
	}
	
	public void SetSemaphore(Semaphore semaphore)
	{
		this.semaphore = semaphore;
	}
	
	private void InitBias(Double value)
	{
		input.add(0, value);
		processed=false;
	}
	
	public void SetNInputs(int numberInputs)
	{
		input.clear();
		for(int i=0; i<numberInputs; i++)
			input.add(null);
		InitBias(null);
		processed=false;
	}
	
	public int GetNInputs()
	{
		return input.size();
	}
	
	public void Init(int numberInputs)
	{
		SetNInputs(numberInputs);
		Random rnd = new Random();
		for(int i=0; i<input.size(); i++)
		{
			double x = rnd.nextDouble()*2-1;
			SetInput(i, 2*x);
		}
		processed=false;
	}
	
	public void SetInput(int index, double value)
	{
		processed=false;
		if(index < input.size())
			input.set(index, value);
		else
			System.out.println("index "+ Integer.toString(index) +" not exists");
	}
	
	public Double GetInput(int index)
	{
		if(index < input.size())
			return input.get(index);
		else
		{
			System.out.println("index "+ Integer.toString(index) +" not exists");
			return null;
		}
	}
	
	public Double GetOutput()
	{
		return output;
	}
	
	protected Double ActivationFunction(Double value)
	{
		return value;
	}

	//@Override
	public void run() 
	{
		// TODO Auto-generated method stub
		try 
		{
			if(semaphore!=null)
				semaphore.acquire();
			double sum=0;
			for(int i=0; i<input.size(); i++)
			{
				sum += GetInput(i);
				output = ActivationFunction(sum);
			}
		} catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally 
		{
			if(semaphore!=null)
				semaphore.release();
		}
		processed=true;
	}
}
