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

        serializeProgress(saveOne, "save1.dat");
        serializeProgress(saveTwo, "save2.dat");
        serializeProgress(saveThree, "save3.dat");

        addProgressToZip("zip1.zip", "save1.dat", "packed_save1.txt");
        addProgressToZip("zip2.zip", "save2.dat", "packed_save2.txt");
        addProgressToZip("zip3.zip", "save3.dat", "packed_save3.txt");

        File save1 = new File("/Games/savegames/save1.dat");
        File save2 = new File("/Games/savegames/save2.dat");
        File save3 = new File("/Games/savegames/save3.dat");
        save1.delete();
        save2.delete();
        save3.delete();
    }

    private static void serializeProgress(GameProgress progress, String path) {
        try (FileOutputStream fileOutputStream = new FileOutputStream("/Games/savegames/" + path);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(progress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void addProgressToZip(String zipPath, String progressFilePath, String entryName) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream("/Games/savegames/" + zipPath));
             FileInputStream fis = new FileInputStream("/Games/savegames/" + progressFilePath)) {
            ZipEntry entry = new ZipEntry(entryName);
            zipOutputStream.putNextEntry(entry);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            zipOutputStream.write(buffer);
            zipOutputStream.closeEntry();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
