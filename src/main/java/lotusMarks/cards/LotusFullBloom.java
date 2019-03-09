package lotus.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.abstracts.CustomCard;
import lotus.Lotus;
import lotus.powers.LotusMarkPower;

public class LotusFullBloom
extends CustomCard {
/*
"LotusFullBloom": {
        "NAME": "Full Bloom",
        "DESCRIPTION": "Apply !M! Lotus Marks to all enemies.",
        "UPGRADE_DESCRIPTION": "Apply !M! Lotus Marks to all enemies."
*/
	
// TEXT DECLARATION

	public static final String ID = "LotusFullBloom";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
// -TEXT DECLARATION-

	public static final String IMG = Lotus.makePath(Lotus.LOTUS_FULL_BLOOM);
	
// STAT DECLARATION

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

	private static final int COST = 2;	
	private static final int UPGRADE_COST = 1;	
	
	private static final int MAGIC = 2;
	
// -STAT DECLARATION-

	
	public LotusFullBloom() {
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.magicNumber = this.baseMagicNumber = MAGIC;
		this.exhaust = true;

	}
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
   
    	for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
        	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new LotusMarkPower(mo, p, this.baseMagicNumber), this.baseMagicNumber));
        }
    }
    	


    @Override
    public AbstractCard makeCopy() {
        return new LotusFullBloom();
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
