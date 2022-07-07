package fr.loria.mathador;

import java.util.ArrayList;
import java.util.Date;

/**
 * Class de l'Object Epreuve
 * @author Romain
 */

public class Epreuve {
	/* Variables  CSV */
	private String idProfile;
	private String idApplication;
	private String deviceToken;
	private Date created;
	private String remaningTime;
	private String IDpartyGame;
	private int abandoned;
	private int network;
	private int level;
	private int idDrawing;
	private int result;
	private int past;
	private int back;
	private int win;
	private int score;
	private Date validate_calcul1;
	private int numb1;
	private String operator1;
	private int numb2;
	private Date validate_calcul2;
	private int numb3;
	private String operator2;
	private int numb4;
	private Date validate_calcul3;
	private int numb5;
	private String operator3;
	private int numb6;
	private Date validate_calcul4;
	private int numb7;
	private String operator4;
	private int numb8;
	private int position;
	private int Correct;
	private Date started;
	private Date finished;
	private ArrayList<Integer> drawNumber; //A.y, B.y, C.y, D.y, E.y 
	private int difficulty;
	private int result_ligne_1;
	private int result_ligne_2;
	private int result_ligne_3;
	private int result_ligne_4;
	private int nb1_est_nb_outil;
	private int nb2_est_nb_outil;
	private int nb3_est_nb_outil;
	private int nb4_est_nb_outil;
	private int nb5_est_nb_outil;
	private int nb6_est_nb_outil;
	private int nb7_est_nb_outil;
	private int nb8_est_nb_outil;
	private int total_nb_outils_utilises;	

	/* Variables calculé */
	private long tempsreponse;
	
	
	/* Getter & Setter */
	public String getIdApplication() {
		return idApplication;
	}
	public void setIdApplication(String idApplication) {
		this.idApplication = idApplication;
	}
	public String getIdProfile() {
		return idProfile;
	}
	public void setIdProfile(String idProfile) {
		this.idProfile = idProfile;
	}
	public Date getCreated()
	{
		return this.created;
	}
	public void setCreated(Date created)
	{
		this.created = created;
	}
	public String getIDpartyGame() {
		return IDpartyGame;
	}
	public void setIDpartyGame(String iDpartyGame) {
		IDpartyGame = iDpartyGame;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public ArrayList<Integer> getDrawNumber()
	{
		return this.drawNumber;
	}
	public void setDrawNumber(ArrayList<Integer> drawNumbers) {
			this.drawNumber = drawNumbers;		
	}
	public void addDrawNumber(int drawNumber){
		if(this.drawNumber==null)
			this.drawNumber = new ArrayList<Integer>();
		this.drawNumber.add(drawNumber);
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public Date getStarted() {
		return started;
	}
	public void setStarted(Date started) {
		this.started = started;
	}
	public Date getValidate_calcul1() {
		return validate_calcul1;
	}
	public void setValidate_calcul1(Date validate_calcul1) {
		this.validate_calcul1 = validate_calcul1;
	}
	public Date getValidate_calcul2() {
		return validate_calcul2;
	}
	public void setValidate_calcul2(Date validate_calcul2) {
		this.validate_calcul2 = validate_calcul2;
	}
	public Date getValidate_calcul3() {
		return validate_calcul3;
	}
	public void setValidate_calcul3(Date validate_calcul3) {
		this.validate_calcul3 = validate_calcul3;
	}
	public Date getValidate_calcul4() {
		return validate_calcul4;
	}
	public void setValidate_calcul4(Date validate_calcul4) {
		this.validate_calcul4 = validate_calcul4;
	}
	public int getNumb1() {
		return numb1;
	}
	public void setNumb1(int numb1) {
		this.numb1 = numb1;
	}
	public String getOperator1() {
		return operator1;
	}
	public void setOperator1(String operator1) {
		this.operator1 = operator1;
	}
	public int getNumb2() {
		return numb2;
	}
	public void setNumb2(int numb2) {
		this.numb2 = numb2;
	}
	public int getNumb3() {
		return numb3;
	}
	public void setNumb3(int numb3) {
		this.numb3 = numb3;
	}
	public String getOperator2() {
		return operator2;
	}
	public void setOperator2(String operator2) {
		this.operator2 = operator2;
	}
	public int getNumb4() {
		return numb4;
	}
	public void setNumb4(int numb4) {
		this.numb4 = numb4;
	}
	public int getNumb5() {
		return numb5;
	}
	public void setNumb5(int numb5) {
		this.numb5 = numb5;
	}
	public String getOperator3() {
		return operator3;
	}
	public void setOperator3(String operator3) {
		this.operator3 = operator3;
	}
	public int getNumb6() {
		return numb6;
	}
	public void setNumb6(int numb6) {
		this.numb6 = numb6;
	}
	public int getNumb7() {
		return numb7;
	}
	public void setNumb7(int numb7) {
		this.numb7 = numb7;
	}
	public String getOperator4() {
		return operator4;
	}
	public void setOperator4(String operator4) {
		this.operator4 = operator4;
	}
	public int getNumb8() {
		return numb8;
	}
	public void setNumb8(int numb8) {
		this.numb8 = numb8;
	}
	public Date getFinished() {
		return finished;
	}
	public void setFinished(Date finished) {
		this.finished = finished;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public long getTempsreponse()
	{
		return this.tempsreponse;
	}	
	public void setTempsreponse(long tempsreponse) {
		this.tempsreponse = tempsreponse;
	}
	public String getRemaningTime() {
		return remaningTime;
	}
	public void setRemaningTime(String remaingTime) {
		this.remaningTime = remaingTime;
	}
	public int getBack() {
		return back;
	}
	public void setBack(int back) {
		this.back = back;
	}
	public int getIdDrawing() {
		return this.idDrawing;
	}
	public void setIdDrawing(int idDrawing) {
		this.idDrawing = idDrawing;
	}
	public String getDeviceToken() {
		return deviceToken;
	}
	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	public int getNetwork() {
		return network;
	}
	public void setNetwork(int network) {
		this.network = network;
	}
	public int getPast()
	{
		return this.past;
	}
	public void setPast(int past)
	{
		this.past = past;
	}
	public int getAbandoned()
	{
		return this.abandoned;
	}
	public void setAbandoned(int abandoned)
	{
		this.abandoned = abandoned;
	}	
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getCorrect() {
		return Correct;
	}
	public void setCorrect(int correct) {
		Correct = correct;
	}
	public int getResult_ligne_1() {
		return result_ligne_1;
	}
	public void setResult_ligne_1(String rl1) {
		if(!rl1.isBlank())
		{
			this.result_ligne_1 = Integer.parseInt(rl1);
		}		
	}
	public int getResult_ligne_2() {
		return result_ligne_2;
	}
	public void setResult_ligne_2(String rl2) {
		if(!rl2.isBlank())
		{
			this.result_ligne_2 = Integer.parseInt(rl2);
		}
	}
	public int getResult_ligne_3() {
		return result_ligne_3;
	}
	public void setResult_ligne_3(String rl3) {
		if(!rl3.isBlank())
		{
			this.result_ligne_3 = Integer.parseInt(rl3);
		}
	}
	public int getResult_ligne_4() {
		return result_ligne_4;
	}
	public void setResult_ligne_4(String rl4) {
		if(!rl4.isBlank())
		{
			this.result_ligne_4 = Integer.parseInt(rl4);
		}
	}
	public int getNb1_est_nb_outil() {
		return nb1_est_nb_outil;
	}
	public void setNb1_est_nb_outil(String n1nbO) {
		if(!n1nbO.isBlank())
			this.nb1_est_nb_outil = Integer.parseInt(n1nbO);
	}
	public int getNb2_est_nb_outil() {
		return nb2_est_nb_outil;
	}
	public void setNb2_est_nb_outil(String n2nbO) {
		if(!n2nbO.isBlank())
			this.nb2_est_nb_outil = Integer.parseInt(n2nbO);
	}
	public int getNb3_est_nb_outil() {
		return nb3_est_nb_outil;
	}
	public void setNb3_est_nb_outil(String n3nbO) {
		if(!n3nbO.isBlank())
			this.nb3_est_nb_outil = Integer.parseInt(n3nbO);
	}
	public int getNb4_est_nb_outil() {
		return nb4_est_nb_outil;
	}
	public void setNb4_est_nb_outil(String n4nbO) {
		if(!n4nbO.isBlank())
			this.nb4_est_nb_outil = Integer.parseInt(n4nbO);
	}
	public int getNb5_est_nb_outil() {
		return nb5_est_nb_outil;
	}
	public void setNb5_est_nb_outil(String n5nbO) {
		if(!n5nbO.isBlank())
			this.nb5_est_nb_outil = Integer.parseInt(n5nbO);
	}
	public int getNb6_est_nb_outil() {
		return nb6_est_nb_outil;
	}
	public void setNb6_est_nb_outil(String n6nbO) {
		if(!n6nbO.isBlank())
			this.nb6_est_nb_outil = Integer.parseInt(n6nbO);
	}
	public int getNb7_est_nb_outil() {
		return nb7_est_nb_outil;
	}
	public void setNb7_est_nb_outil(String n7nbO) {
		if(!n7nbO.isBlank())
			this.nb7_est_nb_outil = Integer.parseInt(n7nbO);
	}
	public int getNb8_est_nb_outil() {
		return nb8_est_nb_outil;
	}
	public void setNb8_est_nb_outil(String n8nbO) {
		if(!n8nbO.isBlank())
			this.nb8_est_nb_outil = Integer.parseInt(n8nbO);
	}
	public int getTotal_nb_outils_utilises() {
		return total_nb_outils_utilises;
	}
	public void setTotal_nb_outils_utilises(String ttnbO) {
		if(!ttnbO.isBlank())
			this.total_nb_outils_utilises = Integer.parseInt(ttnbO);
	}
	
	
	
	
	/* Fonction */
	/**
	 * fonction qui retourne un score y selon l'operateur de l'operation n°x
	 * @param numCalcul //l'operation x
	 * @return int 		//score y
	 */
	public int getScoreCalcult(int numCalcul)
	{
		if(numCalcul == 1)
		{
			switch(this.getOperator1())
			{
			case"+":
				return 1;
			case"x":
				return 1;
			case"-":
				return 2;
			case"/":
				return 3;
			default:
				return 0;
			}
		}
		else if(numCalcul == 2)
		{
			switch(this.getOperator2())
			{
			case"+":
				return 1;
			case"x":
				return 1;
			case"-":
				return 2;
			case"/":
				return 3;
			default:
				return 0;
			}
		}
		else if(numCalcul == 3)
		{
			switch(this.getOperator3())
			{
			case"+":
				return 1;
			case"x":
				return 1;
			case"-":
				return 2;
			case"/":
				return 3;
			default:
				return 0;
			}
		}
		else if(numCalcul == 4)
		{
			switch(this.getOperator4())
			{
			case"+":
				return 1;
			case"x":
				return 1;
			case"-":
				return 2;
			case"/":
				return 3;
			default:
				return 0;
			}
		}
		return 0;
	}
}