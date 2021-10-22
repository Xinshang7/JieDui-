package zcy.Utils;

import java.io.*;
import java.util.ArrayList;

public class FileUtil {
    /**
     * 将String内容写入文件
     * @param text 内容
     * @param path 文件路径
     */
    public static void write(String text,String path){
        File file = new File(path);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file,true));
            writer.append(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件，将内容存储于list
     * @param path 文件路径
     * @return 存储内容的list
     */
    public static ArrayList<String> read(String path){
        File file = new File(path);
        BufferedReader reader = null;
        ArrayList<String> list = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine())!=null){
                int index = line.indexOf(".");
                list.add(line.substring(index+2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }
}
