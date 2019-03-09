package lotusMarks.powers;

import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.*;

import lotusMarks.LotusMarks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;

public class LotusSkilledPower extends AbstractPower {

	public AbstractCreature source;
	public static final Logger logger = LogManager.getLogger(LotusMarks.class.getName());
	
	public static final String POWER_ID = "LotusSkilledPower";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
    public LotusSkilledPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
    	logger.info("Declaration Start");
    	
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;	
        this.isTurnBased = false;
        this.img = LotusMarks.getLotusSkilledPower();
        this.source = source;
        
    	logger.info("Declaration end");

    }
    
    @Override
    public void updateDescription() 
    {
    	logger.info("Description Start");

    	if (this.amount == 1){     
    		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    	}
    	
    	else if (this.amount > 1) {
    		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];
    	}
    	
    	logger.info("Description end");

    	}
 //=====================
    @Override
    public void onUseCard(final AbstractCard card, final UseCardAction action) {
        if (!card.purgeOnUse && card.type == AbstractCard.CardType.ATTACK && this.amount > 0) {
            this.flash();
            AbstractMonster m = null;
            if (action.target != null) {
                m = (AbstractMonster)action.target;
            }
         
            while(this.amount > 0) {
            final AbstractCard tmp = card.makeStatEquivalentCopy();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = Settings.WIDTH / 2.0f - 300.0f * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0f;
            tmp.freeToPlayOnce = true;
            if (m != null) {
                tmp.calculateCardDamage(m);
            }
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, m, card.energyOnUse));
            if (tmp.cardID.equals("Rampage")) {
                AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(card.uuid, tmp.magicNumber));
            }
            --this.amount;
            }
            
            if (this.amount == 0) {
                AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "LotusSkilledPower"));
            }
        
    }
    }
    @Override
    public void atEndOfTurn(final boolean isPlayer) {
        if (isPlayer) {
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, "LotusSkilledPower"));
        }
    }
//=====================  


}


