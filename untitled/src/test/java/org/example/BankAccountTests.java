package org.example;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTests{
     @Test
     @DisplayName("TC-1.1.1: Пополнение увеличивает баланс")
        void Deposit_IncreaseBalanceTest(){
         BankAccount acc =new BankAccount("Alice",500.0);
         acc.deposit(200.0);
         assertEquals(700.0,acc.getBalance(),0.001);
     }
     @Test
     @DisplayName("TC-1.1.2: Снятие уменьшает баланс")
        void Withdraw_DecreaseBakanceTest(){
         BankAccount acc= new BankAccount("Alice",500.0);
         acc.withdraw(100.0);
         assertEquals(400,acc.getBalance(),0.001);
     }
     @Test
     @DisplayName("TC-1.1.3: Владелец возвращается корректно")
        void GetOwner_BalanceTest() {
         BankAccount acc=new BankAccount("Tricia",500.0);
         assertEquals("Tricia",acc.getOwner());
     }
     @Test
     @DisplayName("TC-1.1.4: Новый счёт не заморожен")
        void IsFrozen_BalanceTest(){
         BankAccount acc =new BankAccount("Pike",500.0);
         assertFalse(acc.isFrozen());
     }
    @Test
    @DisplayName("TC-1.2.1: Снятие больше, чем на счёте")
        void MoreThanHave_BalanceTest(){
         BankAccount acc = new BankAccount("Alice",250.0);
         Throwable exception=assertThrows(IllegalStateException.class,()->acc.withdraw(260));
         assertEquals("Insufficient funds",exception.getMessage());

    }
    @Test
    @DisplayName("TC-1.2.2: Пополнение отрицательной суммой")
        void NegativeDeposit_BalanceTest(){
         BankAccount acc = new BankAccount("Alice",300.0);
         Throwable exception = assertThrows(IllegalArgumentException.class,()-> acc.deposit(-250.0));
         assertEquals("Deposit amount must be positive",exception.getMessage());
     }
    @Test
    @DisplayName("TC-1.2.3: Снятие нуля")
        void NullWithdraw_BalanceTest(){
         BankAccount acc = new BankAccount("Alice",250.0);
         Throwable exception = assertThrows(IllegalArgumentException.class,()->acc.withdraw(0));
         assertEquals("Withdrawal amount must be positive",exception.getMessage());
    }
    @Test
    @DisplayName("TC-1.2.4: Создание счёта с отрицательным балансом")
        void CreateNegativeAcc_BalanceTest(){
         IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,()->new BankAccount("Vasya",-300));
         assertEquals("Initial balance cannot be negative",exception.getMessage());
    }
    @Test
    @DisplayName("TC-1.2.5: Создание счёта с null именем")
        void CreateNullName_OwnerTest(){
         IllegalArgumentException exception =assertThrows(IllegalArgumentException.class,()->new BankAccount(null,250));
         assertEquals("Owner name cannot be blank",exception.getMessage());
    }
    @Test
    @DisplayName("TC-1.2.6: Создание счёта с пустым именем")
        void CreateBlankName_OwnerTest(){
         IllegalArgumentException exception= assertThrows(IllegalArgumentException.class, ()->new BankAccount("",500));
         assertEquals("Owner name cannot be blank",exception.getMessage());
    }
    @Test
    @DisplayName("TC-1.2.7: Создание счёта с именем из пробелов")
        void CreateSpaceName_OwnerTest(){
         IllegalArgumentException exception= assertThrows(IllegalArgumentException.class, ()->new BankAccount("   ",500));
         assertEquals("Owner name cannot be blank",exception.getMessage());
    }
    @Test
    @DisplayName("TC-1.2.8: Пополнение нулём")
    void DepositWithZero_BalanceTest(){
        BankAccount acc = new BankAccount("Alice",500);
        IllegalArgumentException exception=assertThrows(IllegalArgumentException.class,()->acc.deposit(0));
        assertEquals("Deposit amount must be positive",exception.getMessage());
    }
    @Test
    @DisplayName("TC-1.2.9: Баланс не меняется после неудачного снятия")
    void withdraw_rejection_wontChangeBalance(){
        BankAccount acc = new BankAccount("Alice",250);
        assertThrows(IllegalStateException.class,()->acc.withdraw(260));
        assertEquals(250,acc.getBalance());
    }
    @Test
    @Disabled("BUG-1: Конструктор принимает имя из одних цифр")
    @DisplayName("TC-1.2.10: Имя счета из одних цифр отклоняется")
    void CreateAccount_NumericOwner_throwsIllegalTest(){
        assertThrows(IllegalArgumentException.class,()-> new BankAccount("1234",300));
    }
    @Test
    @Disabled("BUG-2: Депозит принимает NaN")
    @DisplayName("TC-1.2.11: Пополнение баланса NaN")
    void DepositWithNan_BalanceTest(){
        BankAccount acc = new BankAccount("Alice",500);
        assertThrows(IllegalArgumentException.class,()->acc.deposit(Double.NaN));
    }
    @Test
    @DisplayName("TC-1.3.1: Пополнение замороженного счёта")
    void deposit_isFrozen_IllegalState(){
         BankAccount acc =new BankAccount("Alice",100);
         acc.freeze();
         assertThrows(IllegalStateException.class,()->acc.deposit(50));
         assertEquals(100,acc.getBalance(),0.001);
    }
    @Test
    @DisplayName("TC-1.3.2: Снятие с замороженного счёта")
    void withdraw_isFrozen_IllegalState(){
        BankAccount acc =new BankAccount("Alice",100);
        acc.freeze();
        assertThrows(IllegalStateException.class,()->acc.withdraw(50));
        assertEquals(100,acc.getBalance(),0.001);
    }
    @Test
    @DisplayName("TC-1.3.3: Пополнение после разморозки")
    void deposit_afterUnfreeze_succeed(){
        BankAccount acc =new BankAccount("Alice",100);
        acc.freeze();
        acc.unfreeze();
        acc.deposit(50);
        assertEquals(150,acc.getBalance(),0.001);
    }
    @Test
    @DisplayName("TC-1.3.4: Заморозка меняет флаг")
    void freeze_isFrozen_true(){
        BankAccount acc =new BankAccount("Alice",100);
        acc.freeze();
        assertTrue(acc.isFrozen());
    }
    @Test
    @DisplayName("TC-1.3.5: Разморозка меняет флаг")
    void unfreeze_isFrozen_false(){
        BankAccount acc =new BankAccount("Alice",100);
        acc.freeze();
        acc.unfreeze();
        assertFalse(acc.isFrozen());
    }
    @Test
    @DisplayName("TC-1.4.1: Успешный перевод")
    void transferTo_succeed(){
         BankAccount A =new BankAccount("Alice",300);
         BankAccount B =new BankAccount("Boris",50);
         A.transferTo(B,100);
         assertAll(
                 ()->assertEquals(200,A.getBalance(),0.001),
                 ()->assertEquals(150,B.getBalance(),0.001)
         );
    }
    @Test
    @DisplayName("TC-1.4.2: Перевод с замороженного счёта")
    void transferTo_fromFrozen_IllegalState(){
         BankAccount A =new BankAccount("Alice",300);
         BankAccount B =new BankAccount("Boris",50);
         A.freeze();
         IllegalStateException exception = assertThrows(IllegalStateException.class,()->A.transferTo(B,100));
         assertEquals("Account is frozen",exception.getMessage());
    }
    @Test
    @DisplayName("TC-1.4.3: Перевод больше баланса")
    void transferTo_moreThanBalance_IllegalState(){
        BankAccount A =new BankAccount("Alice",100);
        BankAccount B =new BankAccount("Boris",0);
        IllegalStateException exception = assertThrows(IllegalStateException.class,()->A.transferTo(B,500));
        assertAll(
                ()->assertEquals("Insufficient funds",exception.getMessage()),
                ()->assertEquals(100,A.getBalance(),0.001),
                ()->assertEquals(0,B.getBalance(),0.001)
        );
    }
    @Test
    @Disabled("BUG-3: transferTo снимает деньги при переводе на замороженный счёт")
    @DisplayName("TC-1.4.4: Перевод на замороженный счёт")
    void transferTo_toFrozen_IllegalState(){
        BankAccount A =new BankAccount("Alice",300);
        BankAccount B =new BankAccount("Boris",50);
        B.freeze();
        assertThrows(IllegalStateException.class,()->A.transferTo(B,100));
        assertAll(
                ()->assertEquals(50,B.getBalance(),0.001),
                ()->assertEquals(300,A.getBalance(),0.001)
        );
    }





}
