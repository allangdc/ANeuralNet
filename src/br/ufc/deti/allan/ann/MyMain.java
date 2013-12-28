package br.ufc.deti.allan.ann;

public class MyMain 
{
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException 
	{
		// TODO Auto-generated method stub
		APerceptron p = new APerceptron();
		p.Init(3);
		p.start();
		for(int i=0; i<p.GetNInputs(); i++)
		{
			System.out.println("A[" + Integer.toString(i) + "] = " + Double.toString(p.GetInput(i)));
		}
		p.join();
		System.out.println("out = " + Double.toString(p.GetOutput()));
	}
}
