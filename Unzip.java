package Files2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Unzip {
    public static void main(String[] args) {
        openZip("zip1.zip", "unpacked_save1.dat");
        openProgress("unpacked_save1.dat");
    }

    private static void openZip(String zipPath, String rename) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream("/Games/savegames/" + zipPath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                FileOutputStream fileOutputStream = new FileOutputStream("/Games/savegames/" + rename);
                for (int c = zipInputStream.read(); c != -1; c = zipInputStream.read()) {
                    fileOutputStream.write(c);
                }
                fileOutputStream.flush();
                zipInputStream.closeEntry();
                fileOutputStream.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void openProgress(String path) {
        GameProgress progress = null;
        try (FileInputStream fileInputStream = new FileInputStream("/Games/savegames/" + path);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            progress = (GameProgress) objectInputStream.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(progress);
    }
}
