package lotusMarks.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import lotusMarks.LotusMarks;
import lotusMarks.powers.LotusMarkPower;

import static lotusMarks.LotusMarks.makeCardPath;


public class OnTheHunt extends CustomCard {
// TEXT DECLARATION	

    public static final String ID = LotusMarks.makeID("OnTheHunt");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// -TEXT DECLARATION-

    public static final String IMG = makeCardPath("OnTheHunt.png");

// STAT DECLARATION	

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    private static final int MAGIC = 1;

    // -STAT DECLARATION-

    public OnTheHunt() {


        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);

        this.magicNumber = this.baseMagicNumber = MAGIC;
        this.exhaust = true;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int marks = 0;

        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(LotusMarkPower.POWER_ID)) {
                marks += mo.getPower(LotusMarkPower.POWER_ID).amount;
            }
        }

        for (int i = 0; i < marks; i++) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        }
    }


    @Override
    public AbstractCard makeCopy() {
        return new OnTheHunt();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.upgradeBaseCost(UPGRADE_COST);
            this.initializeDescription();
        }
    }
}