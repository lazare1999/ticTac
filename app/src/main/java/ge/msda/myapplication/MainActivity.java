package ge.msda.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;

    private TextView notesTextView;

    private SharedPreferences notesPref;

    private int activePlayer = 1;

    private List<Integer> firstPlayer = new ArrayList<>();
    private List<Integer> secondPlayer = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        notesPref = getSharedPreferences("prefs", MODE_PRIVATE);


        int player1 = notesPref.getInt("player1", 0);
        int player2 = notesPref.getInt("player2", 0);

        String ans = "player1: " +player1 +" - "+ "player2: " + player2;

        notesTextView.setText(ans);

    }

    private void init() {

        notesTextView = findViewById(R.id.textViewNotes);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        button9 = findViewById(R.id.button9);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
        button9.setOnClickListener(this);

    }

    @Override
    public void onClick(View clickedView) {

        Button clickedButton = (Button) clickedView;

        int buttonNumber = 0;

        switch (clickedButton.getId()) {
            case R.id.button1:
                buttonNumber = 1;
                break;
            case R.id.button2:
                buttonNumber = 2;
                break;
            case R.id.button3:
                buttonNumber = 3;
                break;
            case R.id.button4:
                buttonNumber = 4;
                break;
            case R.id.button5:
                buttonNumber = 5;
                break;
            case R.id.button6:
                buttonNumber = 6;
                break;
            case R.id.button7:
                buttonNumber = 7;
                break;
            case R.id.button8:
                buttonNumber = 8;
                break;
            case R.id.button9:
                buttonNumber = 9;
                break;
        }

        if (buttonNumber != 0) {
            playGame(buttonNumber, clickedButton);
        }

    }

    private void playGame(int buttonNumber, Button clickedButton) {
        clickedButton.setEnabled(false);
        if (activePlayer == 1) {
            clickedButton.setText("X");
            clickedButton.setBackgroundColor(Color.RED);
            activePlayer = 2;
            firstPlayer.add(buttonNumber);
            check(firstPlayer, 1);
        } else {
            clickedButton.setText("0");
            clickedButton.setBackgroundColor(Color.YELLOW);
            activePlayer = 1;
            secondPlayer.add(buttonNumber);
            check(secondPlayer, 2);
        }
    }

    private void check(List<Integer> args, int player) {


        if (
                        isContainsSet(args, 1, 2, 3) ||
                        isContainsSet(args, 4, 5, 6) ||
                        isContainsSet(args, 7, 8, 9) ||
                        isContainsSet(args, 1, 4, 7) ||
                        isContainsSet(args, 2, 5, 8) ||
                        isContainsSet(args, 3, 6, 9) ||
                        isContainsSet(args, 1, 5, 9) ||
                        isContainsSet(args, 3, 5, 7)

        ) {

            Toast.makeText(this, "Player " + player + " won!", Toast.LENGTH_SHORT).show();


            if (player ==1) {

                notesPref.edit()
                        .putInt("player1", notesPref.getInt("player1", 0)+1)
                        .apply();
            } else if (player ==2) {
                notesPref.edit()
                        .putInt("player2", notesPref.getInt("player2", 0)+1)
                        .apply();
            }

            int player1 = notesPref.getInt("player1", 0);
            int player2 = notesPref.getInt("player2", 0);

            String ans = "player1: " +player1 +" - "+ "player2: " + player2;

            notesTextView.setText(ans);


        }

    }

    private boolean isContainsSet(List<Integer> arr, int... params) {
        for (int param : params) {
            if (!arr.contains(param)) {
                return false;
            }
        }
        return true;
    }

}