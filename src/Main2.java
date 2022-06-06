
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main2 {

    public enum fieldSymbol {
        KRESTIK('x'),
        NOLIK('o'),
        PUSTO('.');
        final char param;

        fieldSymbol(char param) {
            this.param = param;
        }

        public char getParam() {
            return param;
        }
    }

    public static void main(String[] args) {
//rerion algoritm
        //1. создание перменных
        //2. ввод координат первым игроком. Проверка адекватности ввода.
        //3. проверка победы первого игрока
        //4. ввод координат вторым игроком, если первый не победил. Проверка адекватности ввода.
        //5. проверка победы второго игрока
        //6. Возврат на 2 пункт если второй не победил. Повторяем до тех пор,
        // пока на поле есть место для хода и никто не победил.
//endregion
//region Переменные

        Random random = new Random();
        int columns = 10;
        int lines = 10;
        fieldSymbol[][] gameField = new fieldSymbol[lines][columns];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < lines; j++) {
                gameField[i][j] = fieldSymbol.PUSTO;
            }
        }
        boolean errorDetected = true;
        boolean xIsWinner = false;
        boolean oIsWinner = false;
        boolean noPlaceOnField = false;
        int xCount=0;
        int oCount=0;
        int step = 0;
        int counter1 = 0;
        int counter2 = 0;
        int columnFirstPlayer = 0;
        int lineFirstPlayer = 0;
        int columnSecondPlayer = 0;
        int lineSecondPlayer = 0;
        int horizontalKrestik1Count = 0;
        int horizontalKrestik2Count = 0;
        int verticalKrestik1Count = 0;
        int verticalKrestik2Count = 0;
        int mainDiagonalKrestik1Count = 0;
        int mainDiagonalKrestik2Count = 0;
        int secondaryDiagonalKrestik1Count = 0;
        int secondaryDiagonalKrestik2Count = 0;
        for (int i = 0; i < columns; i++) {
            System.out.println();
            for (int j = 0; j < lines; j++) {
                System.out.print(gameField[i][j].getParam()+" ");
            }
        }
        //endregion
//region Игра
        do {
//region entering x mark
            Scanner scanner = new Scanner(System.in);
            System.out.println();

            do {
                System.out.println("Enter line coordinate of X");
                do {
                    try {
                        Scanner sc = new Scanner(System.in);
                        lineFirstPlayer = sc.nextInt() - 1;
                        errorDetected = false;
                        if (lineFirstPlayer > 10 || lineFirstPlayer < 0) {
                            errorDetected = true;
                            System.out.println("Wrong coordinate");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Pls, enter a number from 1 to 10");
                        errorDetected = true;
                    }
                } while (errorDetected);

                System.out.println("Enter column coordinate of X");
                do {
                    try {
                        Scanner sc = new Scanner(System.in);
                        columnFirstPlayer = sc.nextInt() - 1;
                        errorDetected = false;
                        if (columnFirstPlayer > 10 || columnFirstPlayer < 0) {
                            errorDetected = true;
                            System.out.println("Wrong coordinate");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Pls, enter a number from 1 to 10");
                        errorDetected = true;
                    }


                } while (errorDetected);

            }while(gameField[lineFirstPlayer][columnFirstPlayer]!=fieldSymbol.PUSTO);

//enter x mark at pos
            gameField[lineFirstPlayer][columnFirstPlayer] = fieldSymbol.KRESTIK;
            xCount++;
//show field
            for (int i = 0; i < columns; i++) {
                System.out.println();
                for (int j = 0; j < lines; j++) {
                    System.out.print(gameField[i][j].getParam() + " ");
                }
            }
//endregion
//region vertical scan
            horizontalKrestik1Count = 0;
            horizontalKrestik2Count = 0;
            verticalKrestik1Count = 0;
            verticalKrestik2Count = 0;
            mainDiagonalKrestik1Count = 0;
            mainDiagonalKrestik2Count = 0;
            secondaryDiagonalKrestik1Count = 0;
            secondaryDiagonalKrestik2Count = 0;
            step = 0;
            do {

                if (gameField[lineFirstPlayer + step][columnFirstPlayer] == fieldSymbol.KRESTIK) {
                    verticalKrestik1Count++;
                }
                if (lineFirstPlayer + step < 9) {
                    step++;
                }
                if(lineFirstPlayer+step == 9){
                    if (gameField[lineFirstPlayer + step][columnFirstPlayer] == fieldSymbol.KRESTIK) {
                        verticalKrestik1Count++;
                    }
                }

            } while (gameField[lineFirstPlayer + step][columnFirstPlayer] == fieldSymbol.KRESTIK
                    && lineFirstPlayer + step < 9);

            counter1 = lineFirstPlayer;
            do {

                if (gameField[counter1][columnFirstPlayer] == fieldSymbol.KRESTIK && counter1 < lineFirstPlayer) {
                    verticalKrestik2Count++;
                }
                if (counter1 > 0) {
                    counter1--;
                }
                if(counter1==0){
                    if (gameField[counter1][columnFirstPlayer] == fieldSymbol.KRESTIK && counter1 < lineFirstPlayer) {
                        verticalKrestik2Count++;
                    }
                }

            } while (gameField[counter1][columnFirstPlayer] == fieldSymbol.KRESTIK && counter1 != 0);
//endregion
//region horizontal scan
            step = 0;
            do {

                if (gameField[lineFirstPlayer][columnFirstPlayer + step] == fieldSymbol.KRESTIK) {
                    horizontalKrestik1Count++;
                }
                if (columnFirstPlayer + step < 9) {
                    step++;
                }
                if (columnFirstPlayer + step == 9) {
                    if (gameField[lineFirstPlayer][columnFirstPlayer + step] == fieldSymbol.KRESTIK) {
                        horizontalKrestik1Count++;
                    }
                }

            } while (gameField[lineFirstPlayer][columnFirstPlayer + step] == fieldSymbol.KRESTIK
                    && columnFirstPlayer + step < 9);

            counter2 = columnFirstPlayer;

            do {

                if (gameField[lineFirstPlayer][counter2] == fieldSymbol.KRESTIK && counter2 < columnFirstPlayer) {
                    horizontalKrestik2Count++;
                }

                if (counter2 > 0) {
                    counter2--;
                }

                if (counter2 == 0) {
                    if (gameField[lineFirstPlayer][counter2] == fieldSymbol.KRESTIK) {
                        horizontalKrestik2Count++;
                    }
                }
            } while (gameField[lineFirstPlayer][counter2] == fieldSymbol.KRESTIK && counter2 > 0);
//endregion
//region main diagonal scan

            counter1 = lineFirstPlayer;
            counter2 = columnFirstPlayer;
            do {

                if (gameField[counter1][counter2] == fieldSymbol.KRESTIK && counter1 < lineFirstPlayer
                        && counter2 < columnFirstPlayer) {
                    mainDiagonalKrestik1Count++;
                }
                if (counter1 > 0 && counter2 > 0) {
                    counter1--;
                    counter2--;
                }
                if(counter1==0 && counter2==0){
                    if (gameField[counter1][counter2] == fieldSymbol.KRESTIK && counter1 < lineFirstPlayer
                            && counter2 < columnFirstPlayer) {
                        mainDiagonalKrestik1Count++;
                    }
                }

            } while (gameField[counter1][counter2] == fieldSymbol.KRESTIK && counter1 != 0 && counter2 != 0);
            step = 0;
            do {

                if (gameField[lineFirstPlayer + step][columnFirstPlayer + step] == fieldSymbol.KRESTIK) {
                    mainDiagonalKrestik2Count++;
                }
                if (columnFirstPlayer + step < 9 && lineFirstPlayer + step < 9) {
                    step++;
                }
                if (columnFirstPlayer + step == 9 && lineFirstPlayer + step == 9) {
                    if (gameField[lineFirstPlayer + step][columnFirstPlayer + step] == fieldSymbol.KRESTIK) {
                        mainDiagonalKrestik2Count++;
                    }
                }
            } while (gameField[lineFirstPlayer + step][columnFirstPlayer + step] == fieldSymbol.KRESTIK
                    && columnFirstPlayer + step < 9 && lineFirstPlayer + step < 9);
//endregion
//region secondary diagonal scan

            step = 0;
            counter1 = columnFirstPlayer;
            do {

                if (gameField[lineFirstPlayer + step][counter1] == fieldSymbol.KRESTIK) {
                    secondaryDiagonalKrestik1Count++;
                }
                if (lineFirstPlayer + step < 9 && counter1 > 0) {
                    step++;
                    counter1--;
                }
                if(lineFirstPlayer+step ==9 && counter1==0){
                    if (gameField[lineFirstPlayer + step][counter1] == fieldSymbol.KRESTIK) {
                        secondaryDiagonalKrestik1Count++;
                    }
                }

            } while (gameField[lineFirstPlayer + step][counter1] == fieldSymbol.KRESTIK && lineFirstPlayer + step < 9
                    && counter1 != 0);

            step = 0;
            counter2 = lineFirstPlayer;
            do {

                if (gameField[counter2][columnFirstPlayer + step] == fieldSymbol.KRESTIK && counter2 < lineFirstPlayer) {
                    secondaryDiagonalKrestik2Count++;
                }
                if (columnFirstPlayer + step < 9 && counter2 > 0) {
                    step++;
                    counter2--;
                }
                if(columnFirstPlayer+step==9 && counter2==0){
                    if (gameField[counter2][columnFirstPlayer + step] == fieldSymbol.KRESTIK && counter2 < lineFirstPlayer) {
                        secondaryDiagonalKrestik2Count++;
                    }
                }

            } while (gameField[counter2][columnFirstPlayer + step] == fieldSymbol.KRESTIK && columnFirstPlayer + step < 9
                    && counter2 != 0);
//endregion
//region program tests data
//System.out.println();
//System.out.println(horizontalKrestik1Count);
//System.out.println(horizontalKrestik2Count);
//System.out.println(verticalKrestik1Count);
//System.out.println(verticalKrestik2Count);
//System.out.println(mainDiagonalKrestik1Count);
//System.out.println(mainDiagonalKrestik2Count);
//System.out.println(secondaryDiagonalKrestik1Count);
//System.out.println(secondaryDiagonalKrestik2Count);
//endregion
//region game end from X scan
            if (verticalKrestik1Count + verticalKrestik2Count >= 5
                    || horizontalKrestik1Count + horizontalKrestik2Count >= 5
                    || mainDiagonalKrestik1Count + mainDiagonalKrestik2Count >= 5
                    || secondaryDiagonalKrestik1Count + secondaryDiagonalKrestik2Count >= 5) {
                xIsWinner = true;

            }
//endregion
            if (!xIsWinner) {
//region entering o mark
                System.out.println();
                do {
                    System.out.println("Enter line coordinate of 'o'");
                    do {
                        try {
                            Scanner sc = new Scanner(System.in);
                            lineSecondPlayer = sc.nextInt() - 1;
                            errorDetected = false;
                            if (lineSecondPlayer > 10 || lineSecondPlayer < 0) {
                                errorDetected = true;
                                System.out.println("Wrong coordinate");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Pls, enter a number from 1 to 10");
                            errorDetected = true;
                        }
                    } while (errorDetected);

                    System.out.println("Enter column coordinate of 'o'");
                    do {
                        try {
                            Scanner sc = new Scanner(System.in);
                            columnSecondPlayer = sc.nextInt() - 1;
                            errorDetected = false;
                            if (columnSecondPlayer > 10 || columnSecondPlayer < 0) {
                                errorDetected = true;
                                System.out.println("Wrong coordinate");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Pls, enter a number from 1 to 10");
                            errorDetected = true;
                        }


                    } while (errorDetected);
                }while(gameField[lineSecondPlayer][columnSecondPlayer]!=fieldSymbol.PUSTO);

                gameField[lineSecondPlayer][columnSecondPlayer] = fieldSymbol.NOLIK;
                oCount++;
                for (int i = 0; i < columns; i++) {
                    System.out.println();
                    for (int j = 0; j < lines; j++) {
                        System.out.print(gameField[i][j].getParam() + " ");
                    }
                }
//endregion
//region vertical scan for o
                horizontalKrestik1Count = 0;//vertical scan
                horizontalKrestik2Count = 0;
                verticalKrestik1Count = 0;
                verticalKrestik2Count = 0;
                mainDiagonalKrestik1Count = 0;
                mainDiagonalKrestik2Count = 0;
                secondaryDiagonalKrestik1Count = 0;
                secondaryDiagonalKrestik2Count = 0;
                step = 0;
                do {

                    if (gameField[lineSecondPlayer + step][columnSecondPlayer] == fieldSymbol.NOLIK) {
                        verticalKrestik1Count++;
                    }
                    if (lineSecondPlayer + step < 9) {
                        step++;
                    }
                    if (lineSecondPlayer + step == 9) {
                        if (gameField[lineSecondPlayer + step][columnSecondPlayer] == fieldSymbol.NOLIK) {
                            verticalKrestik1Count++;
                        }
                    }

                } while (gameField[lineSecondPlayer + step][columnSecondPlayer] == fieldSymbol.NOLIK
                        && lineSecondPlayer + step < 9);

                counter1 = lineSecondPlayer;
                do {

                    if (gameField[counter1][columnSecondPlayer] == fieldSymbol.NOLIK && counter1 < lineSecondPlayer) {
                        verticalKrestik2Count++;
                    }
                    if (counter1 > 0) {
                        counter1--;
                    }
                    if (counter1 == 0) {
                        if (gameField[counter1][columnSecondPlayer] == fieldSymbol.NOLIK && counter1 < lineSecondPlayer) {
                            verticalKrestik2Count++;
                        }
                    }

                } while (gameField[counter1][columnSecondPlayer] == fieldSymbol.NOLIK && counter1 != 0);
//endregion
//region horizontal scan for o
                step = 0;
                do {

                    if (gameField[lineSecondPlayer][columnSecondPlayer + step] == fieldSymbol.NOLIK) {
                        horizontalKrestik1Count++;
                    }
                    if (columnSecondPlayer + step < 9) {
                        step++;
                    }
                    if (columnSecondPlayer + step == 9) {
                        if (gameField[lineSecondPlayer][columnSecondPlayer + step] == fieldSymbol.NOLIK) {
                            horizontalKrestik1Count++;
                        }
                    }

                } while (gameField[lineSecondPlayer][columnSecondPlayer + step] == fieldSymbol.NOLIK
                        && columnSecondPlayer + step < 9);

                counter2 = columnSecondPlayer;
                do {

                    if (gameField[lineSecondPlayer][counter2] == fieldSymbol.NOLIK && counter2 < columnSecondPlayer) {
                        horizontalKrestik2Count++;
                    }

                    if (counter2 > 0) {
                        counter2--;
                    }
                    if (counter2 == 0) {
                        if (gameField[lineSecondPlayer][counter2] == fieldSymbol.NOLIK) {
                            horizontalKrestik2Count++;
                        }
                    }

                } while (gameField[lineSecondPlayer][counter2] == fieldSymbol.NOLIK && counter2 != 0);
//endregion
//region main diagonal scan for o

                counter1 = lineSecondPlayer;
                counter2 = columnSecondPlayer;
                do {

                    if (gameField[counter1][counter2] == fieldSymbol.NOLIK && counter1 < lineSecondPlayer
                            && counter2 < columnSecondPlayer) {
                        mainDiagonalKrestik1Count++;
                    }
                    if (counter1 > 0 && counter2 > 0) {
                        counter1--;
                        counter2--;
                    }
                    if (counter1 == 0 && counter2 == 0){
                        if (gameField[counter1][counter2] == fieldSymbol.NOLIK && counter1 < lineSecondPlayer) {
                            mainDiagonalKrestik1Count++;
                        }
                    }


                } while (gameField[counter1][counter2] == fieldSymbol.NOLIK && counter1 != 0 && counter2 != 0);
                step = 0;
                do {

                    if (gameField[lineSecondPlayer + step][columnSecondPlayer + step] == fieldSymbol.NOLIK) {
                        mainDiagonalKrestik2Count++;
                    }
                    if (columnSecondPlayer + step < 9 && lineSecondPlayer + step < 9) {
                        step++;
                    }
                    if (columnSecondPlayer + step == 9 && lineSecondPlayer + step == 9){
                        if (gameField[lineSecondPlayer + step][columnSecondPlayer + step] == fieldSymbol.NOLIK) {
                            mainDiagonalKrestik2Count++;
                        }
                    }

                } while (gameField[lineSecondPlayer + step][columnSecondPlayer + step] == fieldSymbol.NOLIK
                        && columnSecondPlayer + step < 9 && lineSecondPlayer + step < 9);
//endregion
//region secondary diagonal scan for o

                step = 0;
                counter1 = columnSecondPlayer;
                do {

                    if (gameField[lineSecondPlayer + step][counter1] == fieldSymbol.NOLIK) {
                        secondaryDiagonalKrestik1Count++;
                    }
                    if (lineSecondPlayer + step < 9 && counter1 > 0) {
                        step++;
                        counter1--;
                    }
                    if (lineSecondPlayer + step == 9 && counter1 == 0){
                        if (gameField[lineSecondPlayer + step][counter1] == fieldSymbol.NOLIK) {
                            secondaryDiagonalKrestik1Count++;
                        }
                    }



                } while (gameField[lineSecondPlayer + step][counter1] == fieldSymbol.NOLIK && lineSecondPlayer + step < 9
                        && counter1 != 0);

                step = 0;
                counter2 = lineSecondPlayer;
                do {

                    if (gameField[counter2][columnSecondPlayer + step] == fieldSymbol.NOLIK
                            && counter2 < lineSecondPlayer) {
                        secondaryDiagonalKrestik2Count++;
                    }
                    if (columnSecondPlayer + step < 9 && counter2 > 0) {
                        step++;
                        counter2--;
                    }
                    if (columnSecondPlayer + step == 9 && counter2 == 0){
                        if (gameField[counter2][columnSecondPlayer + step] == fieldSymbol.NOLIK
                                && counter2 < lineSecondPlayer) {
                            secondaryDiagonalKrestik2Count++;
                        }
                    }

                } while (gameField[counter2][columnSecondPlayer + step] == fieldSymbol.NOLIK && columnSecondPlayer + step < 9
                        && counter2 != 0);
//endregion
//region program tests data for o
//System.out.println();
//System.out.println(horizontalKrestik1Count + " 2p");
//System.out.println(horizontalKrestik2Count + " 2p");
//System.out.println(verticalKrestik1Count + " 2p");
//System.out.println(verticalKrestik2Count + " 2p");
//System.out.println(mainDiagonalKrestik1Count + " 2p");
//System.out.println(mainDiagonalKrestik2Count + " 2p");
//System.out.println(secondaryDiagonalKrestik1Count + " 2p");
//System.out.println(secondaryDiagonalKrestik2Count + " 2p");
//endregion
//region game end from o scan
                if (verticalKrestik1Count + verticalKrestik2Count >= 5
                        || horizontalKrestik1Count + horizontalKrestik2Count >= 5
                        || mainDiagonalKrestik1Count + mainDiagonalKrestik2Count >= 5
                        || secondaryDiagonalKrestik1Count + secondaryDiagonalKrestik2Count >= 5) {
                    oIsWinner = true;

                }
//endregion
            }
        } while (!xIsWinner && !oIsWinner && xCount+oCount<columns*lines);
//endregion
//region объявление победителя или конца места а поле
        System.out.println();
        if(xCount+oCount>=columns*lines){
            System.out.println("The place is over! Game end.");
        }

        if(xIsWinner){
            System.out.println("x is winner!");
        }else if(oIsWinner){
            System.out.println("o is winner!");
        }

//endregion
    }
}