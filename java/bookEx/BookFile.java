package a0128.bookEx;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class BookFile {
    private File file;
    private String dir;
    private String fileName;

    public BookFile(){
        file = new File("d:\\");
    }

    public BookFile(String dir, String fName){
        file = new File("d:\\"+dir+"\\"+fName+".txt");
        this.dir = "d:\\"+dir;
        fileName = fName+".txt";
    }

    public void create() throws Exception {
        boolean exist = check(file);
        if(exist){
            file.delete();
            file.createNewFile();
        } else {
            file = new File(dir);
            file.mkdir();
            file = new File(dir+"\\"+fileName);
            file.createNewFile();
        }
    }

    private boolean check(File f) {
        if(f.exists()){
            return true;
        }
        return false;
    }

    public void write(String str) throws Exception {
        FileWriter fw = new FileWriter(file);
        PrintWriter pw = new PrintWriter(fw);

        pw.println(str);
        fw.close();
    }

    public void read() throws Exception {
        boolean exist = check(file);
        if(exist){
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String str;
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }
            br.close();
        } else {
            System.out.println("읽을 파일이 없습니다");
        }
    }

}
