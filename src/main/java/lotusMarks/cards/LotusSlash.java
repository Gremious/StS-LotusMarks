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
import lotusMarks.LotusMarks;
import lotusMarks.powers.LotusMarkPower;

import static lotusMarks.LotusMarks.makeCardPath;


public class LotusSlash extends CustomCard {
	
/*
     "LotusSlash": 
        "NAME": "Lotus Slash",
        "DESCRIPTION": "Deal !D! damage. NL Apply !M! Lotus Mark.",
        "UPGRADE_DESCRIPTION": "Deal !D! damage. NL Apply !M! Lotus Marks."
*/

// TEXT DECLARATION 

    public static final String ID = LotusMarks.makeID("LotusSlash");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// TEXT DECLARATION 

    public static final String IMG = makeCardPath("LotusSlash.png");

// STAT DECLARATION 	

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

    private static final int COST = 1;
    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int MAGIC = 1;
    private static final int UPGRADE_MAGIC = 1;

// -STAT DECLARATION-

    public LotusSlash() {


        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;
        this.magicNumber = this.baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(
                m, p, new LotusMarkPower(m, p, magicNumber), magicNumber));

        AbstractDungeon.actionManager.addToBottom(new DamageAction(
                m, new DamageInfo(p, damage, damageTypeForTurn),
                AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    }


    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(UPGRADE_PLUS_DMG);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.upgradeMagicNumber(UPGRADE_MAGIC);
            this.initializeDescription();
        }
    }
}