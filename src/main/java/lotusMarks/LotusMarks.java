package lotusMarks;

import archetypeAPI.util.IDCheckDontTouchPls;
import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.files.FileHandle;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.localization.*;
import lotusMarks.cards.*;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

@SpireInitializer
public class LotusMarks implements
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        PostInitializeSubscriber  {
    public static final Logger logger = LogManager.getLogger(LotusMarks.class.getName());

    private static final String MODNAME = "Lotus Marks Mod";
    private static final String AUTHOR = "Gremious";
    private static final String DESCRIPTION = "Slay the Spire mod adding a new 'LotusMarks' archetype for the Silent!";

    private static String modID;

	// =============== SUBSCRIBE AND INITIALIZE =================

    public LotusMarks() {
    	logger.info("Subscribe to things");
        BaseMod.subscribe(this);
        setModID("lotusMarks");
        logger.info("Done subscribing");
    }

    public static void initialize(){
    	logger.info("Initialize");
    	
		@SuppressWarnings("unused")
        LotusMarks lotusMarks = new LotusMarks();
		
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

    // ================ LOAD THE TEXT ===================

    @Override
    public void receiveEditStrings() {
        logger.info("Beginning to edit strings");

        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/LotusMarks-Card-Strings.json");

        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/LotusMarks-Power-Strings.json");


        logger.info("Done edittting strings");
    }

    // ================ /LOAD THE TEXT/ ===================

    // ================ LOAD THE KEYWORDS ===================

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/LotusMarks-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                //  getModID().toLowerCase() makes your keyword mod specific (it won't show up in other cards that use that word)
            }
        }
    }

    // ================ /LOAD THE KEYWORDS/ ===================


  @Override
  public void receivePostInitialize() {
      // Mod badge
      Texture badgeTexture = new Texture(makePath(BADGE_IMAGE));
      ModPanel settingsPanel = new ModPanel();
      settingsPanel.addUIElement(new ModLabel("LotusMarks Mark doesn't have any settings!", 400.0f, 700.0f, settingsPanel, (me)->{}));
      BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
  }
  
  
    // =============== /ADD CARDS/ ==================

    // ====== NO EDIT AREA ======
    // DON'T TOUCH THIS STUFF. IT IS HERE FOR STANDARDIZATION BETWEEN MODS AND TO ENSURE GOOD CODE PRACTICES.
    // IF YOU MODIFY THIS I WILL HUNT YOU DOWN AND DOWNVOTE YOUR MOD ON WORKSHOP

    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson(); // EY DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStrings.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files
        InputStream in = LotusMarks.class.getResourceAsStream("/IDCheckStrings.json"); // DON'T EDIT THIS ETHER
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT

        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T
        } else { // NO EDIT AREA
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE
        } // NO
    } // NO

    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH

    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NNOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStrings.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = LotusMarks.class.getResourceAsStream("/IDCheckStrings.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT

        String packageName = LotusMarks.class.getPackage().getName(); // STILL NOT EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO
    // ====== YOU CAN EDIT AGAIN ======
}
