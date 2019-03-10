package lotusMarks.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import lotusMarks.LotusMarks;
import lotusMarks.powers.LotusMarkPower;

import static lotusMarks.LotusMarks.makeCardPath;


public class SeasonOfFire extends CustomCard {

    public static final String ID = LotusMarks.makeID("SeasonOfFire");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    public static final String IMG = makeCardPath("SeasonOfFire.png");

// STAT DECLARATION

    public static final CardRarity RARITY = CardRarity.UNCOMMON;
    public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    public static int DAMAGE = 1;
    public static int MAGIC = 1;

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;


// /STAT DECLARATION/

    public SeasonOfFire() {

        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            int count = 0;

            if (mo.hasPower(LotusMarkPower.POWER_ID)) {
                count = mo.getPower(LotusMarkPower.POWER_ID).amount;
            }

            damage = count;
            magicNumber = count;

            AbstractDungeon.actionManager.addToBottom(new DamageAction(
                    mo, new DamageInfo(p, damage, damageTypeForTurn),
                    AbstractGameAction.AttackEffect.FIRE));

            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                    mo, p, new VulnerablePower(mo, magicNumber, false), magicNumber));


            damage = baseDamage;
            magicNumber = baseMagicNumber;

        }
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}

