package fr.loria.mathador;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortEpreuve {
	
	public static List<Epreuve> sortByProfile(ArrayList<Epreuve> eps)
	{
		return eps.stream().sorted(Comparator.comparing(Epreuve::getIdProfile)).collect(Collectors.toList());		
	}
	
	public static List<Epreuve> sortByIdGame(ArrayList<Epreuve> eps)
	{
		return eps.stream().sorted(Comparator.comparing(Epreuve::getIDpartyGame)).collect(Collectors.toList());
	}
	
	public static List<Epreuve> sortByCreated(ArrayList<Epreuve> eps)
	{
		return eps.stream().sorted(Comparator.comparing(Epreuve::getCreated)).collect(Collectors.toList());
	}
	
	public static List<Epreuve> sortByLevelPosition(ArrayList<Epreuve> eps)
	{
		return eps.stream().sorted(Comparator.comparing(Epreuve::getPosition)).sorted(Comparator.comparing(Epreuve::getLevel)).collect(Collectors.toList());
	}
	
	public static List<Epreuve> sortBydifficulty(ArrayList<Epreuve> eps)
	{
		return eps.stream().sorted(Comparator.comparing(Epreuve::getDifficulty)).collect(Collectors.toList());
	}

}
