
public class HuffmanEncodedResult {
	final Node root;
	final String encodedData;
	HuffmanEncodedResult(final String encodedData, final Node root){
this.encodedData = encodedData;
this.root = root;
}
public Node getRoot() {
	return this.root;
}
public String getEncodedData() {
	return this.encodedData;
}
}
