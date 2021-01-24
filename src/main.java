import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

public class main{
	/* public static void printMap(Map mp) {
	    Iterator it = mp.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        it.remove(); // avoids a ConcurrentModificationException
		    }
		}*/
	public static void main(String[] args) throws IOException{
	
		Scanner input = new Scanner(System.in);
		System.out.println("Filename:");
		String fileName = input.nextLine();
		File file = new File("//Users//hanaelgoweini//Desktop//"+fileName);
		System.out.println("1-Compress   2-Decompress");
		int choice = input.nextInt();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		
	
	/*parse the file*/
		final HuffmanEncoder encoder = new HuffmanEncoder();
		long start = System.nanoTime();
		FileInputStream fileinputstream = new FileInputStream(file);
		long initLength = fileinputstream.getChannel().size();
		final HuffmanEncodedResult result = encoder.compress(file);
		long end = System.nanoTime();
		long newLength = fileinputstream.getChannel().size();
		long ratio = newLength/initLength ;
		System.out.println("Compression Ratio: "+ ratio);
		long duration = end - start;
		System.out.println("Compression Duration: "+duration);
		 //final HuffmanDecoder decoder = new HuffmanDecoder();
		 //String s= decoder.readDecodedFile(file);
		 //System.out.println("s="+s);
		// String s = new String(HuffmanEncoder.myBytes);
		/* String s = "";
		 for(int i=0; i < HuffmanEncoder.myBytes.length; i++){
		 	System.out.println(HuffmanEncoder.myBytes[i]);
			 if(HuffmanEncoder.myBytes[i] == (byte)1){
		 		s +="1";
		 	}else{
		 		s +="0";
		 	}
		 }
		 System.out.println("s="+s);
		 String decoded = decoder.decodeFile(s, result, file);*/
		
		
	/*switch(choice) {
	case 1:
		final HuffmanEncoder encoder = new HuffmanEncoder();
		 encoder.compress(file);
	case 2:
		final HuffmanDecoder decoder = new HuffmanDecoder();
	}*/
	  
	  
	  
	 
	  //System.out.println("encoded message =" +result.encodedData);
	 // System.out.println("unencoded message =" +decoder.decompress(result));
	  
	  
// for(int i = 0;i<256;i++) {
// System.out.println(ft[i]);
// System.out.println(i);
// }
//	 System.out.println(n.character);
//	 while(!n.isLeaf()) {
//		 System.out.println(n.character);
//		 n = n.rightChild;
//	 }  
}
}