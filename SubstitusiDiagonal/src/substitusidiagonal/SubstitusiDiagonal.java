/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package substitusidiagonal;

/**
 *
 * @author Urip Yogantara
 */
public class SubstitusiDiagonal {
    private final String alfabert="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private char[] tempKode=alfabert.toCharArray();
    private String code=alfabert;
    private char[] key,plainText,diagonalKode,tempKey,chiperText;
    private char[][] diagonal;
    private int width,height;
    
    public SubstitusiDiagonal(char[] key,char[] plainText){
        this.key=key;
        this.plainText=plainText;
        setKey();
        insializeDiagonal();
        setCode();
        setDiagonal();
        setDiagonalCode();
        setChiper();
    }
    
    /*
    fungsi insializeDiagonal() digunakan untuk menginisialisasi panjang dan lebar dari diagonal
    ex : Kunci sama dengan URIP
    maka height=4, dan width=(26/4)+1=7,
    sehingga ukuran diagonal 7x4
    */
    private void insializeDiagonal(){
        height=key.length;
        width=(int) Math.ceil(26.0/height);
        diagonal = new char[width][height];
    }
    
    /*
    fungsi setKey digunakan untuk membuat kunci apabila ada huruf yang sama.
    ex : Key = TEKNIK
    key TEKNIK akan menjadi TEKNI
    */
    private void setKey(){
        boolean isSame;
        int index=0;
        tempKey = key;
        for(int i=0;i<tempKey.length;i++){
            isSame=false;
            for(int j=i-1;j>=0;j--){
                if(tempKey[i]==tempKey[j]){
                    isSame=true;
                    break;
                }
            }
            if(!isSame){
                tempKey[index]=key[i];
                index++;
            }
        }
        
        key = new char[index];
        System.arraycopy(tempKey, 0, key, 0, index);
    }
    
    /*
    fungsi setCode membuat Kode berdasarkan key yang ditentukan
    ex: key= URIP
        fungsi ini akan membawa huruf U,R,I,P ke posisi depan berdasarkan alfabert mulai dari P
        1. huruf P
            a. 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
            b. 'A','B','C','D','E','F','G','H','I','J','K','L','M','N','P','O','Q','R','S','T','U','V','W','X','Y','Z'
            c. 'A','B','C','D','E','F','G','H','I','J','K','L','M','P','N','O','Q','R','S','T','U','V','W','X','Y','Z'
            dst sampai c. 'P','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','Q','R','S','T','U','V','W','X','Y','Z'
        2. huruf I,R,dan P juga sama sehingga kode menjadi
            'U','R','I','P','A','B','C',...'Z'
    */
    private void setCode(){
        char temp;//varible temp untuk menukar huruf
        int keyLength=this.key.length;// panjang dari key ex: URIP =4
        for(int i=keyLength-1;i>=0;i--){
            int index=code.indexOf(key[i]);//menentukan index dari hurup ex: P=16
            
            /*
            fungsi di bawah akan membawa huruf ke posisi 0(paling depan)
            dimana looping akan dilakukan dari index 16(P), sampai 0(A)
            */
            for(int j=index;j>0;j--){
                temp=tempKode[j];
                tempKode[j]=tempKode[j-1];
                tempKode[j-1]=temp;
            }
            //menginisialisasikan kode kembali, karena kode akan berubah dari ABCD..Z menjadi PABCD..Z, sehingga index akan berubah
            code=String.valueOf(tempKode);
        }
    }
    
    //getCode untuk mengambil nilai Kode
    public String getCode(){
        return code;
    }
    
    /*
    fungsi setDiagonal digunakan untuk membuat diagonal sesuai dengan key
    ex : key=TEKNIK
        maka diagonal menjadi
        TAGOUZ
        EBHPV 
        KCJQW 
        NDLRX 
        IFMSY
    
        NB:isi array setelah Z akan menjadi ' '(spasi)
    */
    final void setDiagonal(){
        int index=0;
        for (int i=0;i<width;i++){
            for (int j=0;j<height;j++){
                if(index<26){
                    diagonal[i][j]= tempKode[index];
                }else{
                    diagonal[i][j]= ' ';
                }
                index++;
            }
        }
    }
    
    //menecetak diagonal
    public void printDiagonal(){
        for (int i=0;i<height;i++){
            for (int j=0;j<width;j++){
                System.out.print(diagonal[j][i]);
            }
            System.out.println();
        }
    }
    
    /*membuat diagonal kode
    ex:
    diagonal :  TAGOUZ
                EBHPV 
                KCJQW 
                NDLRX 
                IFMSY
    
    diagonal kode : TEAKBGNCHOIDJPUFLQVZMRWSXY
    */
    private void setDiagonalCode(){
        int diagonalIndex=0;
        diagonalKode= new char[26];
        int ndiags=width+height-1;
            
        for (int diag = 0; diag < ndiags; diag++) {
            int row_stop = Math.max(0,  diag -  width + 1);
            int row_start = Math.min(diag, height - 1);
            for (int row = row_start; row >= row_stop; row--) {
                int col = diag - row;
                if(diagonal[col][row]!=' '){
                    diagonalKode[diagonalIndex]=diagonal[col][row];
                    diagonalIndex++;
                }
            }
        }
    }
    
    /*
    mengambil nilai diagonalCode
    */
    public String getDiagonalCode(){
        return String.valueOf(diagonalKode);
    }
    
    /*
    mengubah plainText menjadi ChiperText
    */
    private void setChiper(){
        int plainLenght=plainText.length;
        chiperText = new char[plainLenght];
        for(int i=0;i<plainLenght;i++){
            int position=alfabert.indexOf(plainText[i]);
            chiperText[i]=diagonalKode[position];
        }
    }
    
    public String getChiper(){
        return String.valueOf(chiperText);
    }
}
