/**
 * 
 */
package br.ufc.deti.allan.ann;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * @author allan
 *
 */
public class ALayer 
{
	private ArrayList<APerceptron> perceptrons;
	private Semaphore semaphore;
	
	public ALayer()
	{
		perceptrons = new ArrayList<APerceptron>();
		Runtime.getRuntime().availableProcessors();
	}
	
	public void SetNPerceptrons(int numberPerceptrons)
	{
		for(int i=0; i<numberPerceptrons; i++)
		{
			perceptrons.add(null);
		}
	}
	
	public void SetPerceptrons(int index, APerceptron perceptron)
	{
		if(index < perceptrons.size())
			perceptrons.set(index, perceptron);
		else
			System.out.println("index "+ Integer.toString(index) +" not exists");
	}
	
	public APerceptron GetPerceptron(int index)
	{
		if(index < perceptrons.size())
			return perceptrons.get(index);
		else
		{
			System.out.println("index "+ Integer.toString(index) +" not exists");
			return null;
		}
	}
}
