package lotus.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import lotus.Lotus;
import basemod.abstracts.CustomCard;


public class LotusChain
extends CustomCard {
	
/*
 "LotusChain": {
        "NAME": "Lotus Chain",
        "DESCRIPTION": "Deal !D! damage to a random enemy two times, and an extra time for each Lotus Mark on any enemy.",
        "UPGRADE_DESCRIPTION": "Deal !D! damage to a random enemy three times, and an extra time for each Lotus Mark on any enemy."
*/
	
// TEXT DECLARATION
	
	public static final String ID = "LotusChain";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
// -TEXT DECLARATION-	
	
	public static final String IMG = Lotus.makePath(Lotus.LOTUS_CHAIN);

// STAT DECLARATION
	
	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.ATTACK;
	public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

	private static final int COST = 1;	
	private static final int DAMAGE = 4;
	private static final int UPGRADE_DAMAGE = 1;
	private int MARKS = 0;
	
// -STAT DECLARATION-

	public LotusChain() {
		
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		
		this.damage = this.baseDamage = DAMAGE;
		
		
	}

	@Override
	public void use (AbstractPlayer p, AbstractMonster m) {

	   	for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
	   		
	   		if (mo.hasPower("Lotus_Mark")) {
	   			
	   		this.MARKS = mo.getPower("Lotus_Mark").amount;
			for (int i = 0; i < MARKS; i++) {
				AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(
						AbstractDungeon.getMonsters().getRandomMonster(true),
						new DamageInfo(p, this.damage, this.damageTypeForTurn),
						AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
				
			}
				
			}	
			
			
			}
	 	
}
	
/*
 	@Override
	public float calculateModifiedCardDamage(AbstractPlayer player, AbstractMonster m, float tmp) {
		if (m == null || m.getPower("Lotus_Mark") == null) {
			return tmp;
		}
		int markDamage = DAMAGE + (m.getPower("Lotus_Mark").amount * this.magicNumber);
		tmp = markDamage;
		return tmp;
	}
	*/

	
    @Override
    public AbstractCard makeCopy() {
        return new LotusChain();
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();	
            this.rawDescription = UPGRADE_DESCRIPTION;
            this.upgradeDamage(UPGRADE_DAMAGE);
            this.initializeDescription();
        }
    }
}