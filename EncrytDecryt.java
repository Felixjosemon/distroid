public static byte[] generateKey(String password) throws Exception
{
    byte[] keyStart = password.getBytes("UTF-8");

    KeyGenerator kgen = KeyGenerator.getInstance("AES");
    SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "Crypto");
    sr.setSeed(keyStart);
    kgen.init(128, sr);
    SecretKey skey = kgen.generateKey();
    return skey.getEncoded();
}

    public static byte[] encodeFile(byte[] key, byte[] fileData) throws Exception
    {

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encrypted = cipher.doFinal(fileData);

        return encrypted;
    }

    public static byte[] decodeFile(byte[] key, byte[] fileData) throws Exception
    {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, skeySpec);

        byte[] decrypted = cipher.doFinal(fileData);

        return decrypted;
    }
    
    //To save a encrypted file to sd :
File file = new File(Environment.getExternalStorageDirectory() + File.separator + "your_folder_on_sd", "file_name");
BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
byte[] yourKey = generateKey("password");
byte[] filesBytes = encodeFile(yourKey, yourByteArrayContainigDataToEncrypt);
bos.write(fileBytes);
bos.flush();
bos.close();
    //To decode file:
byte[] yourKey = generateKey("password");
byte[] decodedData = decodeFile(yourKey, bytesOfYourFile);    
