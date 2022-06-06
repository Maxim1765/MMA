import java.util.Scanner;


public class Main1 {


    static int EnteringNum(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Pls,enter a number of watermelons:");
        return sc.nextInt();
    }

    static int[] EnteringMas(int numWatermelon){

        int[] mas = new int[numWatermelon];
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < mas.length; i++) {
            System.out.println("Enter a weigh of watermelon number: " + (i+1));
            mas[i] = scanner.nextInt();
        }
        return mas;
    }


    static void ClearConsole(){
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    static void ShowMassive(int[] mas){
        System.out.print("Weighs of watermelons: ");
        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");

        }
        System.out.println();
    }

    static void MinElem(int[] mas){
        int min = mas[0];
        for (int i = 0; i < mas.length; i++) {
            if (min > mas[i]) {
                min = mas[i];
            }
        }
        System.out.print("Min weigh: " + min);
    }

    static void MaxElem(int[] mas){
        int max = mas[0];
        for (int i = 0; i < mas.length; i++) {
            if (max < mas[i]) {
                max = mas[i];
            }
        }
        System.out.println("Max weigh: " + max);
    }




    public static void main(String[] args) {

        int[] mas = EnteringMas(EnteringNum());

        ShowMassive(mas);
        ClearConsole();
        MinElem(mas);
        System.out.println();
        MaxElem(mas);

    }
}

