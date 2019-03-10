package lotusMarks.archetypes;

import archetypeAPI.cards.AbstractArchetypeCard;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import lotusMarks.LotusMarks;

import static archetypeAPI.patches.ArchetypeCardTags.SINGLE;

public class LotusMarchSelectionCard extends AbstractArchetypeCard {

    public static final String ID = LotusMarks.makeID("LotusMarchSelectionCard");
    public static final String IMG = "lotusMarksResources/images/cards/Mark.png";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String EXTENDED_DESCRIPTION[] = cardStrings.EXTENDED_DESCRIPTION;

    public static final CardColor COLOR = CardColor.GREEN;
    public static final CardType TYPE = CardType.ATTACK;

    // ==/ Basic String/Type Declaration /==

    public LotusMarchSelectionCard() {
        super(ID, NAME, IMG, DESCRIPTION, TYPE, COLOR);
        if (Loader.isModLoaded("archetypeapi")) {
            tags.add(SINGLE);
        }
    }

    @Override
    public void archetypeEffect() {
        LotusMarkSilent lotusMarkSilent = new LotusMarkSilent();
    }

    @Override
    public String getTooltipName() {
        return EXTENDED_DESCRIPTION[0];
    }

    @Override
    public String getTooltipDesc() {
        return EXTENDED_DESCRIPTION[1];
    }

}
