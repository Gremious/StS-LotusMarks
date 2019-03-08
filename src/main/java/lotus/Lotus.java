package lotus;

import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import lotus.cards.*;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;

@SpireInitializer
public class Lotus implements EditCardsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber, PostInitializeSubscriber  {
    private static final String MODNAME = "Lotus Mod";
    private static final String AUTHOR = "Gremious";
    private static final String DESCRIPTION = "Slay the Spire mod adding a new 'Lotus' archetype for the Silent!";
    
	public static final Logger logger = LogManager.getLogger(Lotus.class.getName());

	// =============== SUBSCRIBE AND INITIALIZE =================

    public Lotus() {
    	logger.info("Subscribe to things");
        BaseMod.subscribe(this);
        logger.info("Done subscribing");
    }

    public static void initialize(){
    	logger.info("Initialize");
    	
		@SuppressWarnings("unused")
    	Lotus lotusmod = new Lotus();
		
    	logger.info("Initialized");
    }
	// ============== /SUBSCRIBE AND INITIALIZE/ =================
    

    // ============== ADD CARD ART =================
    private static final String MOD_ASSETS_FOLDER = "images";
    
    public static final String makePath(String resource) {
    	return  MOD_ASSETS_FOLDER + "/" + resource;
    }
    

    public static final String BADGE_IMAGE = "powers/lotus_mark.png";
    //Power strings
    public static final String LOTUS_MARK_POWER = "powers/lotus_mark.png";
    public static final String SKILLED_POWER = "powers/skilled_power.png";
	
    //Card Strings
    public static final String LOTUS_CHAIN = "cards/lotus_chain.png";
    public static final String LOTUS_FIRE = "cards/season_of_fire.png";
    public static final String LOTUS_FULL_BLOOM = "cards/full_bloom.png";
    public static final String LOTUS_HUNT = "cards/on_the_hunt.png";
    public static final String LOTUS_MARK = "cards/mark.png";
    public static final String LOTUS_SKILLED = "cards/skilled_application.png";
    public static final String LOTUS_SLASH = "cards/lotus_slash.png";
    public static final String LOTUS_STRIKE = "cards/lotus_strike.png";
    public static final String LOTUS_TEST = "cards/placeholder.png";

    //Load Strings
    	//Load Powers
    public static Texture getLotusMarkTexture() { return new Texture(makePath(LOTUS_MARK_POWER));	}
    public static Texture getLotusSkilledPower() { return new Texture(makePath(SKILLED_POWER));	}	
    
    // ============== /ADD CARD ART/ ================
    
    
    // ================ ADD CARDS ===================
    @Override
    public void receiveEditCards() {
    	logger.info("Add Card");
    	
        BaseMod.addCard(new LotusChain());
        BaseMod.addCard(new LotusFire());
        BaseMod.addCard(new LotusFullBloom());
        BaseMod.addCard(new LotusHunt());
        BaseMod.addCard(new LotusMark());
        BaseMod.addCard(new LotusSkilled());
        BaseMod.addCard(new LotusSlash());
        BaseMod.addCard(new LotusStrike());
    	//BaseMod.addCard(new LotusTest());
        
        logger.info("Cards - added!");

    }
    
    

    
    
    @Override
	public void receiveEditStrings() {
		logger.info("begin editting strings");
		
        // CardStrings
		String cardStrings = Gdx.files.internal("localization/card-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(CardStrings.class, cardStrings);
        
		// PowerStrings

        	String powerStrings = Gdx.files.internal("localization/power-strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        	BaseMod.loadCustomStrings(PowerStrings.class, powerStrings);

		logger.info("done editting strings");
	}
    

  public void receiveEditKeywords() {
       	logger.info("Lotus Mark keyword");
        
       	String[] LotusMark= {"mark", "marks", "Mark", "Marks"};
        BaseMod.addKeyword(LotusMark, "Mark an enemy. Other cards interact with lotus marks and become more powerful.");
           
    }

  @Override
  public void receivePostInitialize() {
      // Mod badge
      Texture badgeTexture = new Texture(makePath(BADGE_IMAGE));
      ModPanel settingsPanel = new ModPanel();
      settingsPanel.addUIElement(new ModLabel("Lotus Mark doesn't have any settings!", 400.0f, 700.0f, settingsPanel, (me)->{}));
      BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
      
      Settings.isDailyRun = false;
      Settings.isTrial = false;
      Settings.isDemo = false;
  }
  
  
    // =============== /ADD CARDS/ ==================

}
