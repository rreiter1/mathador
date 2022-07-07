package fr.loria.generatorXapi;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.loria.mathador.Epreuve;
import gov.adlnet.xapi.model.Activity;
import gov.adlnet.xapi.model.ActivityDefinition;
import gov.adlnet.xapi.model.Agent;
import gov.adlnet.xapi.model.Context;
import gov.adlnet.xapi.model.Result;
import gov.adlnet.xapi.model.Score;
import gov.adlnet.xapi.model.Statement;
import gov.adlnet.xapi.model.Verb;

/**
 * Class permettant de générer un statement, 
 * une fonction = un verb
 * @author romain
 */
public class GenStatement {
	private Agent agent;
	private Verb verb;
	private Activity Object; 
	private Gson gson;
	private Epreuve epreuve;
	private String IRI = "http://domaine.com/";
	private int coupMathador = 18;
	private int scoreCalcul = 0;
	
	/**
	 * Constructeur, initialise les variables utilisées par toutes les autres fonctions
	 * @param e		//correspond a l'Epreuve qui va être traitée
	 */
	public GenStatement(Epreuve e)
	{
		this.epreuve = e;
		this.gson = new Gson();	
		//initialization of the profile for all statements
		this.agent = new Agent(null, "mailto:" + this.epreuve.getIdProfile() + "@domaine.com");
	}	
	
	/**
	 * Créer le verb started (devenue launched)
	 * @return Statement
	 */
	public Statement Started()
	{
		//initialization of local variables : 
		//initialization
		this.verb = new Verb();
		Context context = new Context();
		//for context extensions
		HashMap<String, JsonElement> extensionsContext = new HashMap<String, JsonElement>();
		//for the verb display
		HashMap<String, String> displayVerb = new HashMap<String, String>();

		//definition of the Verb	
		displayVerb.put("en-US","launched");
		this.verb.setId("http://adlnet.gov/expapi/verbs/launched");
		this.verb.setDisplay(displayVerb);
		
		//definition of the Object
		this.Object = new Activity(IRI + this.epreuve.getIDpartyGame());
		
		//definition of the Context extension
		JsonElement idAppli = gson.fromJson("" + this.epreuve.getIdApplication(), JsonElement.class);
		extensionsContext.put(IRI + "extentions/" + "idApplication", idAppli);
		if(this.epreuve.getIdApplication().equals("29f6f4d0cb77aa4f951a1ee15c521bb7"))
		{
			JsonElement network = gson.fromJson("" + this.epreuve.getNetwork(), JsonElement.class);
			extensionsContext.put(IRI + "extentions/" + "gameType", network);
		}
		context.setExtensions(extensionsContext);
		
		//creation of the statement		
		Statement statement = new Statement(this.agent, this.verb, this.Object);
		statement.setTimestamp(this.epreuve.getStarted().toString());
		statement.setContext(context);
		
		return statement;	
	}
	
	/**
	 * Créer le verb selectOperator (devenue selected)
	 * @return Statement
	 */
	public Statement selectOperator(int numCalcul)
	{
		//see if this is the first calculation (we won't have to do it again for the next calculations because they will be identical)
		if(numCalcul==1)
		{
			//initialization of variables
			//for the extension of the object
			HashMap<String, JsonElement> extensionObject = new HashMap<String, JsonElement>();
			//for the verb display
			HashMap<String, String> displayVerb = new HashMap<String, String>();
			//to add the extension of the object to the object
			ActivityDefinition AD = new ActivityDefinition();
			
			//defined the new Verb
			displayVerb.put("en-US","selected");
			this.verb = new Verb("http://id.tincanapi.com/verb/selected");
			this.verb.setDisplay(displayVerb);
			//defined the new Object
			this.Object = new Activity(IRI + this.epreuve.getIdDrawing());
			JsonElement IdpartyGame = gson.fromJson("" + this.epreuve.getIDpartyGame(), JsonElement.class);
			extensionObject.put(IRI + "extentions/" + "IdpartyGame", IdpartyGame);
			AD.setExtensions(extensionObject);			
			this.Object.setDefinition(AD);
		}
		//initialization of variables :
		//for the extension of the result
		JsonObject extensionsResult = new JsonObject();
		//initialization of the result
		Result result = new Result();
		//Score initialization
		Score score = new Score();
		//initialization of the Statement
		Statement statement = new Statement(agent, verb, Object);
		
		//initialization of variables for the result
		JsonElement numb1 = null;
		JsonElement operator1 = null;
		JsonElement numb2 = null;
		//look at numCalcul
		if(numCalcul==1)
		{
			//retrieve the first number selected for the first calculation
			numb1 = gson.fromJson("" + this.epreuve.getNumb1(), JsonElement.class);	
			//retrieve the operator selected for the first calculation
			operator1 = gson.fromJson("\""+ this.epreuve.getOperator1()+"\"", JsonElement.class);
			//retrieve the second number selected for the first calculation
			numb2 = gson.fromJson("" + this.epreuve.getNumb2(), JsonElement.class);
			//set the Timestamp of the first calculation
			statement.setTimestamp(this.epreuve.getValidate_calcul1().toString());
			//set the score received
			this.scoreCalcul = this.epreuve.getScoreCalcult(1);
		}else if(numCalcul==2)
		{
			//same but for the second calculation
			numb1 = gson.fromJson("" + this.epreuve.getNumb3(), JsonElement.class);			
			operator1 = gson.fromJson("\""+ this.epreuve.getOperator2()+"\"", JsonElement.class);			
			numb2 = gson.fromJson("" + this.epreuve.getNumb4(), JsonElement.class);	
			statement.setTimestamp(this.epreuve.getValidate_calcul2().toString());
			this.scoreCalcul = this.epreuve.getScoreCalcult(2);
		}
		else if (numCalcul==3)
		{
			//same but for the third calculation
			numb1 = gson.fromJson("" + this.epreuve.getNumb5(), JsonElement.class);			
			operator1 = gson.fromJson("\""+ this.epreuve.getOperator3()+"\"", JsonElement.class);			
			numb2 = gson.fromJson("" + this.epreuve.getNumb6(), JsonElement.class);		
			statement.setTimestamp(this.epreuve.getValidate_calcul3().toString());
			this.scoreCalcul = this.epreuve.getScoreCalcult(3);
		}else if(numCalcul==4)
		{
			//same but for the last calculation
			numb1 = gson.fromJson("" + this.epreuve.getNumb7(), JsonElement.class);			
			operator1 = gson.fromJson("\""+ this.epreuve.getOperator4()+"\"", JsonElement.class);			
			numb2 = gson.fromJson("" + this.epreuve.getNumb8(), JsonElement.class);	
			statement.setTimestamp(this.epreuve.getValidate_calcul4().toString());
			this.scoreCalcul = this.epreuve.getScoreCalcult(4);
		}
		//adds calculation data in the Result extension
		extensionsResult.add(IRI + "extentions/" + "number1", numb1);
		extensionsResult.add(IRI + "extentions/" + "operator", operator1);
		extensionsResult.add(IRI + "extentions/" + "number2", numb2);

		//set score obtained
		score.setRaw(this.scoreCalcul);
		//set MIN-MAX
		score.setMin(1);
		score.setMax(3);
		//set the score data in the result
		result.setScore(score);
		//adds the result extensions to the result
		result.setExtensions(extensionsResult);
		//adds the result to the statement
		statement.setResult(result);
		
		return statement;
	}
	
	/**
	 * Créer le verb validated (devenue completed)
	 * @return Statement
	 */
	public Statement validated()
	{
		//initialization of variables
		//verb display
		HashMap<String, String> displayVerb = new HashMap<String, String>();
		//for the result / and the score
		Result result = new Result();
		Score score = new Score();
		//for the context
		Context context = new Context();
		//the extension of the context
		HashMap<String, JsonElement> extensionsContext = new HashMap<String, JsonElement>();
		
		//defined the new verb
		this.verb = new Verb("http://adlnet.gov/expapi/verbs/completed");
		displayVerb.put("en-US","completed");
		this.verb.setDisplay(displayVerb);
		
		//defined the score + the Min-Max
		score.setRaw(this.epreuve.getScore());
		score.setMin(5);
		score.setMax(this.coupMathador);
		result.setScore(score);
		
		//see if it is the chrono mode or not
		if(!this.epreuve.getIdApplication().equals("29f6f4d0cb77aa4f951a1ee15c521bb7"))
		{ //if it's not the chrono mode then we add in the context extension:
			//the level of the floor
			JsonElement level = gson.fromJson("" + this.epreuve.getLevel(), JsonElement.class);
			extensionsContext.put(IRI + "extentions/" + "level", level);
			//its position in the level
			JsonElement position = gson.fromJson("" + this.epreuve.getPosition(), JsonElement.class);
			extensionsContext.put(IRI + "extentions/" + "position", position);	
		}
		
		//indicates the difficulty in the context
		JsonElement difficulty = gson.fromJson("" + this.epreuve.getDifficulty(), JsonElement.class);
		extensionsContext.put("https://w3id.org/xapi.gblxapi/extensions/difficulty", difficulty);

		//indicates the number of times it has made a back
		JsonElement back = gson.fromJson("" + this.epreuve.getBack(), JsonElement.class);
		extensionsContext.put(IRI + "extentions/" + "back", back);
		//indicates the response time to the calculation
		JsonElement tempsReponse = gson.fromJson("" + this.epreuve.getTempsreponse(),JsonElement.class);
		extensionsContext.put(IRI + "extentions/" + "responseTime",tempsReponse);
		
		//adds context extension to context
		context.setExtensions(extensionsContext);
		
		//initialize the statement
		Statement statement = new Statement(agent, verb, Object);
		statement.setTimestamp(this.epreuve.getFinished().toString());
		statement.setContext(context);
		statement.setResult(result);
		
		return statement;
	}
	
	
	/**
	 * Créer le verb passed
	 * @return Statement
	 */
	public Statement passed()
	{
		HashMap<String, JsonElement> extensionsContext = new HashMap<String, JsonElement>();
		HashMap<String, JsonElement> extensionObject = new HashMap<String, JsonElement>();
		HashMap<String, String> displayVerb = new HashMap<String, String>();
		ActivityDefinition AD = new ActivityDefinition();
		
		this.Object = new Activity(IRI + this.epreuve.getIdDrawing());
		
		JsonElement IdpartyGame = gson.fromJson("" + this.epreuve.getIDpartyGame(), JsonElement.class);
		extensionObject.put(IRI + "extentions/" + "IdpartyGame", IdpartyGame);
		AD.setExtensions(extensionObject);			
		this.Object.setDefinition(AD);
		
		displayVerb.put("en-US","passed");
		this.verb = new Verb("http://id.tincanapi.com/verb/skipped");
		this.verb.setDisplay(displayVerb);
		Score score = new Score();
		Result result = new Result();
		Context context = new Context();
		score.setRaw(0);
		result.setScore(score);
		
		if(!this.epreuve.getIdApplication().equals("29f6f4d0cb77aa4f951a1ee15c521bb7"))
		{
			JsonElement level = gson.fromJson("" + this.epreuve.getLevel(), JsonElement.class);
			extensionsContext.put(IRI + "extentions/" + "level", level);
			JsonElement position = gson.fromJson("" + this.epreuve.getPosition(), JsonElement.class);
			extensionsContext.put(IRI + "extentions/" + "position", position);	
		}
		if(this.epreuve.getDifficulty()!=0)
		{
			JsonElement difficulty = gson.fromJson("" + this.epreuve.getDifficulty(), JsonElement.class);
			extensionsContext.put("https://w3id.org/xapi.gblxapi/extensions/difficulty", difficulty);
			
		}			
		
		context.setExtensions(extensionsContext);
		Statement statement = new Statement(agent, verb, Object);
		
		//the timestamp of finished is equal to that of Created so if for x or y reason there is no timestamp of finished we can take that of created
		if(this.epreuve.getFinished() != null)
			statement.setTimestamp(this.epreuve.getFinished().toString());
		else
			statement.setTimestamp(this.epreuve.getCreated().toString());
		statement.setResult(result);
		if(!extensionsContext.isEmpty())
			statement.setContext(context);
				
		return statement;
	}
	
	
	/**
	 * Créer le verb abandoned
	 * @return Statement
	 */
	public Statement abandoned()
	{
		HashMap<String, String> displayVerb = new HashMap<String, String>();
		displayVerb.put("en-US","abandoned");
		this.verb = new Verb("https://w3id.org/xapi/adl/verbs/abandoned");
		this.verb.setDisplay(displayVerb);
		this.Object = new Activity(IRI + this.epreuve.getIDpartyGame());
		Score score = new Score();
		Result result = new Result();
		score.setRaw(0);
		result.setScore(score);

		
		Statement statement = new Statement(agent, verb, Object);
		//the timestamp of finished is equal to that of Created so if for x or y reason there is no timestamp of finished we can take that of created
		if(this.epreuve.getFinished() != null)
			statement.setTimestamp(this.epreuve.getFinished().toString());
		else
			statement.setTimestamp(this.epreuve.getCreated().toString());
		statement.setResult(result);

		return statement;
	}
	
	
}
