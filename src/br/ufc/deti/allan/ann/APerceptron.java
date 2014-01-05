/**
 * 
 */
package br.ufc.deti.allan.ann;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * This class implements the operation of a single neuron.
 * The basis for the neural networks.
 * 
 * @author Allan
 * 
 */
public class APerceptron extends Thread 
{
	private ArrayList<Double> input;
	private ArrayList<Double> weight;
	private Double output;
	private Semaphore semaphore;
	private boolean processed;
	private Random rnd;
	private double bias=0;
	
	/**
	 * The constructor
	 */
	public APerceptron()
	{
		super();
		input = new ArrayList<Double>();
		weight = new ArrayList<Double>();
		semaphore = null;
		processed=false;
		rnd = new Random();
	}

	/**
	 * The copy constructor
	 * 
	 * @param perceptron
	 */
	public APerceptron(APerceptron perceptron)
	{
		super();
		for(int i=0; i<perceptron.GetNInputs(); i++)
		{
			input.add(perceptron.GetInput(i));
			weight.add(perceptron.GetWeight(i));
		}
		semaphore = new Semaphore(semaphore.availablePermits());
		processed = perceptron.IsProcessed();
	}
	
	/**
	 * Informs if the perceptron has processed its inputs.
	 * 
	 * @return boolean
	 * @author Allan
	 */
	public boolean IsProcessed()
	{
		return processed;
	}
	
	/**
	 * Loads a semaphore object. Useful for managing the processing of several perceptrons.
	 * 
	 * @param semaphore
	 * @author Allan
	 */
	public void SetSemaphore(Semaphore semaphore)
	{
		this.semaphore = semaphore;
	}
	
	/**
	 * Allocates a certain number of inputs to the perceptron.
	 * 
	 * @param numberInputs
	 * @author Allan
	 */
	public void SetNInputs(int numberInputs)
	{
		input.clear();
		for(int i=0; i<numberInputs; i++)
		{
			input.add(null);
			weight.add(null);
		}
		processed=false;
	}
	
	/**
	 * Informs how many inputs were allocated.
	 * 
	 * @return int - size of vector
	 * @author Allan
	 */
	public int GetNInputs()
	{
		return input.size();
	}
	
	public void Init()
	{
		for(int i=0; i<input.size(); i++)
		{
			double x = rnd.nextDouble()*2-1;
			SetWeight(i, 2*x);
		}
		bias = rnd.nextDouble()*2-1;
		processed=false;
	}
	
	public void Init(int numberInputs)
	{
		SetNInputs(numberInputs);
		Init();
	}
	
	public void SetInput(int index, double value)
	{
		processed=false;
		if(index < input.size())
			input.set(index, value);
		else
			System.out.println("index "+ Integer.toString(index) +" for input not exists");
	}
	
	public void SetWeight(int index, double value)
	{
		processed=false;
		if(index < weight.size())
			weight.set(index, value);
		else
			System.out.println("index "+ Integer.toString(index) +" for weight not exists");
	}
	
	public Double GetInput(int index)
	{
		if(index < input.size())
			return input.get(index);
		else
		{
			System.out.println("index "+ Integer.toString(index) +" input not exists");
			return null;
		}
	}
	
	public Double GetWeight(int index)
	{
		if(index < weight.size())
			return weight.get(index);
		else
		{
			System.out.println("index "+ Integer.toString(index) +" weight not exists");
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
		try 
		{
			if(semaphore!=null)
				semaphore.acquire();
			double sum=bias;
			for(int i=0; i<GetNInputs(); i++)
				sum += GetInput(i)*GetWeight(i);
			output = ActivationFunction(sum);
			processed=true;
		} catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally 
		{
			if(semaphore!=null)
				semaphore.release();
		}
	}
}
