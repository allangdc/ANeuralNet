/**
 * 
 */
package br.ufc.deti.allan.ann;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * @author Allan
 *
 */
public class APerceptron 
{
	private ArrayList<Double> input;
	private Double output;
	
	public APerceptron() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public void SetNInputs(int numberInputs)
	{
		input.clear();
		for(int i=0; i<numberInputs; i++)
			input.add(null);
	}
	
	public void SetInput(int index, double value)
	{
		if(index < input.size())
			input.set(index, value);
		else
			System.out.println("index "+ Double.toString(index) +" not exists");
	}
	
	public Double GetInput(int index)
	{
		if(index < input.size())
			return input.get(index);
		else
		{
			System.out.println("index "+ Double.toString(index) +" not exists");
			return null;
		}
	}
	
	public Double GetOutput()
	{
		return output;
	}
}
