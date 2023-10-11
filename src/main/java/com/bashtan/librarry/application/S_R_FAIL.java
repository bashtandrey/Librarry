package com.bashtan.librarry.application;
import javax.crypto.Cipher;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class S_R_FAIL {
    private static File file;
    private static final String KEY_CRYPT ="UkRaInE2023";


    public S_R_FAIL(File file) {
        S_R_FAIL.file = file;
    }

    public void create(String string) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(file);
        outputStream.write(Coder.crypt(Cipher.ENCRYPT_MODE,string.getBytes(StandardCharsets.UTF_8), KEY_CRYPT));
        outputStream.close();
    }

    public  String[] read() throws Exception {
        return new String(Coder.crypt(Cipher.DECRYPT_MODE, Files.readAllBytes(file.toPath()), KEY_CRYPT), StandardCharsets.UTF_8).split("%");
    }
}
