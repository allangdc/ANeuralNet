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
			input.add(null);
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
		System.out.println("SIZE="+Integer.toString(input.size()));
		return input.size();
	}
	
	public void Init()
	{
		for(int i=0; i<input.size(); i++)
		{
			double x = rnd.nextDouble()*2-1;
			SetInput(i, 2*x);
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
			double sum=bias;
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
