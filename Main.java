package Files2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) {
        GameProgress saveOne = new GameProgress(100, 2, 15, 539.12);
        GameProgress saveTwo = new GameProgress(90, 3, 25, 755.72);
        GameProgress saveThree = new GameProgress(40, 7, 75, 1445.772);

        try (FileOutputStream fileOutputStream = new FileOutputStream("C:/Games/savegames/save1.dat");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(saveOne);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream("C:/Games/savegames/save2.dat");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(saveTwo);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream("C:/Games/savegames/save3.dat");
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(saveThree);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("C:/Games/savegames/zip1.zip"));
             FileInputStream fis = new FileInputStream("C:/Games/savegames/save1.dat")) {
            ZipEntry entry = new ZipEntry("packed_save1.txt");
            zipOutputStream.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zipOutputStream.write(buffer);
            zipOutputStream.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("C:/Games/savegames/zip2.zip"));
             FileInputStream fis = new FileInputStream("C:/Games/savegames/save2.dat")) {
            ZipEntry entry = new ZipEntry("packed_save2.txt");
            zipOutputStream.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zipOutputStream.write(buffer);
            zipOutputStream.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("C:/Games/savegames/zip3.zip"));
             FileInputStream fis = new FileInputStream("C:/Games/savegames/save3.dat")) {
            ZipEntry entry = new ZipEntry("packed_save3.txt");
            zipOutputStream.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zipOutputStream.write(buffer);
            zipOutputStream.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        File save1 = new File("C:/Games/savegames/save1.dat");
        File save2 = new File("C:/Games/savegames/save2.dat");
        File save3 = new File("C:/Games/savegames/save3.dat");
        save1.delete();
        save2.delete();
        save3.delete();
    }
}
