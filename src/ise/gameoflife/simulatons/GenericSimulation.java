package ise.gameoflife.simulatons;

import ise.gameoflife.environment.Environment;
import ise.gameoflife.environment.EnvironmentDataModel;
import ise.gameoflife.models.Food;
import ise.gameoflife.models.NameGenerator;
import ise.gameoflife.participants.AbstractAgent;
import ise.gameoflife.participants.AbstractFreeAgentGroup;
import ise.gameoflife.participants.AbstractGroupAgent;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;
import presage.EventScriptManager;
import presage.Participant;
import presage.Plugin;
import presage.PluginManager;
import presage.PresageConfig;
import presage.ScriptedEvent;
import presage.events.CoreEvents.ActivateParticipant;
import static presage.configure.ConfigurationWriter.write;

/**
 *
 * @author Benedict
 */
abstract public class GenericSimulation
{

	private final HashMap<String, Food> foods = new HashMap<String, Food>();
	private final TreeMap<String, Participant> agents = new TreeMap<String, Participant>();
	private final ArrayList<Class<? extends AbstractGroupAgent>> groups = new ArrayList<Class<? extends AbstractGroupAgent>>();
	private final EventScriptManager ms = new EventScriptManager();
	private final PluginManager pm = new PluginManager();
	private String configPath;
	
	protected final String comment;

	@SuppressWarnings("OverridableMethodCallInConstructor")
	GenericSimulation(String comment, int iterations, long randomSeed, double foodConsumedPerAdvice)
	{
		PresageConfig presageConfig = new PresageConfig();
		this.comment = comment;
		presageConfig.setComment(comment);
		presageConfig.setIterations(iterations);
		presageConfig.setRandomSeed(randomSeed);
		presageConfig.setThreadDelay(1);
		presageConfig.setAutorun(false);
		presageConfig.setEnvironmentClass(Environment.class);

		// Path configuarations
		File path = new File(System.getProperty("user.dir"), "simulations/" + this.getClass().getSimpleName());
		configPath = path.getAbsolutePath();

		presageConfig.setPluginsConfigPath(configPath + "/plugins.xml");
		presageConfig.setEventscriptConfigPath(configPath + "/methods.xml");
		presageConfig.setParticipantsConfigPath(configPath + "/participants.xml");
		presageConfig.setEnvironmentConfigPath(configPath + "/environment.xml");

		EnvironmentDataModel dm = new EnvironmentDataModel(comment, foods, groups, foodConsumedPerAdvice);
		Environment e = new Environment(true, randomSeed, dm, chooseFreeAgentHandler());

		NameGenerator.setRandomiser(new Random(randomSeed));
		foods();
		agents();
		groups();
		plugins();
		events();

		write(configPath + "/sim.xml", presageConfig, agents, e, pm, ms);
	}

	abstract protected void plugins();
	abstract protected void foods();
	abstract protected void agents();
	abstract protected void groups();
	abstract protected void events();
	abstract protected Class<? extends AbstractFreeAgentGroup> chooseFreeAgentHandler();

	protected final void addFood(String name, double nutrition, int huntersRequired)
	{
		Food f = new Food(name, nutrition, huntersRequired);
		this.foods.put(f.getId().toString(), f);
	}

	protected final void addAgent(AbstractAgent a)
	{
		agents.put(a.getId(), a);
		ms.addPreEvent(new ScriptedEvent(-1, new ActivateParticipant(a.getId())));
	}

	protected final void addGroup(Class<? extends AbstractGroupAgent> g)
	{
		groups.add(g);
	}

	protected final void addPlugin(Plugin p)
	{
		pm.addPlugin(p);
	}

	protected final String getPath()
	{
		return configPath;
	}
}
