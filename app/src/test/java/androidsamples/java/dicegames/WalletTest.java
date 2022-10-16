package androidsamples.java.dicegames;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Example local unit test, which will execute on the development machine
 * (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class WalletTest {

  @Test
  public void singleWin() {
    WalletViewModel mockWalletVM = spy(new WalletViewModel());
    when(mockWalletVM.dieValue()).thenReturn(1).thenReturn(6);

    mockWalletVM.rollDie();
    assertEquals(5, mockWalletVM.balance());
  }

  @Test
  public void doubleWin() {
    WalletViewModel mockWalletVM = spy(new WalletViewModel());
    when(mockWalletVM.dieValue()).thenReturn(6).thenReturn(6);

    mockWalletVM.rollDie();
    assertEquals(10, mockWalletVM.balance());
  }

  @Test
  public void noWin() {
    WalletViewModel mockWalletVM = spy(new WalletViewModel());
    when(mockWalletVM.dieValue()).thenReturn(1).thenReturn(2);

    mockWalletVM.rollDie();
    assertEquals(0, mockWalletVM.balance());
  }

  @Test
  public void loss() {
    WalletViewModel mockWalletVM = spy(new WalletViewModel());
    when(mockWalletVM.dieValue()).thenReturn(3).thenReturn(3);

    mockWalletVM.rollDie();
    assertEquals(-5, mockWalletVM.balance());
  }

  @Test
  public void twoDoubleWin() {
    WalletViewModel mockWalletVM = spy(new WalletViewModel());
    when(mockWalletVM.dieValue()).thenReturn(6).thenReturn(6);

    mockWalletVM.rollDie();
    mockWalletVM.rollDie();
    assertEquals(20, mockWalletVM.balance());
  }
}