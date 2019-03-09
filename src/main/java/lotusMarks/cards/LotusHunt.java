package lotusMarks.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.actions.common.*;
import basemod.abstracts.CustomCard;
import lotusMarks.LotusMarks;


public class LotusHunt
extends CustomCard {
	
/*
    "LotusHunt": {
        "NAME": "On The Hunt",
        "DESCRIPTION": "Gain strength equal to the total number of LotusMarks Marks on all enemies. Exhaust.",
        "UPGRADE_DESCRIPTION": "Gain strength equal to the total number of LotusMarks Marks on all enemies. Exhaust."
*/

// TEXT DECLARATION	
	
	public static final String ID = "LotusHunt";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
// -TEXT DECLARATION-
	
	public static final String IMG = LotusMarks.makePath(LotusMarks.LOTUS_HUNT);
	
// STAT DECLARATION	
	
	private static final CardRarity RARITY = CardRarity.UNCOMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

	private static final int COST = 2;	
	private static final int UPGRADE_COST = 1;	

	private static final int MAGIC = 1;
	
	private int MARKS = 0;
	
// -STAT DECLARATION-	
	
	public LotusHunt() {
		
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
			
		this.magicNumber = this.baseMagicNumber = MAGIC;
		this.exhaust = true;
		
	}

	@Override
	public void use (AbstractPlayer p, AbstractMonster m) {
		
	   	for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
	   		
	   		if (mo.hasPower("Lotus_Mark")) {
	   			
	   		this.MARKS = mo.getPower("Lotus_Mark").amount;
			for (int i = 0; i < MARKS; i++) {
				AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
			}
				
			}	
			
			
			}
	 	
}

	
    @Override
    public AbstractCard makeCopy() {
        return new LotusHunt();
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