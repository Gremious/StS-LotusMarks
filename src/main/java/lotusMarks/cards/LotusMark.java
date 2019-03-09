package lotusMarks.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

import basemod.abstracts.CustomCard;
import lotusMarks.LotusMarks;
import lotusMarks.powers.LotusMarkPower;


public class LotusMark
extends CustomCard {
	
/*
"Mark": {
        "NAME": "Mark",
        "DESCRIPTION": "Apply !M! LotusMarks Mark and !M! vulnerable on an enemy.",
        "UPGRADE_DESCRIPTION": "Apply !M! LotusMarks Marks and !M! vulnerable on an enemy."
*/
	
// TEXT DECLARATION
	
	public static final String ID = "LotusMark";
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final	String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
// -TEXT DECLARATION-
	
	public static final String IMG = LotusMarks.makePath(LotusMarks.LOTUS_MARK);
	
// STAT DECLARATION

	private static final CardRarity RARITY = CardRarity.COMMON;
	private static final CardTarget TARGET = CardTarget.ENEMY;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCard.CardColor.GREEN;

	private static final int COST = 0;	
	
	private static final int MAGIC = 1;
	private static final int UPGRADE_MAGIC = 1;
	
// -STAT DECLARATION-

	
	public LotusMark() {
		
		
		super(ID,NAME,IMG,COST,DESCRIPTION,TYPE,COLOR,RARITY,TARGET);
		this.magicNumber = this.baseMagicNumber = MAGIC;
		
	}
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new LotusMarkPower(m, p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
    	
    	}

    @Override
    public AbstractCard makeCopy() {
        return new LotusMark();
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