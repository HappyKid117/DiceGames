package androidsamples.java.dicegames;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import androidx.lifecycle.ViewModelProvider;
import android.widget.Toast;  

// Quirk libraries
import android.os.Vibrator;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class WalletActivity extends AppCompatActivity {
  private static final String TAG = "WalletActivity";

  private Button mBtnDie;
  private TextView mTxtCoins;
  private TextView mTxtPrevRoll;
  private TextView mTxtSingleSixes;
  private TextView mTxtDoubleSixes;
  private TextView mTxtDoubleOthers;
  private TextView mTxtTotalRolls;
  private WalletViewModel mWalletVM;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wallet);

    mTxtCoins = findViewById(R.id.txt_coins);
    mBtnDie = findViewById(R.id.btn_die);
    mTxtPrevRoll = findViewById(R.id.txt_prev_roll);
    mTxtSingleSixes = findViewById(R.id.txt_single_sixes);
    mTxtDoubleSixes = findViewById(R.id.txt_double_sixes);
    mTxtDoubleOthers = findViewById(R.id.txt_double_others);
    mTxtTotalRolls = findViewById(R.id.txt_total_rolls);

    mWalletVM = new ViewModelProvider(this).get(WalletViewModel.class);
    updateUI(mWalletVM);
  }

  public void onButtonClick(View view) {
    mWalletVM.rollDie();
    updateUI(mWalletVM);
    quirks();
  }

  private void updateUI(WalletViewModel mWalletVM) {
    mTxtCoins.setText(Integer.toString(mWalletVM.balance()));
    mBtnDie.setText(Integer.toString(mWalletVM.dieValue()));
    mTxtPrevRoll.setText(Integer.toString(mWalletVM.previousRoll()));
    mTxtSingleSixes.setText(Integer.toString(mWalletVM.singleSixes()));
    mTxtDoubleSixes.setText(Integer.toString(mWalletVM.doubleSixes()));
    mTxtDoubleOthers.setText(Integer.toString(mWalletVM.doubleOthers()));
    mTxtTotalRolls.setText(Integer.toString(mWalletVM.totalRolls()));

    if(mWalletVM.dieValue() == mWalletVM.WIN_VALUE()){
      Toast.makeText(getApplicationContext(),"You rolled a Six!",Toast.LENGTH_SHORT).show();  
    }
  }

  // Quirks
  private void quirks() {
    vibrate();
    shake();
  }

  private void vibrate() {
    Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    vibe.vibrate(100);
  }

  private void shake() {
    Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
    mBtnDie.startAnimation(shake);
  }

}