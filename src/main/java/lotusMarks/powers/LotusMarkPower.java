package lotusMarks.powers;

import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.powers.*;
import lotusMarks.LotusMarks;

public class LotusMarkPower extends AbstractPower {
	public AbstractCreature source;
	
	public static final String POWER_ID = "Lotus_Mark";
	private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
	
    public LotusMarkPower(final AbstractCreature owner, final AbstractCreature source, final int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.type = PowerType.BUFF;
        this.isTurnBased = false;
        this.img = LotusMarks.getLotusMarkTexture();
        this.source = source;
        
    }
    
    @Override
    public void updateDescription() 
    {
    	if (this.amount == 1){
    		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];}
    	
    	else if (this.amount > 1) {
    		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[2];}

    	}
   

}


