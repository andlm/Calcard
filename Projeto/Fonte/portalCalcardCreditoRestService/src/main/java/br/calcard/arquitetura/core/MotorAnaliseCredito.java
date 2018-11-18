package br.calcard.arquitetura.core;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MotorAnaliseCredito {
	
	public static String[] processar(Double renda,int dependente) {
		
		double[] arrLimiteCredito 			= new double[3];
		int categoria						= getCategoriaPorRenda(renda);
		boolean bFlReprovado				= false;
		String sTraducaoRegraLimiteArovado	= "";
		String sTraducaoRegraLimiteReprovado= "";
		String sStatusProcessamento			= "";
		String[] arrRetorno 				= new String[2];
		
		// Negado		= renda baixa
		if (categoria==0)		
		{
			bFlReprovado = true;
			sTraducaoRegraLimiteReprovado = "renda baixa";
		}
		else
		{
			//Se tem dependentes(s) e for menor ou igual a 2 e Renda até 2000, Saldo =0
			// Negado		= reprovado pela política de crédito
			if (dependente > 0 && categoria <= 2 ) //
			{
				bFlReprovado = true;
				if (renda <= 500.00 )
					sTraducaoRegraLimiteReprovado = "renda baixa";
				else
					sTraducaoRegraLimiteReprovado = "reprovado pela política de crédito";
			}
			else
			{
				//Renda menor 500 = renda baixa
				if (categoria <= 2 ) 
				{
					if (renda <= 500.00 )
					{
						bFlReprovado = true;
						sTraducaoRegraLimiteReprovado = "renda baixa";
					}
				}
			}
		}
		
		if (bFlReprovado ==false)
		{
			arrLimiteCredito = getRegraLimiteAposProcessamentoMotorCredito(renda,categoria,dependente);
			sTraducaoRegraLimiteArovado =getTraducaoRegraLimiteArovado(arrLimiteCredito);
		}
		
		sStatusProcessamento = (sTraducaoRegraLimiteReprovado==""? sTraducaoRegraLimiteArovado : sTraducaoRegraLimiteReprovado);
		
		arrRetorno[0]=(bFlReprovado)? "Negado":"Aprovado";
		arrRetorno[1]=sStatusProcessamento;
		
		return arrRetorno;
	}
	
	public static int[][] getRegraTabelaRendaPorCategoriaProcessamentoMotorCredito() {
		/* 
		 * ----------------------------------
		 * Regra (Renda)			Categoria 
		 * 0  	 < Renda <= 1000 	1 
		 * 1000  < Renda <= 2000 	2 
		 * 2000  < Renda <= 3000 	3
		 * Renda > 3000 			4
		 * ----------------------------------
		 */
		int[][] arrRegraCategoriaPorRenda	= { { 0, 1000, 1 }, { 1000, 2000, 2 }, { 2000, 3000, 3 }, { 3000, 9999999, 4 } };
		
		return arrRegraCategoriaPorRenda;
		
	}
	
	public static String getTraducaoRegraLimiteArovado(double[] arrLimiteCredito) {
		
		if (arrLimiteCredito[0]==arrLimiteCredito[1])
			return "superior 2000";
		else
			return "entre " + formatarNumeroDuasCasasDecimais(arrLimiteCredito[0]) + " - "+ formatarNumeroDuasCasasDecimais(arrLimiteCredito[1]);
	}
	
	public static String formatarNumeroDuasCasasDecimais(Double vlr) {
		if (vlr == null) {
			return "0,00";
		}
		return getDecimalFormatPtBr("###,###,##0.00").format(vlr);
	}
	
	private static DecimalFormat getDecimalFormatPtBr(String mascara) {
		DecimalFormatSymbols formatoPtBr = new DecimalFormatSymbols(new Locale("pt", "BR"));
		formatoPtBr.setDecimalSeparator(',');
		formatoPtBr.setGroupingSeparator('.');
		DecimalFormat df = new DecimalFormat(mascara, formatoPtBr);
		return df;
	}
	
	public static double[] getRegraLimiteAposProcessamentoMotorCredito(Double renda,int categoria,int dependente) {
		
		double[] arrLimiteCredito 			= new double[2];
		double dLimiteCreditoInicial 		= 0;
		double dLimiteCreditoFinal 			= 0;
		
		if (dependente > 0)
		{	
			// 2000 < Renda <= 3000 e renda superior 2000
			if (dependente < 2 && categoria == 3)
			{
				dLimiteCreditoInicial = 500;
				dLimiteCreditoFinal = 1000;		
			}
			
			// 2000 < Renda <= 3000 e renda superior 2000
			if (dependente >= 2 && categoria == 3)
			{
				dLimiteCreditoInicial = 100;
				dLimiteCreditoFinal = 500;	
			}
			
			// Renda > 3000 e renda superior 2000
			if (dependente <= 2 && categoria == 4)
			{
				dLimiteCreditoInicial = 2000;
				dLimiteCreditoFinal = 2000;		
			}
			
			
			// Renda > 3000 e renda superior 2000
			if (dependente > 2 && categoria == 4)
			{
				if (renda > 5000.00 )
				{
					dLimiteCreditoInicial = 1500;
					dLimiteCreditoFinal = 2000;		
				}	
				else
				{
					dLimiteCreditoInicial = 1000;
					dLimiteCreditoFinal = 1500;		
				}
			}
		}
		else
		{
			
			// Renda > 3000 
			if (categoria == 4)
			{
				dLimiteCreditoInicial = 2000;
				dLimiteCreditoFinal = 2000;	
			}
			
			// 2000 < Renda <= 3000
			if (categoria == 3)
			{
				dLimiteCreditoInicial = 500;
				dLimiteCreditoFinal = 1000;	
			}
			
			// 2000 < Renda <= 3000
			if (categoria == 2 || categoria == 1)
			{
				dLimiteCreditoInicial = 100;
				dLimiteCreditoFinal = 500;	
			}
		}
		
		arrLimiteCredito[0] = dLimiteCreditoInicial;
		arrLimiteCredito[1] = dLimiteCreditoFinal;

		return arrLimiteCredito;
	}
	
	public static int getCategoriaPorRenda(Double rendaRegraCategoria)
	{
		int categoria =0;
		int[][]  arrRegraCategoriaPorRenda  = getRegraTabelaRendaPorCategoriaProcessamentoMotorCredito();
		for (int i1 = 0; i1 < arrRegraCategoriaPorRenda.length; ++i1) {
			
			if (verificaCategoriaPorRenda(rendaRegraCategoria, Double.parseDouble(String.valueOf(arrRegraCategoriaPorRenda[i1][0])), 
						   Double.parseDouble(String.valueOf(arrRegraCategoriaPorRenda[i1][1]))))
			{
				categoria=arrRegraCategoriaPorRenda[i1][2];
				break;
			}
		}
		return categoria;
	}

	public static boolean verificaCategoriaPorRenda(Double rendaRegraCategoria, Double minValueInclusive, Double maxValueInclusive) {
	    return (	rendaRegraCategoria >= minValueInclusive 
	    		&& 	rendaRegraCategoria <= maxValueInclusive);
	}

}
