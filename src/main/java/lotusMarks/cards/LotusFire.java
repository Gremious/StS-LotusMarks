package lotusMarks.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import lotus.Lotus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LotusFire
        extends CustomCard {
	
/*
	"LotusFire": {
        "NAME": "Season of Fire",
        "DESCRIPTION": " Deal !D! damage to every enemy for each Lotus Mark they have.",
        "UPGRADE_DESCRIPTION": "Deal !D! damage to every enemy for each Lotus Mark they have."
}
*/

    // TEXT DECLARATION
    public static final String ID = "LotusFire";
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
// -TEXT DECLARATION-

    public static final Logger logger = LogManager.getLogger(Lotus.class.getName());
    public static final String IMG = Lotus.makePath(Lotus.LOTUS_FIRE);

// STAT DECLARATION

    public static final CardRarity RARITY = CardRarity.COMMON;
    public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

    private static final int COST = 2;

    private static int DAMAGE = 4;
    private static int DAMAGE_UPGRADE = 2;

    private static final CardType TYPE = CardType.ATTACK;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;


// -STAT DECLARATION-

    public LotusFire() {

        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.baseDamage = DAMAGE;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

//	logger.info("Attack start this.damage = " + this.damage);
//	logger.info("Attack start this.baseDamage = " + this.baseDamage);

        for (AbstractMonster monster : AbstractDungeon.getCurrRoom().monsters.monsters) {


            int count = countUpDebuffs(monster);

//	logger.info("count = " + count);

            this.damage *= count;

//	logger.info("this.damage *= count = " + this.damage);

            AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(monster,
                    new DamageInfo(p, this.damage, this.damageTypeForTurn),
                    AbstractGameAction.AttackEffect.SLASH_DIAGONAL));

//	logger.info("Attack completed. Current Damage " + this.damage);
//	logger.info("Attack completed. Current Base Damage " + this.baseDamage);

            this.damage = this.baseDamage;

//	logger.info("Reset completed this.damage " + this.damage);
//	logger.info("Reset completed this.baseDamage " + this.baseDamage);
//	logger.info("Full attack loop completed");


        }
    }


    private int countUpDebuffs(AbstractMonster monster) {
        int mark = 0;
        if (monster.hasPower("Lotus_Mark"))
            mark = monster.getPower("Lotus_Mark").amount;
        return mark;
    }


    @Override
    public AbstractCard makeCopy() {
        return new LotusFire();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(DAMAGE_UPGRADE);
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
}

