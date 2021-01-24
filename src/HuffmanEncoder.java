import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore.Entry;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HuffmanEncoder {
	
	public static byte[] myBytes;
	  private static final int ALPHABET_SIZE = 256;

	  public static int[] buildFrequencyTable(File file) throws IOException{
			
		  FileInputStream newInput = new FileInputStream(file);
		  char character ;
		  final int[] freq = new int[ALPHABET_SIZE]; 
		  while(newInput.available() > 0){
			  character = (char) newInput.read();
					freq[character]++;			
					
		}
		  return freq;
	  }
	    public static Node buildHuffmanTree(int[] freq){
	    	
			final PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
			for(int i = 0; i < ALPHABET_SIZE; i++){
				if(freq[i]> 0){
					priorityQueue.add(new Node((char) i,freq[i], null, null));
				}
			}
			while(priorityQueue.size() > 1){
				
				final Node left = priorityQueue.poll();
				final Node right = priorityQueue.poll();
				final Node parent = new Node('\0', left.frequency+ right.frequency ,left, right);
				priorityQueue.add(parent);
			
			}
			
			return priorityQueue.poll();
	}
	    
	    public static Map<Character, String> buildLookupTable (final Node root) throws UnsupportedEncodingException{
			
	    	final Map<Character,String> lookUpTable = new HashMap<>();
			buildLookUpImpl(root,"",lookUpTable);
		
			return lookUpTable;
	}
	
	    public static void buildLookUpImpl(final Node node, final String s,final Map<Character,String> lookUpTable) throws UnsupportedEncodingException{
			
			if(!node.isLeaf()){
				buildLookUpImpl(node.leftChild, s+'0' ,lookUpTable);
				buildLookUpImpl(node.rightChild, s+'1' ,lookUpTable);
			}else{
				lookUpTable.put(node.character, s);
			}	
		}
	  
		public HuffmanEncodedResult compress(File file) throws IOException{
			
			final int[] freq = buildFrequencyTable(file);
			final Node root = buildHuffmanTree(freq);
			final Map<Character , String> lookupTable = buildLookupTable(root);
			String generatedData = genertEncodedData(file, lookupTable);
			byte[] bytes = getItsBytes(generatedData);
			//writeToFile(lookupTable, file);
			writeBytesToFile(bytes,file);
			return new HuffmanEncodedResult(generatedData,root);
		}

		public static String genertEncodedData(File file, Map<Character, String> lookupTable) throws IOException {
			
			final StringBuilder builder = new StringBuilder();
			FileInputStream newInput = new FileInputStream(file);
			char character; 
			while(newInput.available() > 0)
			{character = (char) newInput.read(); 
			builder.append(lookupTable.get(character));}
			return builder.toString();
		}
		public byte[] getItsBytes(String encodedResultData){
			BitSet bitset = new BitSet(encodedResultData.length()); 
			for(int i = 0; i < encodedResultData.length(); i++)
			{
				if(encodedResultData.charAt(i) == '1')
					bitset.set(i);
			}
			byte[] bytes = new byte[(bitset.length()/8 +1)];
			for(int i=0; i< bitset.length();i++){

				if(bitset.get(i)){ 
					bytes[i/8] |= 1 << (7 - i % 8);
				//System.out.println("bytes["+i/8+"] =" + bytes[i/8]);
					}
			}
			/*for(int j=0; j<bytes.length; j++ ) {
				System.out.println(bytes[j]);
			}
			*/
			//myBytes = bytes;
			return bytes;  
		}
		
		public static void writeBytesToFile (byte[] bytes, File file) throws IOException {
			FileOutputStream fileOutput = new FileOutputStream (file, false);
			fileOutput.write(bytes);
		}
		
		/*private static void writeToFile(Map<Character, String> lookuptable, File file) {
			
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,false))) {
				boolean firstLine = true;
				for (Map.Entry<Character, String> entry : lookuptable.entrySet())
				{
					if (firstLine)
						bw.write(entry.getKey() + " " + entry.getValue());
					else
						bw.write("\n" + entry.getKey() + " " + entry.getValue());
				}
				
				
				/*Iterator it = lookuptable.entrySet().iterator();
				
				while(it.hasNext()){
					Entry entry = (Entry) it.next();
					String content ="";
					if(firstLine){
						content = (Character) entry.getKey() + " " + (String)  entry.getValue();
						firstLine = false;
					}
					else
						content = "\n" + Integer.toString((int) entry.getKey()) + " " + (String) entry.getValue();
					bw.write(content);
				}
				
				bw.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}*/
}