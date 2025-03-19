package protune.controller.io;

import java.io.*;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class FileIOSystem {

    private static FileIOSystem FileIOSystemInstance;
    private FileIOSystem() {
    }
    public static FileIOSystem getInstance() {
        if (FileIOSystemInstance == null) {
            FileIOSystemInstance = new FileIOSystem();
        }
        return FileIOSystemInstance;
    }
    public static <T> List<T> read(String filePath) throws IOException, ClassNotFoundException {
        List<T> list = new ArrayList<>();
        FileInputStream fileStream = null;
        ObjectInputStream a = null;
        try {
            fileStream = new FileInputStream(new File(filePath));
            a = new ObjectInputStream(fileStream);
            list = (List<T>) a.readObject();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("File not found!");
        } catch (IOException e) {
//            throw new IOException("Read file failed!");
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Class not found!");
        } finally {
            if(a != null) a.close();
            if(fileStream != null) fileStream.close();
        }
        return list;
    }

    public static <T> void write( List<T> list, String filePath) {
        FileOutputStream fileStream = null;
        ObjectOutputStream a = null;
        try {
            fileStream = new FileOutputStream(new File(filePath));
            a = new ObjectOutputStream(fileStream);
            a.writeObject(list);
            System.out.println("Write file " + filePath + " successfully!");
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        } catch (SocketException e) {
            System.out.println("socket exception");
        } catch (UnknownHostException e) {
            System.out.println("unknown host");
        } catch (IOException e) {
            System.out.println("write failed");
        } finally {
            try {
                a.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                fileStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static <T> void clearData(String filePath) throws IOException {
        List<T> list = new ArrayList<>();
        FileOutputStream fileStream = null;
        ObjectOutputStream a = null;
        try {
            fileStream = new FileOutputStream(filePath);
            a = new ObjectOutputStream(fileStream);
            a.writeObject(list);

        } catch (IOException e) {
            throw new IOException("Clear data failed!");
        } finally {
            a.close();
            fileStream.close();
        }
    }
}