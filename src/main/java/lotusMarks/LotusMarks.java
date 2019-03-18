package lotusMarks;

import archetypeAPI.archetypes.abstractArchetype;
import basemod.BaseMod;
import basemod.ModLabel;
import basemod.ModPanel;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import lotusMarks.archetypes.LotusMarchSelectionCard;
import lotusMarks.cards.*;
import lotusMarks.util.IDCheckDontTouchPls;
import lotusMarks.util.TextureLoader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static lotusMarks.archetypes.LotusMarkSilent.lotusMarkSilentArchetypeFiles;

@SpireInitializer
public class LotusMarks implements
        EditCardsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        PostInitializeSubscriber {
    public static final Logger logger = LogManager.getLogger(LotusMarks.class.getName());

    private static final String MODNAME = "Lotus Marks Mod";
    private static final String AUTHOR = "Gremious";
    private static final String DESCRIPTION = "Slay the Spire mod adding a new 'LotusMarks' archetype for the Silent!";
    public static final String BADGE_IMAGE = "lotusMarksResources/images/Badge.png";

    private static String modID;

    // =============== SUBSCRIBE AND INITIALIZE =================

    public LotusMarks() {
        logger.info("Subscribe to things");
        BaseMod.subscribe(this);
        setModID("lotusMarks");
        logger.info("Done subscribing");
    }

    public static void initialize() {
        logger.info("Initialize");

        @SuppressWarnings("unused")
        LotusMarks lotusMarks = new LotusMarks();

        logger.info("Initialized");
    }
    // ============== /SUBSCRIBE AND INITIALIZE/ =================


    // =============== MAKE IMAGE PATHS =================

    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }

    // =============== /MAKE IMAGE PATHS/ =================


    // ================ ADD CARDS ===================
    @Override
    public void receiveEditCards() {
        logger.info("Add Card");

        BaseMod.addCard(new LotusChain());
        BaseMod.addCard(new SeasonOfFire());
        BaseMod.addCard(new FullBloom());
        BaseMod.addCard(new OnTheHunt());
        BaseMod.addCard(new Mark());
        BaseMod.addCard(new SkilledExecution());
        BaseMod.addCard(new LotusSlash());
        BaseMod.addCard(new LotusStrike());

        logger.info("Cards - added!");

        UnlockTracker.unlockCard(LotusChain.ID);
        UnlockTracker.unlockCard(SeasonOfFire.ID);
        UnlockTracker.unlockCard(FullBloom.ID);
        UnlockTracker.unlockCard(OnTheHunt.ID);
        UnlockTracker.unlockCard(Mark.ID);
        UnlockTracker.unlockCard(SkilledExecution.ID);
        UnlockTracker.unlockCard(LotusSlash.ID);
        UnlockTracker.unlockCard(LotusStrike.ID);
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
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addUIElement(new ModLabel("LotusMarks Mark doesn't have any settings!", 400.0f, 700.0f, settingsPanel, (me) -> {
        }));
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        if (Loader.isModLoaded("archetypeapi")) {
            lotusMarkSilentArchetypeFiles.add("lotusMarksResources/localization/eng/ArchetypeAPIJsons/LotusMark-Silent-Archetype.json");
            abstractArchetype.silentArchetypeSelectCards.addToTop(new LotusMarchSelectionCard().makeCopy());

        }
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

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }
}
