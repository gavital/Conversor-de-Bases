import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

public class Main extends JFrame implements ActionListener{

	private static final long serialVersionUID= 1L;
	private JLabel l_entrada,l_saida,vir1,vir2,l_base1,l_base2,l_int1,l_int2,l_frac1,l_frac2,esp1,esp2; //texto a ser exibido na tela
	private JTextField t_BaseEnt, t_BaseSaida, t_IntEnt, t_FracEnt,t_IntSai,t_FracSai;
	private JButton bOk;
	private JPanel pan1, pan2;
	private JFrame frame;
	
	public Main(){
		super("Projeto de CD");
	 
		JFrame frame = new JFrame("Projeto CD");
		JPanel pan1 = new JPanel (new GridLayout(4,5));
		JPanel pan2 = new JPanel (new BorderLayout());
		 
		l_entrada = new JLabel("Valor Que deseja converter");
		l_saida = new JLabel("Valor convertido");
		vir1 = new JLabel(",");
		vir2 = new JLabel(",");
		t_BaseEnt = new JTextField(2);
		t_BaseSaida = new JTextField(2);
		t_IntEnt = new JTextField(20);
		t_FracEnt = new JTextField(20);
		t_IntSai = new JTextField(20);
		t_FracSai = new JTextField(20);
		l_base1 = new JLabel("Base");
		l_base2 = new JLabel("Base");
		l_int1 = new JLabel ("Valor inteiro");
		l_int2 = new JLabel ("Valor convertido");
		l_frac1 = new JLabel ("Valor fracionario");
		l_frac2 = new JLabel ("");
		esp1 = new JLabel (" ");
		esp2 = new JLabel (" ");
		
		bOk = new JButton("Ok");
		
		
		

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container janela;
		janela = getContentPane();
		janela.add(pan2);
		pan2.add(pan1,BorderLayout.CENTER);
		pan2.add(bOk,BorderLayout.SOUTH);
		pan1.add(esp1);
		pan1.add(l_base1);
		pan1.add(l_int1);
		pan1.add(l_frac1);
		pan1.add(l_entrada);
		pan1.add(t_BaseEnt);
		pan1.add(t_IntEnt);
		//pan1.add(vir1);
		pan1.add(t_FracEnt);
		pan1.add(esp2);
		pan1.add(l_base2);
		pan1.add(l_int2);
		pan1.add(l_frac2);
		pan1.add(l_saida);
		pan1.add(t_BaseSaida);
		pan1.add(t_IntSai);
		//pan1.add(vir2);
		pan1.add(t_FracSai);
		bOk.addActionListener(this);
		
		t_IntSai.setEditable(false);
		t_FracSai.setEditable(false);
		
		
		setSize(800,180);
		setResizable(false);
	
	 
     }
	
	

	public static void main(String[]args){
		Main cd = new Main();
		cd.setVisible(true);
		
			
	}
	
	
	public void actionPerformed(ActionEvent e) {	
		
		if (e.getSource() == bOk){
			String numInt = t_IntEnt.getText(); //pega valores do campo inteiro e joga na variavel numInt
			String numFrac =t_FracEnt.getText();
			int baseInicial = Integer.parseInt(t_BaseEnt.getText());
			int baseConversao = Integer.parseInt(t_BaseSaida.getText());
			
				if (Converter(numInt,numFrac,baseInicial,baseConversao).equals("")){
					JOptionPane.showMessageDialog(null,"Erro, base(s) incorreta(s)!");
					
				}
					
				else{
					t_IntSai.setText(Converter(numInt,numFrac,baseInicial,baseConversao));
				}
		}	
			
		
	}
	
	public static String Converter(String numeroInt, String numeroFrac, int baseInicial, int baseConversao){
    	int expoente, contador;
		int digito = 0; //substring
		double digitoFrac = 0;
		double digitoFracTemp = 0;
		int numFracTemp = 0;
		int decimalInt = 0;
		double decimalFrac = 0;
		int decimalTemp = 0;
		String numeroConvertidoInt = "";
		String numeroConvertidoFrac = "";
		String numeroStringInt = "";
		String numeroStringFrac = "";
		
		//inicializando o expoente para converter numero inteiro para a base 10
		expoente = numeroInt.length() - 1;
		
		if (baseInicial < 2 || baseInicial > 20 || baseConversao < 2 || baseConversao > 20){
			return " ";
        }else{
			//convertendo parte inteira para decimal
			//contador - para identificar qual posição calcular da string, nao pode ultrapassar o tamanho da string digitada pelo usuário
			//expoente - calcular base 10 ----> PRECISA VER COMO FAZ PARTE FRACIONARIA
        	int contaOperacaoInt;
        	for (contador = 0, contaOperacaoInt = 0; contador < numeroInt.length(); contador++, expoente--, contaOperacaoInt++) {
        		digito = Integer.parseInt(numeroInt.substring(contador, (contador + 1)), 20);
        		if (digito > baseInicial) {
        			return " ";
        		}else {
        			decimalInt += digito * Math.pow(baseInicial, expoente);
        			if (contaOperacaoInt >=19){ // limite de 20 caracteres
        				contador = numeroInt.length();
        			}
        		}
            }
        	
        	//convertendo a base fracionaria
        	int contaOperacaoFrac;
        	expoente = -1;
        	for(contador = 0, contaOperacaoFrac = 0; contador < numeroFrac.length(); contador++, contaOperacaoFrac++){
        		digitoFracTemp = Integer.parseInt(numeroFrac.substring(contador, (contador + 1)), 20);
        		if (digitoFracTemp > baseInicial){
        			return " ";
        		}else{
        			digitoFrac = (double)digitoFracTemp;
        			decimalFrac += ((digitoFracTemp * Math.pow(baseInicial, expoente))*100)/100;
        			
        			if (contaOperacaoFrac >= 19){ // limite de 20 caracteres
        				contador = numeroFrac.length();
        			}
        		}
        		expoente--;
        	}
        }

        //converter parte inteira para a base pretendida
		int contaConversaoInt = 0;
        while (decimalInt >= baseConversao) {
        	numeroConvertidoInt += Letras(decimalInt % baseConversao);
            decimalInt /= baseConversao;
            contaConversaoInt++;
            if (contaConversaoInt >= 19){ // limite de 20 caracteres
            	decimalInt = baseConversao - 1;
            }
        }
        numeroConvertidoInt += Letras(decimalInt % baseConversao);
        
      //converter parte fracionaria para a base pretendida
        expoente = -1;
        int contaConversaoFrac = 0;
        while ((decimalFrac * 100) >= baseConversao) {
        	decimalFrac = (((decimalFrac - (int)decimalFrac)) * baseConversao);
    		decimalTemp = (int)decimalFrac;
            numeroConvertidoFrac += Letras(decimalTemp);
            contaConversaoFrac++;
            if (contaConversaoFrac >= 19){ // limite de 20 caracteres
            	decimalTemp = baseConversao - 1;
            }
        }

        //inverter a String da parte inteira
        for (contador = numeroConvertidoInt.length() - 1; contador >= 0; contador--) {
        	numeroStringInt += numeroConvertidoInt.charAt(contador);
        }
        
      //inverter a String da parte fracionaria
        for (contador = numeroConvertidoFrac.length() - 1; contador >= 0; contador--) {
        	numeroStringFrac += numeroConvertidoFrac.charAt(contador);
        }
        return numeroStringInt + "," + numeroStringFrac;
    }
	
	private static String Letras(int x) {
		switch (x) {
			case 10:
				return "A";
			case 11:
				return "B";
			case 12:
				return "C";
			case 13:
				return "D";
			case 14:
				return "E";
			case 15:
				return "F";
			case 16:
				return "G";
			case 17:
				return "H";
			case 18:
				return "I";
			case 19:
				return "J";
			default:
				return String.valueOf(x);
		}
	}
}
