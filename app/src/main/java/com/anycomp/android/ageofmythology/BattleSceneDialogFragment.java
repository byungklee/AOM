package com.anycomp.android.ageofmythology;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by byung on 4/6/15.
 */
public class BattleSceneDialogFragment extends DialogFragment {

    PlayerController pc;
    AttackController ac;

    ListView attackers;
    ListView defenders;
    ImageView attackCard;
    ImageView defendCard;
    Button battleButton;
    Button nextButton;
    Button retreatButton;
    Button rollButton;
    TextView attackerText;
    TextView attackerScore;
    TextView defenderText;
    TextView defenderScore;

    public BattleSceneDialogFragment() {}
    public void setPlayerController(PlayerController pc) {
        this.pc = pc;
    }
    public void setAttackController(AttackController ac) {
        this.ac = ac;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.fragment_battle_scene, null);
        attackers=(ListView) v.findViewById(R.id.attacker_card_list);
        defenders=(ListView) v.findViewById(R.id.defender_card_list);
        attackCard=(ImageView) v.findViewById(R.id.attacker_card);
        defendCard=(ImageView) v.findViewById(R.id.defender_card);
        battleButton=(Button) v.findViewById(R.id.battle_button);
        nextButton=(Button) v.findViewById(R.id.next_button);
        rollButton=(Button) v.findViewById(R.id.roll_button);
        retreatButton=(Button)v.findViewById(R.id.retreat_button);
        attackerText=(TextView) v.findViewById(R.id.attacker_text);
        attackerScore=(TextView) v.findViewById(R.id.attacker_score);
        defenderText=(TextView)v.findViewById(R.id.defender_text);
        defenderScore=(TextView) v.findViewById(R.id.defender_score);
        //TO DO:

        battleButton.setEnabled(false);
        rollButton.setEnabled(false);
        nextButton.setEnabled(false);


        BattleUnitAdapter attackersAdapter = new BattleUnitAdapter(getActivity().getApplicationContext(), ac.getAttackers());
        BattleUnitAdapter defendersAdapter = new BattleUnitAdapter(getActivity().getApplicationContext(), ac.getDefenders());



        attackers.setAdapter(attackersAdapter);
        defenders.setAdapter(defendersAdapter);

        if(ac.isHumanAttacking()) {
            attackers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ac.setAttackerSelection(position);
                    attackCard.setImageResource(ac.getAttackers().get(ac.getAttackerSelection()).getImagePath());

                    ac.aiDefenderChooseRandomUnit();
                    defendCard.setImageResource(R.drawable.cardback);
                    battleButton.setEnabled(true);
                }
            });
        } else {
            defenders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ac.setDefenderSelection(position);
                    defendCard.setImageResource(ac.getDefenders().get(position).getImagePath());

                    ac.aiAttackerChooseRandomUnit();
                    attackCard.setImageResource(R.drawable.cardback);
                    battleButton.setEnabled(true);
                }
            });
        }

        battleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.battle();
                if(ac.isHumanAttacking()) {
                    defendCard.setImageResource(ac.getDefenders().get(ac.getDefenderSelection()).getImagePath());
                } else {
                    attackCard.setImageResource(ac.getAttackers().get(ac.getAttackerSelection()).getImagePath());
                }
                battleButton.setEnabled(false);
                rollButton.setEnabled(true);
                attackers.setEnabled(false);
                defenders.setEnabled(false);
                updateTextViewsForBattle();
            }
        });

        rollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ac.roll()) {
                    nextButton.setEnabled(true);
                    rollButton.setEnabled(false);
                }
                updateTextViewsForRoll();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(ac.nextBattle()) {
                clearTextAndImageViews();
               } else {
                    //game end
                   gameEndDialog();

                }
                nextButton.setEnabled(false);
                attackers.setEnabled(true);
                defenders.setEnabled(true);

            }
        });

        retreatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ac.retreat(0);
             //   BattleSceneDialogFragment.this.dismiss();
                ac.winnerTakeVictoryCube();
                ac.moveAllTheUnitBack();
                gameEndDialog();
                //ac.takeTrophy();

            }
        });

        builder.setView(v);
        return builder.create();
    }

    public void updateTextViewsForBattle() {
        attackerText.setText("Dice: "+ac.getAttackerPossibleDice());
        attackerScore.setText(""+ac.getAttackerScore());
        defenderText.setText("Dice: "+ac.getDefenderPossibleDice());
        defenderScore.setText(""+ac.getDefenderScore());
    }

    public void updateTextViewsForRoll() {
        attackerText.setText("Rolled: "+ac.getAttackerDice());
        attackerScore.setText(""+ac.getAttackerScore());
        defenderText.setText("Rolled: "+ac.getDefenderDice());
        defenderScore.setText(""+ac.getDefenderScore());
    }

    public void gameEndDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if(ac.isAttackerWin) {
            builder.setMessage("Attacker won");
        } else {
            builder.setMessage("Defender won");
        }

        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                if(ac.isAttackerWin) {
                    if(ac.isHumanAttacking()) {
                        ac.takeTrophy();
                    } else {
                        ac.takeResources();
                    }
                }
                BattleSceneDialogFragment.this.dismiss();
            }
        });
        builder.create().show();
    }

    public void clearTextAndImageViews() {
        attackCard.setImageResource(0);
        defendCard.setImageResource(0);
        attackerText.setText("");
        defenderText.setText("");

    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        //pc.nextRound();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(ac.decideWinner()) {

            gameEndDialog();
        }
    }
}
