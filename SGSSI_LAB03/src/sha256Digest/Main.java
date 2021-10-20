package sha256Digest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public class Main {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		String path1="C:\\Users\\Enika\\Desktop\\UNI\\4TO\\SGSSI\\SGSSI-21.CB.04.txt";
		String path2="C:\\Users\\Enika\\Desktop\\UNI\\4TO\\SGSSI\\SGSSI-21.CB.04.txtmodifiedtozero1MINGroup.txt";
		modifyFileGroup1Min(path1,"G222430");
		if(comprobarArchivos(path1,path2)) {
			System.out.println("Los dos archivos son correctos");
		}
	}

	private static String sha256Digest(String path) throws IOException, NoSuchAlgorithmException {
		File Archivo = new File(path);
		FileInputStream fis= new FileInputStream(Archivo);
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		
		byte[] byteArray = new byte[1024];
	    int bytesCount = 0; 
	      
	    //Read file data and update in message digest
	    while ((bytesCount = fis.read(byteArray)) != -1) {
	        digest.update(byteArray, 0, bytesCount);
	    };
	     
	    //close the stream; We don't need it now.
	    fis.close();
		
		byte[] encodedhash = digest.digest();
		
		StringBuilder hexString = new StringBuilder();
	    for (int i = 0; i < encodedhash.length; i++) {
	        String hex = Integer.toHexString(0xff & encodedhash[i]);
	        if(hex.length() == 1) {
	            hexString.append('0');
	        }
	        hexString.append(hex);
	    }
		return hexString.toString();
	}
	
	private static void writeChecksum(String path) throws NoSuchAlgorithmException, IOException {
		String sha=sha256Digest(path);
		BufferedReader br = new BufferedReader(new FileReader(path));
		FileWriter fw = new FileWriter(path+"sha256.txt");
		
		String escritor="";
		String linea=br.readLine();
		while (linea!=null) {
			escritor=escritor+linea+System.lineSeparator();
			linea=br.readLine();
		}
		escritor=escritor+sha;
		fw.write(escritor);
		fw.close();
		br.close();
		System.out.println("archivo creado");
	}
	//labo4
	private static String randomString(int length) {
		String AlphaNumericString = "0123456789abcdef";

		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
		
			int index
			= (int)(AlphaNumericString.length()
			    * Math.random());
		
		// add Character one by one in end of sb
		sb.append(AlphaNumericString
		      .charAt(index));
		}

		return sb.toString();
	}
	private static void modifyFileToZero(String path) throws NoSuchAlgorithmException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String escritor="";
		String linea=br.readLine();
		while (linea!=null) {
			escritor=escritor+linea+System.lineSeparator();
			linea=br.readLine();
		}
		boolean encontrado=false;
		String escritoraux=escritor;
		br.close();
		int x=0;
		while(encontrado!=true) {
			x++;
			FileWriter fw = new FileWriter(path+"modifiedtozero.txt");
			String generatedString = randomString(8);		
			escritor=escritoraux+generatedString;
			fw.write(escritor);
			fw.close();
			String sha=sha256Digest(path+"modifiedtozero.txt");
			if(sha.charAt(0)=='0') {
				encontrado=true;
				System.out.println("este es : "+sha);
				System.out.println("este es el codigo :" +generatedString);
				System.out.println("archivo creado");
			}
			else {
				System.out.println("este no es el sha :" + sha);
				System.out.println("y este el el string : " + generatedString);
				File file = new File(path+"modifiedtozero.txt");
				file.delete();
			}
			if(x==15) {
				break;
			}
		}
		System.out.println("este es el num de intentos :" + x);
	}
	
	private static void modifyFileToZero1MIN(String path) throws NoSuchAlgorithmException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String mejorescritor="";
		String escritor="";
		String linea=br.readLine();
		while (linea!=null) {
			escritor=escritor+linea+System.lineSeparator();
			linea=br.readLine();
		}
		String escritoraux=escritor;
		br.close();
		
		long start = System.currentTimeMillis();
		int bestzeros = 0;
		long timeElapsed =0;
		while(timeElapsed < 60000) {
			FileWriter fw = new FileWriter(path+"modifiedtozero1MIN.txt");
			String generatedString = randomString(8);		
			escritor=escritoraux+generatedString;
			fw.write(escritor);
			fw.close();
			String sha=sha256Digest(path+"modifiedtozero1MIN.txt");
			if(sha.charAt(0)=='0') {
				int currentzeros = calcularceros(sha);
				if(bestzeros<currentzeros) {
					bestzeros=currentzeros;
					mejorescritor=escritor;
					System.out.println("este es el mejor sha  " +sha);
				}
				if(currentzeros ==4) {
					System.out.println("sha con 4 ceros es" + sha);
					System.out.println(generatedString);
				}
			}
			File file = new File(path+"modifiedtozero1MIN.txt");
			file.delete();
			long finish = System.currentTimeMillis();
			timeElapsed = finish - start;
		}
		File file = new File(path+"modifiedtozero1MIN.txt");
		file.delete();
		FileWriter fwtmp = new FileWriter(path+"modifiedtozero1MIN.txt");
		fwtmp.write(mejorescritor);
		fwtmp.close();
		System.out.println("archivo creado");
	}
	
	private static void modifyFileGroup1Min(String path,String group) throws NoSuchAlgorithmException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
		String mejorescritor="";
		String escritor="";
		String linea=br.readLine();
		while (linea!=null) {
			escritor=escritor+linea+System.lineSeparator();
			linea=br.readLine();
		}
		String escritoraux=escritor;
		br.close();
		
		long start = System.currentTimeMillis();
		int bestzeros = 0;
		long timeElapsed =0;
		while(timeElapsed < 600000) {
			FileWriter fw = new FileWriter(path+"modifiedtozero1MINGroup.txt");
			String generatedString = randomString(8);		
			escritor=escritoraux+generatedString+" "+group;
			fw.write(escritor);
			fw.close();
			String sha=sha256Digest(path+"modifiedtozero1MINGroup.txt");
			if(sha.charAt(0)=='0') {
				int currentzeros = calcularceros(sha);
				if(bestzeros<currentzeros) {
					bestzeros=currentzeros;
					mejorescritor=escritor;
					System.out.println("este es el mejor sha  " +sha);
				}
				if(currentzeros ==4) {
					System.out.println("sha con 4 ceros es" + sha);
					System.out.println(generatedString);
				}
			}
			File file = new File(path+"modifiedtozero1MINGroup.txt");
			file.delete();
			long finish = System.currentTimeMillis();
			timeElapsed = finish - start;
		}
		File file = new File(path+"modifiedtozero1MINGroup.txt");
		file.delete();
		FileWriter fwtmp = new FileWriter(path+"modifiedtozero1MINGroup.txt");
		fwtmp.write(mejorescritor);
		fwtmp.close();
		System.out.println("archivo creado");
	}
	//path1 es el archivo original
	//path2 es el nuevo archivo con el HEX
	private static boolean comprobarArchivos(String path1,String path2) throws IOException, NoSuchAlgorithmException {
			BufferedReader br1 = new BufferedReader(new FileReader(path1));
			BufferedReader br2 = new BufferedReader(new FileReader(path2));
			
			String line1 = br1.readLine();
			String line2 = br2.readLine();
			String sha=sha256Digest(path2);
			if(sha.charAt(0)!='0') {
				return false;
			}
			while((line1 != null) && (line2 != null)) {
				if(!line1.equals(line2))
					return false;
				line1=br1.readLine();
				line2=br2.readLine();
			}
			
			if(line1 == null) {
				if(!line2.matches("-?[0-9a-fA-F]{8}+\\s+G([0-3][0-9])+"))
					return false;
			}
			
			br1.close();
			br2.close();
			
			return true;
		}
	private static int calcularceros(String sha) {
		int ceros = 0;
		for (int i = 0; i < sha.length(); i++) {
			if (sha.charAt(i)=='0') {
				ceros++;
			}
			else {
				break;
			}
		}
		return ceros;
	}
}
