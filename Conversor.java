public class Conversor {
	private String numBinario = "";
	
	public Conversor(){ //construtor vazio
	}
	
	public static String Converter(String numeroInt, String numeroFrac, int baseInicial, int baseConversao){
    	int expoente, contador;
		int digito = 0; //substring
		double digitoFrac = 0;
		int digitoFracTemp = 0;
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
        	for (contador = 0; contador < numeroInt.length(); contador++, expoente--) {
        		digito = Integer.parseInt(numeroInt.substring(contador, (contador + 1)), 20);
        		if (digito > baseInicial) {
        			return " ";
        		}else {
        			decimalInt += digito * Math.pow(baseInicial, expoente);
        		}
            }
        	
        	//convertendo a base fracionaria
        	int contaOperacao;
        	expoente = -1;
        	for(contador = 0, contaOperacao = 0; contador < numeroFrac.length(); contador++, contaOperacao++){
        		digitoFracTemp = Integer.parseInt(numeroFrac.substring(contador, (contador + 1)), 20);
        		if (digitoFracTemp > baseInicial){
        			return " ";
        		}else{
        			digitoFrac = (double)digitoFracTemp;
        			decimalFrac += ((digitoFracTemp * Math.pow(baseInicial, expoente))*100)/100;
        			
        			if (contaOperacao >= 19){
        				contador = numeroFrac.length();
        			}
        		}
        		expoente--;
        	}
        }

        //converter parte inteira para a base pretendida
        while (decimalInt >= baseConversao) {
        	numeroConvertidoInt += Letras(decimalInt % baseConversao);
            decimalInt /= baseConversao;
        }
        numeroConvertidoInt += Letras(decimalInt % baseConversao);
        
      //converter parte fracionaria para a base pretendida
        decimalTemp = (int)(decimalFrac * 100);
        int contaConversao = 0;
        while (decimalTemp >= baseConversao) {
        	numeroConvertidoFrac += Letras(decimalTemp % baseConversao);
            decimalInt /= baseConversao;
            contaConversao++;
            if (contaConversao >= 19){
            	decimalTemp = baseConversao - 1;
            }
        }
        numeroConvertidoFrac += Letras(decimalInt % baseConversao);

        //inverter a String da parte inteira
        for (contador = numeroConvertidoInt.length() - 1; contador >= 0; contador--) {
        	numeroStringInt += numeroConvertidoInt.charAt(contador);
        }
        
      //inverter a String da parte fracionaria
        for (contador = numeroConvertidoFrac.length() - 1; contador >= 0; contador--) {
        	numeroStringFrac += numeroConvertidoFrac.charAt(contador);
        }
        return numeroStringInt + "," + numeroConvertidoFrac;
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
