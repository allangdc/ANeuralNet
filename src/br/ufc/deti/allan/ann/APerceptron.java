/**
 * 
 */
package br.ufc.deti.allan.ann;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Allan
 *
 */
public class APerceptron extends Thread 
{
	private ArrayList<Double> input;
	private Double output;
	
	public APerceptron() 
	{
		// TODO Auto-generated constructor stub
		super();
		input = new ArrayList<Double>();
	}
	
	private void InitBias(Double value)
	{
		input.add(0, value);
	}
	
	public void SetNInputs(int numberInputs)
	{
		input.clear();
		for(int i=0; i<numberInputs; i++)
			input.add(null);
		InitBias(null);
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
	}
	
	public void SetInput(int index, double value)
	{
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
		double sum=0;
		for(int i=0; i<input.size(); i++)
		{
			sum += GetInput(i);
			output = ActivationFunction(sum);
		}
	}
}
