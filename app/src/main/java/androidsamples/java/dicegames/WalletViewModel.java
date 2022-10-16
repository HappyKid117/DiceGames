package androidsamples.java.dicegames;

import androidx.lifecycle.ViewModel;

public class WalletViewModel extends ViewModel {

  private static final String TAG = "WalletViewModel";
  private static int WIN_VALUE = 6;
  private static int INCREMENT = 5;
  
  private Die mDie;
  private int mBalance;
  private int mSingleSixes;
  private int mDoubleSixes;
  private int mDoubleOthers;
  private int mPreviousRoll;
  private int mTotalRolls;

  /*
   * The no argument constructor.
   */
  public WalletViewModel() {
    mDie = new Die6();
    mBalance = 0;
    mSingleSixes = 0;
    mDoubleSixes = 0;
    mDoubleOthers = 0;
    mPreviousRoll = 0;
    mTotalRolls = 0;
  }

  /**
   * Reports the current balance.
   *
   */
  public int balance() {
    return mBalance;
  }

  /**
   * Rolls the {@link Die} in the wallet and implements the changes accordingly.
   */
  public void rollDie() {
    mPreviousRoll = dieValue();
    mDie.roll();
    mTotalRolls++;
    if (dieValue() == WIN_VALUE) {
      mBalance += INCREMENT;
      if(mPreviousRoll == WIN_VALUE){
        mBalance += INCREMENT;
        mDoubleSixes++;
      }else{
        mSingleSixes++;
      }
    }
    else if(dieValue() == mPreviousRoll){
      mBalance -= INCREMENT;
      mDoubleOthers++;
    }
  }

  /**
   * Reports the current value of the {@link Die}.
   *
   */
  public int dieValue() {
    return mDie.value();
  }

  /**
   * Reports the number of single (or first) sixes rolled so far.
   *
   */
  public int singleSixes() {
    return mSingleSixes;
  }

  /**
   * Reports the total number of dice rolls so far.
   *
   */
  public int totalRolls() {
    return mTotalRolls;
  }

  /**
   * Reports the number of times two sixes were rolled in a row.
   *
   */
  public int doubleSixes() {
    return mDoubleSixes;
  }

  /**
   * Reports the number of times any other number was rolled twice in a row.
   *
   */
  public int doubleOthers() {
    return mDoubleOthers;
  }

  /**
   * Reports the value of the die on the previous roll.
   *
   */
  public int previousRoll() {
    return mPreviousRoll;
  }

  public int WIN_VALUE() {
    return WIN_VALUE;
  }
}
