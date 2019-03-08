package lotus.cards;

import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.monsters.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.actions.common.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import basemod.abstracts.CustomCard;

import lotus.*;
import lotus.powers.*;
import com.megacrit.cardcrawl.core.*;

public class LotusSkilled extends CustomCard
{
// TEXT DECLARATION
	
    public static final String ID = "LotusSkilled";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    
// -TEXT DECLARATION-
    
	public static final Logger logger = LogManager.getLogger(Lotus.class.getName());
	public static final String IMG = Lotus.makePath(Lotus.LOTUS_SKILLED);
	
// STAT DECLARATION
	
    private static final int COST = 3;
    private static final int UPGRADE_COST = 2;
    
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

    private int AMOUNT = 1; 
    
// -STAT DECLARATION-
    
    public LotusSkilled() {
    	super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
        this.AMOUNT = 1;
        this.baseMagicNumber = this.AMOUNT;
        this.magicNumber = this.baseMagicNumber;
    }
    
    @Override
    public void use(final AbstractPlayer p, final AbstractMonster m) {
    	logger.info("Card Use Start");

    	if (m.hasPower("Lotus_Mark")) {
    	int count = countUpDebuffs(m);
    	this.magicNumber = count-1;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LotusSkilledPower(p, p, this.magicNumber), this.magicNumber));
        this.magicNumber = AMOUNT;
        
    	}
    	
        logger.info("Card Use End");

    }
    
    private int countUpDebuffs(AbstractMonster monster) {
    	int mark = 0;
    	  if (monster.hasPower("Lotus_Mark"))
    	         mark = monster.getPower("Lotus_Mark").amount;
    	  return mark;
    }


	@Override
    public AbstractCard makeCopy() {
        return new LotusSkilled();
    }
    
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeBaseCost(UPGRADE_COST);
            this.rawDescription = LotusSkilled.UPGRADE_DESCRIPTION;
            this.initializeDescription();
        }
    }
    
}
