/**
 * 
 */
package br.ufc.deti.allan.ann;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * This class implements the concept of layers in a neural network.
 * In it we have stored a set of perceptrons,
 * may be a layer of input, output or hidden layer.
 * 
 * @author allan
 */
public class ALayer 
{
	private ArrayList<APerceptron> perceptrons;
	private Semaphore semaphore;
	
	public ALayer()
	{
		int nprocess;
		perceptrons = new ArrayList<APerceptron>();
		nprocess = Runtime.getRuntime().availableProcessors();
		semaphore = new Semaphore(nprocess);
	}
	
	public Semaphore GetSemaphore()
	{
		return semaphore;
	}
	
	public void SetNPerceptrons(int numberPerceptrons)
	{
		perceptrons.clear();
		for(int i=0; i<numberPerceptrons; i++)
		{
			perceptrons.add(null);
		}
	}
	
	public void SetPerceptrons(int index, APerceptron perceptron)
	{
		if(index < perceptrons.size())
		{
			perceptron.SetSemaphore(semaphore);
			perceptrons.set(index, perceptron);
		}
		else
			System.out.println("index "+ Integer.toString(index) +" for perceptron not exists");
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
	
	public int GetNPerceptrons()
	{
		return perceptrons.size();
	}
	
	public void Process()
	{
		for(int i=0; i<GetNPerceptrons(); i++)
		{
			APerceptron p = GetPerceptron(i);
			p.start();
		}
	}
	
	public boolean IsProcessed()
	{
		for(int i=0; i<perceptrons.size(); i++)
		{
			APerceptron p = GetPerceptron(i);
			if(p.IsProcessed()==false)
				return false;
		}
		return true;
	}
	
	public boolean LinkToPreviousLayer(ALayer previous)
	{
		if(previous.IsProcessed())
		{
			for(int b=0; b<GetNPerceptrons(); b++)
			{
				APerceptron p2 = GetPerceptron(b);
				p2.SetNInputs(previous.GetNPerceptrons());
				for(int a=0; a<previous.GetNPerceptrons(); a++)
				{
					APerceptron p1 = previous.GetPerceptron(a);
					p2.SetInput(a, p1.GetOutput());
				}
			}
			return true;
		}
		else
			return false;
	}
}
