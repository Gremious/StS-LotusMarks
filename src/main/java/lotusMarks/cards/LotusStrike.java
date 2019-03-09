package lotus.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import lotus.Lotus;


public class LotusStrike
extends CustomCard {
	
/*
	"LotusStrike": {
        "NAME": "Lotus Strike",
        "DESCRIPTION": "Deal !D! damage. +!M! for every Lotus Mark on the enemy.",
        "UPGRADE_DESCRIPTION": "Deal !D! damage. +!M! for every Lotus Mark on the enemy."
*/
	
// TEXT DECLARATION 
	
	public static final String ID = "LotusStrike";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

// -TEXT DECLARATION
	
	public static final String IMG = Lotus.makePath(Lotus.LOTUS_STRIKE);
	
// STAT DECLARATION 
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

	private static final int COST = 1;	
	private static final int DAMAGE = 6;
	
	private static final int MAGIC = 2;
	private static final int UPGRADE_MAGIC = 2;

// -STAT DECLARATION- 

	
	public LotusStrike() {
		
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		
		this.damage = this.baseDamage = DAMAGE;
		this.magicNumber = this.baseMagicNumber = MAGIC;
		
	}

	@Override
	public void use (AbstractPlayer p, AbstractMonster m) {

			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
					new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		
	}
	
	@Override
	public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster m, float tmp) {
		if (m == null || m.getPower("Lotus_Mark") == null) {
			return tmp;
		}
		int markDamage = DAMAGE + (m.getPower("Lotus_Mark").amount * this.magicNumber);
		tmp = markDamage;
		return tmp;
	}

	
    @Override
    public AbstractCard makeCopy() {
        return new LotusStrike();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();	
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.upgradeMagicNumber(UPGRADE_MAGIC);
            this.initializeDescription();
        }
    }
}