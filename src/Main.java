import java.util.Random;
import java.util.Scanner;

public class Main {

    public enum FieldCell {
        ALIVE_SHIP('K'),
        DEAD_SHIP('X'),
        MISS('O'),
        EMPTY('.');

        private char value;

        FieldCell(char value) {
            this.value = value;
        }

        public char getValue() {
            return value;
        }
    }

    public enum Player {
        USER("Игорок"),
        COMP("Компьютер"),
        NONE("Пока неизвестно");

        private String value;

        Player(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
    static void var(){
        FieldCell[][] compField, userField;
        Player currentPlayer, winPlayer = Player.NONE;

        int filedSize = 0;
        int countCompShips, countUserShips;

        boolean playGame = true;
        return;
    }
    static void inputPol(int filedSize){
        System.out.println("Добро пожаловать в моской бой против компьютера");

        boolean inputResult;
        do {
            System.out.print("Пожалуйста введите размер поля для игры (от 3 до 10): ");
            inputResult = true;

            try {
                Scanner scanner = new Scanner(System.in);
                filedSize = scanner.nextInt();


                if (filedSize < 3 || filedSize > 10) {
                    throw new Exception();
                }
            } catch (Exception e) {
                inputResult = false;
                System.out.println("Ошибка. Введите число и в заданном диапазоне");
            }

        } while (inputResult == false);
        return;
    }
    static void creatingPol(int[][] compField, int[][] userField, int filedSize, int countCompShips, int countUserShips){
        compField = new int[filedSize][filedSize];
        userField = new int[filedSize][filedSize];

        countCompShips = countUserShips = filedSize;
        return;
    }
    static void randomEps(int filedSize, int[][] compField, int[][] userField , int countCompShips, int countUserShips){
        Random random = new Random();
        for (int i = 0; i < filedSize; i++) {
            for (int j = 0; j < filedSize; j++) {
                compField[i][j] = FieldCell.EMPTY;
                userField[i][j] = FieldCell.EMPTY;
            }
        }

        for (int k = 0; k < countCompShips; k++) {
            int iShip, jShip;

            do {
                iShip = random.nextInt(filedSize);
                jShip = random.nextInt(filedSize);
            } while (compField[iShip][jShip] != FieldCell.EMPTY);

            compField[iShip][jShip] = FieldCell.ALIVE_SHIP;
        }

        for (int k = 0; k < countUserShips; k++) {
            int iShip, jShip;

            do {
                iShip = random.nextInt(filedSize);
                jShip = random.nextInt(filedSize);
            } while (userField[iShip][jShip] != FieldCell.EMPTY);

            userField[iShip][jShip] = FieldCell.ALIVE_SHIP;
        }
    }
    static void randomRun(int filedSize, int[][] compField,){
        System.out.println("поле компьютера");
        for (int i = 0; i < filedSize; i++) {
            for (int j = 0; j < filedSize; j++) {
                if (compField[i][j] == FieldCell.ALIVE_SHIP) {
                    System.out.print(FieldCell.EMPTY.getValue());
                } else {
                    System.out.print(compField[i][j].getValue());
                }
            }
            System.out.println();
        }
    }
    static void inputPlayr(int filedSize, int[][] userField){
        System.out.println("поле игрока");
        for (int i = 0; i < filedSize; i++) {
            for (int j = 0; j < filedSize; j++) {
                System.out.print(userField[i][j].getValue());
            }
            System.out.println();
        }
    }
    static void vstrel(Player currentPlayer, int filedSize, int[][] userField, int countUserShips, int[][] compField, int countCompShips){
        Random random = new Random();
        if (currentPlayer == Player.COMP) {
            System.out.println("Выстрел компьютера. Для продолжения нажмите <Enter>");
            new Scanner(System.in).nextLine();

            int iShoot, jShoot;

            do {
                iShoot = random.nextInt(filedSize);
                jShoot = random.nextInt(filedSize);
            } while (userField[iShoot][jShoot] == FieldCell.DEAD_SHIP || userField[iShoot][jShoot] == FieldCell.MISS);

            if (userField[iShoot][jShoot] == FieldCell.EMPTY) {
                userField[iShoot][jShoot] = FieldCell.MISS;
                currentPlayer = Player.USER;
            } else if (userField[iShoot][jShoot] == FieldCell.ALIVE_SHIP) {
                userField[iShoot][jShoot] = FieldCell.DEAD_SHIP;
                countUserShips--;
            }
        } else if (currentPlayer == Player.USER) {
            System.out.println("Выстрел игрока");
            Scanner scanner = new Scanner(System.in);
            int iShoot, jShoot;
            System.out.print(String.format("введите строчку для выстрела от %d до %d: ", 1, filedSize));
            iShoot = scanner.nextInt() - 1;

            System.out.print(String.format("введите столбец для выстрела от %d до %d: ", 1, filedSize));
            jShoot = scanner.nextInt() - 1;

            if (compField[iShoot][jShoot] == FieldCell.EMPTY) {
                compField[iShoot][jShoot] = FieldCell.MISS;
                currentPlayer = Player.COMP;
            } else if (compField[iShoot][jShoot] == FieldCell.ALIVE_SHIP) {
                compField[iShoot][jShoot] = FieldCell.DEAD_SHIP;
                countCompShips--;
            }
        }
    }
    static void proverka(int countCompShips, int countUserShips, Player winPlayer, boolean playGame){
        if (countCompShips == 0) {
            winPlayer = Player.USER;
            playGame = false;
        } else if (countUserShips == 0) {
            winPlayer = Player.COMP;
            playGame = false;
        }
    }
    static void clear(){
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    static void inputComp(int filedSize, int[][] compField){
        System.out.println("поле компьютера");
        for (int i = 0; i < filedSize; i++) {
            for (int j = 0; j < filedSize; j++) {
                System.out.print(compField[i][j].getValue());
            }
            System.out.println();
        }
    }



    public static void main(String[] args) {
        //region инициализация переменных

        //endregion

        //region ввод размера поля


        //endregion

        //region создание полей

        //endregion

        //region рандомная расстановка кораблей на полях (игрока и компьютера)



        //endregion

        //region рандом кто ходит первым (игрок и компьютер)

            //endregion

            //region вывод поля компа

            //endregion

            //region вывод поля игрока

            //endregion

            //region выстрел (игрока или компьютера)

            //endregion

            //region проверка на победу

            //endregion
        }
        //endregion

        //region очистка экрана

        //endregion

        //region вывод поля компа

        //endregion

        //region вывод поля игрока
        System.out.println("поле игрока");
        for (int i = 0; i < filedSize; i++) {
            for (int j = 0; j < filedSize; j++) {
                System.out.print(userField[i][j].getValue());
            }
            System.out.println();
        }
        //endregion

        //region вывод победителя
        System.out.println("Победил " + winPlayer.getValue());
        //endregion

    }
}