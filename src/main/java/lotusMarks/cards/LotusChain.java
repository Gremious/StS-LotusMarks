package lotusMarks.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import lotusMarks.LotusMarks;
import lotusMarks.powers.LotusMarkPower;

import static lotusMarks.LotusMarks.makeCardPath;

public class LotusChain extends CustomCard {
    
    // TEXT DECLARATION
    
    public static final String ID = LotusMarks.makeID("LotusChain");
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String IMG = makeCardPath("LotusChain.png");
    
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
    
    // -TEXT DECLARATION-
    
    
    // STAT DECLARATION
    
    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = AbstractCard.CardColor.GREEN;
    
    private static final int COST = 1;
    
    private static final int DAMAGE = 4;
    private static final int UPGRADE_DAMAGE = 1;
    
    // -STAT DECLARATION-
    
    public LotusChain() {
        super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        this.damage = this.baseDamage = DAMAGE;
    }
    
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int marks = 0;
        
        for (final AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(LotusMarkPower.POWER_ID)) {
                marks += mo.getPower(LotusMarkPower.POWER_ID).amount;
            }
        }
        
        if (marks <= 0) {
            AbstractCreature randMo = AbstractDungeon.getMonsters().getRandomMonster();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(randMo, p, new LotusMarkPower(randMo, p, 1), 1));
        } else {
            for (int i = 0; i < marks; i++) {
                AbstractCreature randMo = AbstractDungeon.getMonsters().getRandomMonster();
                if (randMo.hasPower(LotusMarkPower.POWER_ID)) {
                    AbstractDungeon.actionManager.addToBottom(new DamageAction(
                            randMo, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
                } else {
                    LotusMarkPower.applyMarksIfEmpty(randMo, p);
                }
            }
        }
        marks = 0;
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