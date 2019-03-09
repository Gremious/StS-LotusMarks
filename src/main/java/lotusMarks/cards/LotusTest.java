package lotus.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.*;

import basemod.abstracts.CustomCard;
import lotus.Lotus;
import lotus.powers.*;
import lotus.cards.*;


public class LotusTest
extends CustomCard {
	
/*
	"LotusFire": {
        "NAME": "Season of Fire",
        "DESCRIPTION": " Deal !D! damage to every enemy for each Lotus Mark they have (?).",
        "UPGRADE_DESCRIPTION": "Deal !D! damage to every enemy for each Lotus Mark they have(?)."
}
*/
	
// TEXT DECLARATION 

	public static final String ID = "LotusTest";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
// -TEXT DECLARATION- 
	
	public static final String IMG = Lotus.makePath(Lotus.LOTUS_TEST);
	
// STAT DECLARATION 
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

	private static final int COST = 0;	
	private static final int COST_UPGRADE = 1;	
	
	private static final int MAGIC_DAMAGE = 6;	
	private static final int DAMAGE = 6;
	
	private static final CardType TYPE = CardType.POWER;
	private static final CardTarget TARGET = CardTarget.SELF;
	
// -TEXT DECLARATION-
	
	public LotusTest() {	
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);

		this.magicNumber = this.baseMagicNumber = MAGIC_DAMAGE;
		this.damage = this.baseDamage = DAMAGE;

	}
	
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {

		
	}
	
	
private int countUpDebuffs(AbstractMonster monster) {
	int mark = 0;
	  if (monster.hasPower("Lotus_Mark"))
	         mark = monster.getPower("Lotus_Mark").amount;
	  return mark;
}


@Override
public AbstractCard makeCopy() {
    return new LotusTest();
}

@Override
public void upgrade() {
    if (!this.upgraded) {
        this.upgradeName();	
        this.upgradeBaseCost(COST_UPGRADE);
        this.rawDescription = UPGRADE_DESCRIPTION;
        this.initializeDescription();
    }
}
}
/*
*BASIC ATTACK
**************************
 AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
**************************

*BASIC MULTI-ATTACK
**************************
AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction(p, this.multiDamage, 
		this.damageTypeForTurn, 
		com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
	
**************************

*COMPLEX MULTI-ATTACK
**************************
AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction(p,       
				com.megacrit.cardcrawl.cards.DamageInfo.createDamageMatrix(debuffCount * this.magicNumber, true), 
				this.damageTypeForTurn, 
				com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
**************************

*DAMAGE CALC
**************************
public void calculateCardDamage(AbstractMonster mo)
{

	if (mo.hasPower("Lotus_Mark")) this.damage = mo.getPower("Lotus_Mark").amount*DAMAGE;
	super.calculateCardDamage(mo);
	
}
*/
	
