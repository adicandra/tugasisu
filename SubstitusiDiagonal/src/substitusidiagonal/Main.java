/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package substitusidiagonal;
import java.util.Scanner;

/**
 *
 * @author Urip Yogantara
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String key;
        Scanner in=new Scanner(System.in);
        System.out.print("Masukan Kunci : ");
        key= in.nextLine();
        System.out.print("Masukan PlainText : ");
        String plain= in.nextLine();
        key=key.toUpperCase();
        plain=plain.toUpperCase();
        SubstitusiDiagonal s= new SubstitusiDiagonal(key.toCharArray(),plain.toCharArray());
//        System.out.println("");
        System.out.println("Substitusi Diagonal : ");
        s.printDiagonal();
        System.out.println("Chiper : "+s.getDiagonalCode());
        System.out.println("Hasil : "+s.getChiper());
    }    
}
