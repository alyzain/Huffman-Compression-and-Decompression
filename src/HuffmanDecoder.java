import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class HuffmanDecoder {
	/*public String decompress(final HuffmanEncodedResult result){
	 	final StringBuilder resultBuilder = new StringBuilder();
	 	Node current = result.getRoot(); 
	 	int  i =0;
	 	while ( i < result.getEncodedData().length()) {
	 		while(!current.isLeaf()) {
	 			char bit = result.getEncodedData().charAt(i);
	 			if(bit == '1') {
	 				current = current.rightChild;
	 			}else if (bit =='0') {
	 				current = current.leftChild;
	 			}else {
	 				throw new IllegalArgumentException("Invalid bit in message! "+ bit);
	 			}
	 			i++;
	 		}
	 		resultBuilder.append(current.character);
	 		current = result.getRoot();
	 	}
		return resultBuilder.toString();
	}*/
	
	/*public String readDecodedFile (File file) throws IOException {
		
		System.out.println("readDecodedFile");
		
		 BufferedReader reader = null;
		 try {
		 reader = new BufferedReader(new FileReader (file));
		 } catch (Exception e) {
			 System.out.println("ERROR!");
		 }
		    String line = null;
		    StringBuilder  stringBuilder = new StringBuilder();
		   // String ls = System.getProperty("line.separator");

		    try {
		        while((line = reader.readLine()) != null) {
		        	System.out.println("line"+line);
		            stringBuilder.append(line);
		     //       stringBuilder.append(ls);
		        }

		        
		    } finally {
		        reader.close();
		    }
		    
		    return stringBuilder.toString();
		
	}*/
	public String readDecodedFile (File file) throws IOException {
		FileInputStream fin = null;
		        try {
		            // create FileInputStream object
		            fin = new FileInputStream(file);
		            byte fileContent[] = new byte[(int)file.length()];

		            // Reads up to certain bytes of data from this input stream into an array of bytes.
		            fin.read(fileContent);
		            //create string from byte array
		            String s = new String(fileContent);
		            System.out.println("File content: " + s);
		            return s;
		        }
		        catch (FileNotFoundException e) {
		            System.out.println("File not found" + e);
		        }
		        catch (IOException ioe) {
		            System.out.println("Exception while reading file " + ioe);
		        }
		        finally{
		            // close the streams using close method
		            try {
		                if (fin != null) {
		                    fin.close();
		                }
		
		            }
		
		            catch (IOException ioe) {
		
		                System.out.println("Error while closing stream: " + ioe);
		            }
		
		        }
				return null;		

	}
	
	public void writeDecodedFile(String decoded, File file) {
		
		System.out.println("writeDecodedFile");
		try {
			FileWriter fileWriter = new FileWriter(file);
			fileWriter.write(decoded);
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String decodeFile(String code, HuffmanEncodedResult result, File file) {
		
		System.out.println("decodeFile");
		System.out.println("code="+code);

		//the string we're going to be adding characters onto
		StringBuilder decoded = new StringBuilder();
		StringBuilder coded = new StringBuilder(code);

		//each loop iteration represents one character in the decoded file, each iteration of this loop starts a new decode on a character 
		while (coded != null) {

			coded = decode(coded, result.getRoot(), decoded);
		}

		//return the decoded string
		writeDecodedFile(decoded.toString(),file);
		System.out.println("decoded="+decoded.toString());
		return decoded.toString();
	}
	
	private StringBuilder decode(StringBuilder code, Node root, StringBuilder decoded) {

		//if it's a leaf, then append the value of the node onto the decoded stringbuilder and return the code without moving the string forward
		if (root.isLeaf()) {

			decoded.append(  String.valueOf( root.getValue() )  );
			return code;
		}

		//else if the string is empty, return a new StrinBuilder obj to break the while loop in the decodeFile function
		else if (code.length() == 0) return null;

		//else if the character at the beginning is a 1, move right
		else if (code.charAt(0) == '1') {

			return decode(code.deleteCharAt(0), root.getRight(), decoded);
		}

		//else if the character at the beginning of the encoded string is a 0, move left
		else if (code.charAt(0) == '0') {

			return decode(code.deleteCharAt(0), root.getLeft(), decoded);
		}

		//if it's not a 0 or a 1, add a newline (a case that is only reached if null is present in the encoded string)
		else  {
			decoded.append("\n");
			return code.deleteCharAt(0);
		}
	}
	
	
}
