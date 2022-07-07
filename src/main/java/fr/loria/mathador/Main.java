package fr.loria.mathador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.loria.generatorXapi.GenActivityProfile;
import fr.loria.generatorXapi.GenStatement;
import gov.adlnet.xapi.model.Statement;

public class Main {
	public static void main(String[] args) {
		int nsplit = 0;
		final java.util.logging.Logger log = java.util.logging.Logger.getLogger("Main");
		// parameters
		String ifilename = null;
		String odirname = null;
		// options
		Options options = new Options();
		Option input = new Option("i", "input", true, "nom du fichier .csv");
		input.setRequired(true);
		options.addOption(input);
		Option output = new Option("o", "output", true, "nom du dossier résultat");
		options.addOption(output);
		Option number = new Option("n", "number", true, "seuil de partitionnement");
		options.addOption(number);
		// parse the command line
		CommandLineParser parser = new DefaultParser();
		CommandLine line = null;
		try {
			line = parser.parse(options, args);
		} catch (ParseException exp) {
			log.severe("Erreur dans la ligne de commande");
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("arguments", options);
			System.exit(1);
		}
		// get params 
		if (line.hasOption("i")) {
			ifilename = line.getOptionValue("i");
		}
		if (line.hasOption("o")) {
			odirname = line.getOptionValue("o");
		}
		if (line.hasOption("n")) {
			try {
				nsplit = Integer.parseInt(line.getOptionValue("n"));
			} catch (NumberFormatException e) {
				log.warning("Erreur du seuil de partionnement");
			}
		}
		// start processing
		log.info("Starting...");
		List<Statement> allStatement = new ArrayList<Statement>();
		List<gov.adlnet.xapi.model.ActivityProfile> allActivityProfile = new ArrayList<gov.adlnet.xapi.model.ActivityProfile>();
		ArrayList<Epreuve> ListeEpreuves = new ArrayList<Epreuve>();
		try {
			ListeEpreuves = CSVProcessor.parseCSV(ifilename);
			log.info("#rows:" + ListeEpreuves.size());
			log.info("#sort all data");
			ListeEpreuves = (ArrayList<Epreuve>) SortEpreuve.sortByProfile((ArrayList<Epreuve>) 
												SortEpreuve.sortByIdGame((ArrayList<Epreuve>) 
												SortEpreuve.sortByLevelPosition((ArrayList<Epreuve>) 
												SortEpreuve.sortBydifficulty(ListeEpreuves))));
			log.info("#conversion of data to xAPI:");
			Epreuve previous= null;
			for (Epreuve e : ListeEpreuves) {
				
				GenStatement gt = new GenStatement(e);
				GenActivityProfile gap = new GenActivityProfile(e);
				
				//check that the game has not already started 
				if(previous==null || (!previous.getIdProfile().equals(e.getIdProfile()) || !previous.getIDpartyGame().equals(e.getIDpartyGame())))
				{
					previous = e;
					//started (start the party)
					allStatement.add(gt.Started());
				}			
				//selectOperator (start the test and selects 2 numbers and 1 operator)
				if(e.getValidate_calcul1() != null)
				{
					boolean test = allActivityProfile.stream().anyMatch(ap -> ap.getActivityId().equals(""+e.getIdDrawing()));
					if(!test)
					{
						allActivityProfile.add(gap.generation());
					}
					allStatement.add(gt.selectOperator(1));
					if(e.getValidate_calcul2() != null)
					{
						allStatement.add(gt.selectOperator(2));
						if(e.getValidate_calcul3() != null)
						{
							allStatement.add(gt.selectOperator(3));
							if(e.getValidate_calcul4() != null)
								allStatement.add(gt.selectOperator(4));
						}
					}
				}
				//passed (if he passed the test)
				if(e.getPast()==1)
				{
					allStatement.add(gt.passed());
				}
				//abandoned (if he abandons the race (only in chrono mode))
				else if(e.getAbandoned() == 1)
				{
					allStatement.add(gt.abandoned());
				}
				//validated (if he passes the test with success)
				else
					allStatement.add(gt.validated());
				
			}
			log.info("#statements:" + allStatement.size());
			log.info("#activity  profile :" + allActivityProfile.size());
		} catch (IOException e) {
			log.warning("Pb de lecture :" + ifilename);
			e.printStackTrace();
		}
		//save xapi/json
		String basename = null;
		if (odirname != null) {
			basename = FilenameUtils.getBaseName(ifilename);
		} 
		log.info("#generation des fichier statement");
		try {
			int counter = 0;
			int prefix = 0;
			Writer out = null;
			if (odirname == null) {
				out = new BufferedWriter(new OutputStreamWriter(System.out));
			} else {
				out = new FileWriter(odirname +"/"+ prefix + "-" + basename + "_St.json");
				prefix++;
			}
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();
			out.write("[");
			for (Statement statement : allStatement) {
				if (counter > nsplit) {
					counter = 0;
					out.write("]");
					out.close();
					if (odirname == null) {
						out = new BufferedWriter(new OutputStreamWriter(System.out));
					} else {
						out = new FileWriter(odirname +"/"+ prefix + "-" + basename + "_St.json");
						prefix++;
					}
					out.write("[");
				}
				if (counter != 0) {
					out.write(",");
				}
				out.write(gson.toJson(statement));
				counter++;
			}
			out.write("]");
			out.close();
		} catch (IOException e) {
			log.warning("Erreur d'écriture : " + odirname);
			e.printStackTrace();
		}
		log.info("#generation des fichier ActivityProfile");
		try {
			int counter = 0;
			int prefix = 0;
			Writer out = null;
			if (odirname == null) {
				out = new BufferedWriter(new OutputStreamWriter(System.out));
			} else {
				out = new FileWriter(odirname +"/"+ prefix + "-" + basename + "_AP.json");
				prefix++;
			}
			GsonBuilder builder = new GsonBuilder();
			builder.setPrettyPrinting();
			Gson gson = builder.create();
			out.write("[");
			for (gov.adlnet.xapi.model.ActivityProfile activityP : allActivityProfile) {
				if (counter > nsplit) {
					counter = 0;
					out.write("]");
					out.close();
					if (odirname == null) {
						out = new BufferedWriter(new OutputStreamWriter(System.out));
					} else {
						out = new FileWriter(odirname +"/"+ prefix + "-" + basename + "_AP.json");
						prefix++;
					}
					out.write("[");
				}
				if (counter != 0) {
					out.write(",");
				}
				out.write(gson.toJson(activityP));
				counter++;
			}
			out.write("]");
			out.close();
		} catch (IOException e) {
			log.warning("Erreur d'écriture : " + odirname);
			e.printStackTrace();
		}
		// start processing
		log.info("Terminated");
	}
}
