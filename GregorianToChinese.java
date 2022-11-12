package challenge;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class GregorianToChinese {
	
	/* Calendário Chinês
	   * 1º Ano: 12 * 29,53059 dias = 354,36708
	   * 2º Ano: 12 * 29,53059 dias = 354,36708
	   * 3º Ano: 13 * 29,53059 dias = 383,89767
	   * 4º Ano: 12 * 29,53059 dias = 354,36708
	   * 5º Ano: 12 * 29,53059 dias = 354,36708
	   * 6º Ano: 13 * 29,53059 dias = 383,89767
	                  .
	                  .
	                  .
	*/
	
	public static void main(String[] args) {
		// Data Base a partir da qual será calculada a diferença de números
		final LocalDate dataBase = LocalDate.of(1900, 1, 31);
		// Duração dos anos no calendário chinês
		final double anoNormal = 354.36708; // Ano Normal: 12 * 29,53059 = 354,36708
		final double anoBissexto = 383.89767; // Ano Bissexto: 13 * 25,53059 = 383,89767
		
		// Input de data do usuário
		LocalDate dataDigitada = lerDataNascimento();
		
		// Chamada do método para calcular a diferença de dias entre a data base e a data digitada
		long dif = entreDatas(dataBase, dataDigitada);
		
		// Chamada do método que irá calcular quantos anos se passaram no calendário chinês
		long numAnos = anosPassadosChines(dif);
		
		// Chamada do método que irá exibir o nome do ano chinês em que a pessoa nasceu
		anoChines(numAnos);
	}
	
	// Método para calcular a diferença de dias entre a data base e a data digitada
	public static long entreDatas(LocalDate dataDigitada, LocalDate dataBase) {
		return ChronoUnit.DAYS.between(dataDigitada, dataBase);
	}
	
	// Input de data do usuário
	public static LocalDate lerDataNascimento() {
		// Atributos
		Integer ano;
		Integer mes;
		Integer dia;
		
		// Instanciamento do objeto sc da classe Scanner
		Scanner sc = new Scanner(System.in);
		
		// Leitura do ano de nascimento
		System.out.print("Ano de Nascimento (Gregoriano): ");
		ano = sc.nextInt();
		// Leitura do mês de nascimento
		System.out.print("Mes de Nascimento (Gregoriano): ");
		mes = sc.nextInt();
		// Leitura do dia de nascimento
		System.out.print("dia de Nascimento (Gregoriano): ");
		dia = sc.nextInt();
		
		// Instanciamento da data dataDigitada da classe LocalDate
		LocalDate dataDigitada = LocalDate.of(ano, mes, dia);
		
		// Fechamento da instância sc da classe Scanner
		sc.close();
		// Retorno objeto LocalDate
		return dataDigitada;
	}
	
	// Método que irá calcular quantos anos se passaram no calendário chinês
	public static long anosPassadosChines(long dif) {
		long status; // Armazena o número ordinal do ano no calendário chinês a partir do ano da dataBase
		
		// quantidade de dias de uma bloco de padrão
		double bloco = 1092.63183;
		int divisaoInt; // Armazena a quantidade de blocos que cabem dentro da diferença
		double restoDif; // Armazena a quantidade de dias que sobraram após retirar o valor (número de blocos * bloco)
		/*
		 * Por exemplo, para o caso de uma dif igual a 1253
		 * o restoDiv para esta dif será restoDiv == 1253 - (1092.63183 * divisaoInt) == 160.36817
		 * ou seja, estará no ano imediatamente após o último daquele bloco de 3 anos.
		 * No caso do exemplo, estaria no ano do Dragão
		*/
		
		/* if case para checar se a diferença é maior que o bloco de padrão repetido
		   * 1º Ano: 12 * 29,53059 dias = 354,36708
	       * 2º Ano: 12 * 29,53059 dias = 354,36708
	       * 3º Ano: 13 * 29,53059 dias = 383,89767
		*/
		if (dif>bloco || dif==bloco) {
			divisaoInt = (int) (dif/bloco);
			restoDif = dif - (bloco*divisaoInt);
			status = 3*divisaoInt + menorQueBloco(restoDif);
		} else {
			status = menorQueBloco(dif);
		}
		
		return status;
	}
	
	// Método que irá ser executado caso a dif for menor que o bloco e retornará em qual dos anos a dif se encaixa
	public static int menorQueBloco(double dif) {
		int dentroDoAno; 
		/* 
		 * Caso a dif seja menor que 354.36708, a data está dentro do 1 ano (dentroDoAno == 1)
		 * Caso a dif seja menor que 708.73416 e maior que 354.36708, a data está dentro do 2 ano (dentroDoAno == 2)
		 * Caso a dif seja menor que 1092.63183 e maior que 708.73416, a data está dentro do 3 ano (dentroDoAno == 3)
		*/
		
		if (dif<354.36708) {
			dentroDoAno = 1;
		} else if (dif<708.73416 && dif>354.36708) {
			dentroDoAno = 2;
		} else {
			dentroDoAno = 3;
		}
		
		return dentroDoAno;
	}
	
	// Método que irá exibir o nome do ano chinês em que a pessoa nasceu
	public static void anoChines(long numAnos) {
		switch ((int) numAnos) {
			case 1:
				System.out.println("Ano do Rato!");
				break;
			case 2:
				System.out.println("Ano do Boi!");
				break;
			case 3:
				System.out.println("Ano do Tigre!");
				break;
			case 4:
				System.out.println("Ano do Coelho!");
				break;
			case 5:
				System.out.println("Ano do Dragao!");
				break;
			case 6:
				System.out.println("Ano da Serpente!");
				break;
			case 7:
				System.out.println("Ano do Cavalo!");
				break;
			case 8:
				System.out.println("Ano da Cabra!");
				break;
			case 9:
				System.out.println("Ano do Macaco!");
				break;
			case 10:
				System.out.println("Ano do Galo!");
				break;
			case 11:
				System.out.println("Ano do Cão!");
				break;
			case 12:
				System.out.println("Ano do Porco!");
				break;
			default:
				anoChines(numAnos - ((numAnos/12))*12);
				break;
		}
	}
}

