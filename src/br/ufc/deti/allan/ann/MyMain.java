package br.ufc.deti.allan.ann;

public class MyMain 
{
	/**
	 * @param args
	 */
	private static final int MAX = 3;

	public static void main(String[] args)
	{
		APerceptron p[] = new APerceptron[MAX];
		ALayer layer1 = new ALayer();
		layer1.SetNPerceptrons(MAX);
		for(int i=0; i<MAX; i++)
		{
			p[i] = new APerceptron();
			p[i].SetNInputs(2);
			p[i].Init();
			p[i].SetInput(0, 10);
			p[i].SetInput(1, 20);
			layer1.SetPerceptrons(i, p[i]);
		}
		layer1.Process();
		for(int i=0; i<MAX; i++)
		{
			System.out.println(layer1.GetPerceptron(i).GetOutput());
			// testando comit
		}
	}
}
