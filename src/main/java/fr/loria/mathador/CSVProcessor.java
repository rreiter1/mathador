package fr.loria.mathador;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
/**
 * Class permettant de parser le dataset de Mathador, CSV sous forme des colonnes : 
 * { idProfil, idApplication, deviceToken, created, remainingTime, IDpartyGame, abandoned, network, level, 
 * idDrawing, result, past, back, win, score, validated_calcul1, numb1, operator1, numb2, validated_calcul2, 
 * numb3, operator2, numb4, validated_calcul3, numb5, operator3, numb6, validated_calcul4, numb7, operator4, 
 * numb8, position, Correct, started, finished, A.y, B.y, C.y, D.y, E.y, difficulte, result_lign_1, result_lign_2, 
 * result_lign_3, result_lign_4, nb1_est_nb_outil, nb2_est_nb_outil, nb3_est_nb_outil, nb4_est_nb_outil, nb5_est_nb_outil, nb6_est_nb_outil,
 * nb7_est_nb_outil, nb8_est_nb_outil, total_nb_outils_utilises }
 * 
 * @author Romain
 */

public class CSVProcessor {
	private static java.util.logging.Logger log = java.util.logging.Logger.getLogger("CSVProcessor");
	/**
	 * À partir d'un nom fichier d'un CSV, renvoie un CSVParser correspond à l'ensemble des Epreuves situé dans le CSV
	 * @param filename	
	 * @return CSVParser
	 * @throws IOException
	 */
	private static CSVParser buildCSVParser(String filename) throws IOException {
		CSVParser res = null;
		Reader in;
		in = new InputStreamReader(new FileInputStream(filename), "utf8");
		CSVFormat csvf = CSVFormat.DEFAULT.withDelimiter(';').withFirstRecordAsHeader();
		res = new CSVParser(in, csvf);
		return res;
	}
	
	
	
	/**
	 * avec le nom d'un fichier d'un CSV, récupère les données avec la fonction CSVProcessor, 
	 * puis traite les données ligne par ligne pour les ranger dans la classe objet Epreuve 
	 * et l'ajoute dans la ArrayList pour la retourner a la fin.
	 * @param filename
	 * @return ArrayList<Epreuve>
	 * @throws IOException
	 */
	public static String idp2;
	public static ArrayList<Epreuve> parseCSV(String filename) throws IOException {
		int c = 0; // counter
		ArrayList<Epreuve> Epreuves = new ArrayList<Epreuve>();
		// Parsing du CSV
		CSVParser p = buildCSVParser(filename);
		for (CSVRecord r : p) {
			c++;
			try {
				//get all VARs in CSV
				String idp;
				//depending on how we converted the data it may not recognize idProfile
				try
				{
					idp = r.get("idProfil");	
				}catch(Exception e)
				{
					p.getHeaderNames().stream().forEach(headerN -> { if(headerN.toString().equals("﻿idProfil")) { idp2=r.get(headerN.toString());} });
					idp = idp2;
				}
				String ida = r.get("idApplication");
				String token = r.get("deviceToken");
				String creat = r.get("created");
				String rmgt = r.get("remainingTime");
				String idpg = r.get("IDpartyGame");				
				String aband = r.get("abandoned");
				String nt = r.get("network");
				String lvl = r.get("level");
				String idD = r.get("idDrawing");
				String resu = r.get("result");
				String past = r.get("past");
				String ba = r.get("back");
				String wi = r.get("win");
				String sc = r.get("score");
				String vc1 = r.get("validated_calcul1");
				String nb1 = r.get("numb1");
				String op1 = r.get("operator1");
				String nb2 = r.get("numb2");
				String vc2 = r.get("validated_calcul2");
				String nb3 = r.get("numb3");
				String op2 = r.get("operator2");
				String nb4 = r.get("numb4");
				String vc3 = r.get("validated_calcul3");
				String nb5 = r.get("numb5");
				String op3 = r.get("operator3");
				String nb6 = r.get("numb6");
				String vc4 = r.get("validated_calcul4");
				String nb7 = r.get("numb7");
				String op4 = r.get("operator4");
				String nb8 = r.get("numb8");
				String ps = r.get("position");
				String cr = r.get("Correct");
				String sta = r.get("started");
				String fin = r.get("finished");
				String Ay = r.get("A.y");
				String By = r.get("B.y");
				String Cy = r.get("C.y");
				String Dy = r.get("D.y");
				String Ey = r.get("E.y");
				String dif = r.get("difficulte");
				String rl1 = r.get("result_lign_1");
				String rl2 = r.get("result_lign_2");
				String rl3 = r.get("result_lign_3");
				String rl4 = r.get("result_lign_4");
				String n1nbO = r.get("nb1_est_nb_outil");
				String n2nbO = r.get("nb2_est_nb_outil");
				String n3nbO = r.get("nb3_est_nb_outil");
				String n4nbO = r.get("nb4_est_nb_outil");
				String n5nbO = r.get("nb5_est_nb_outil");
				String n6nbO = r.get("nb6_est_nb_outil");
				String n7nbO = r.get("nb7_est_nb_outil");
				String n8nbO = r.get("nb8_est_nb_outil");
				String ttnbO = r.get("total_nb_outils_utilises");
				
				long tprep;
			
				//put all VARs in object Epreuve
				if(!idpg.isBlank())
				{
					Epreuve element = new Epreuve();					
					DateFormat dfFR = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
					DateFormat dfEN = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					element.setIdProfile(idp);
					element.setIdApplication(ida);
					element.setDeviceToken(token);
					Date date;
					try {
						date = dfFR.parse(creat);	
					}catch(Exception e)
					{
						date = dfEN.parse(creat);
					}
					long time = date.getTime();
					element.setCreated(new Timestamp(time));
					
					if(!rmgt.isBlank())
					{
						element.setRemaningTime(rmgt);
					}
					element.setIDpartyGame(idpg);
					if(!aband.isBlank())
					{
						element.setAbandoned(Integer.parseInt(aband));	
					}
					if(!nt.isBlank())
						element.setNetwork(Integer.parseInt(nt));
					if(!lvl.isBlank())
					{
						element.setLevel(Integer.parseInt(lvl));	
					}
					if(!idD.isBlank())
					{
						element.setIdDrawing(Integer.parseInt(idD));
					}
					if(!resu.isBlank())
						element.setResult(Integer.parseInt(resu));
					if(!past.isBlank())
						element.setPast(Integer.parseInt(past));
					if(!ba.isBlank())
						element.setBack(Integer.parseInt(ba));
					if(!wi.isBlank())
						element.setWin(Integer.parseInt(wi));
					if(!sc.isBlank())
						element.setScore(Integer.parseInt(sc));
					//DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					if(!vc1.isBlank())
					{
						try {
							date = dfFR.parse(vc1);	
						}catch(Exception e)
						{
							date = dfEN.parse(vc1);
						}
				        time = date.getTime();
						element.setValidate_calcul1(new Timestamp(time));
						element.setNumb1(Integer.parseInt(nb1));
						element.setOperator1(op1);
						element.setNumb2(Integer.parseInt(nb2));
					}
					if(!vc2.isBlank())
					{
						try {
							date = dfFR.parse(vc2);	
						}catch(Exception e)
						{
							date = dfEN.parse(vc2);
						}
				        time = date.getTime();
						element.setValidate_calcul2(new Timestamp(time));
						element.setNumb3(Integer.parseInt(nb3));
						element.setOperator2(op2);
						element.setNumb4(Integer.parseInt(nb4));
					}
					if(!vc3.isBlank())
					{
						try {
							date = dfFR.parse(vc3);	
						}catch(Exception e)
						{
							date = dfEN.parse(vc3);
						}
				        time = date.getTime();
						element.setValidate_calcul3(new Timestamp(time));
						element.setNumb5(Integer.parseInt(nb5));
						element.setOperator3(op3);
						element.setNumb6(Integer.parseInt(nb6));
					}
					if(!vc4.isBlank())
					{
						try {
							date = dfFR.parse(vc4);	
						}catch(Exception e)
						{
							date = dfEN.parse(vc4);
						}
				        time = date.getTime();
						element.setValidate_calcul4(new Timestamp(time));
						element.setNumb7(Integer.parseInt(nb7));
						element.setOperator4(op4);
						element.setNumb8(Integer.parseInt(nb8));
					}
					if(!ps.isBlank())
					{
						element.setPosition(Integer.parseInt(ps));
					}
					if(!cr.isBlank())
						element.setCorrect(Integer.parseInt(cr));
					if(wi.equals("1"))
					{
						try {
							date = dfFR.parse(sta);	
						}catch(Exception e)
						{
							date = dfEN.parse(sta);
						}
				        time = date.getTime();
						element.setStarted(new Timestamp(time));
						try {
							date = dfFR.parse(fin);	
						}catch(Exception e)
						{
							date = dfEN.parse(fin);
						}
				        time = date.getTime();
						element.setFinished(new Timestamp(time));
						element.addDrawNumber(Integer.parseInt(Ay));
						element.addDrawNumber(Integer.parseInt(By));
						element.addDrawNumber(Integer.parseInt(Cy));
						element.addDrawNumber(Integer.parseInt(Dy));
						element.addDrawNumber(Integer.parseInt(Ey));
						element.setDifficulty(Integer.parseInt(dif));
						
						element.setResult_ligne_1(rl1);
						element.setResult_ligne_2(rl2);
						element.setResult_ligne_3(rl3);
						element.setResult_ligne_4(rl4);

						element.setNb1_est_nb_outil(n1nbO);
						element.setNb2_est_nb_outil(n2nbO);
						element.setNb3_est_nb_outil(n3nbO);
						element.setNb4_est_nb_outil(n4nbO);
						element.setNb5_est_nb_outil(n5nbO);
						element.setNb6_est_nb_outil(n6nbO);
						element.setNb7_est_nb_outil(n7nbO);
						element.setNb8_est_nb_outil(n8nbO);

						element.setTotal_nb_outils_utilises(ttnbO);

						
						Timestamp ts = new Timestamp(element.getStarted().getTime());
						Timestamp ts2 = new Timestamp(element.getFinished().getTime());
						tprep = (ts2.getTime()-ts.getTime())/1000;
						element.setTempsreponse(tprep);	
					}				
					Epreuves.add(element);
				}
			} catch (Exception exception) {
				System.out.println("ligne: " + c);
				System.out.println(exception.getMessage());
				System.out.println(r.toString());
				exception.printStackTrace();
			}
		}
		log.info("Nb items chargées : " + c);
		return Epreuves;
	}
}
