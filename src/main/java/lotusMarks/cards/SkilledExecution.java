package lotusMarks.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;
import lotusMarks.powers.LotusMarkPower;
import lotusMarks.powers.SkilledApplicationPower;

import static lotusMarks.LotusMarks.makeCardPath;

public class SkilledExecution extends CustomCard {
// TEXT DECLARATION

    public static final String ID = "SkilledExecution";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);


    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    public static final String[] EXTENDED_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;

    // -TEXT DECLARATION-
    public static final String IMG = makeCardPath("SkilledExecution.png");

// STAT DECLARATION

    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

    private int AMOUNT = 0;

// -STAT DECLARATION-

    public SkilledExecution() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = AMOUNT;
    }

    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {

        if (m.hasPower(LotusMarkPower.POWER_ID)) {
            magicNumber += m.getPower(LotusMarkPower.POWER_ID).amount;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new SkilledApplicationPower(p, p, magicNumber), magicNumber));
            magicNumber = baseMagicNumber;
        } else {
            AbstractDungeon.actionManager.addToBottom(new VFXAction(new ThoughtBubble(p.dialogX, p.dialogY, EXTENDED_DESCRIPTION[0], true)));
        }
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }

}
