
public class Node implements Comparable<Node>{
		
		public final char character;
		public final int frequency;
		public final Node leftChild;
		public final Node rightChild;

		public Node(final char character,final int frequency,final Node leftChild,final Node rightChild){
			this.character = character;
			this.frequency = frequency;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
		}
		
		boolean isLeaf(){
			if(this.leftChild == null && this.rightChild == null)
				return true;
			else 
				return false;
		}

		@Override
		public int compareTo(final Node o) {
			// TODO Auto-generated method stub
			final int freqComparison = Integer.compare(this.frequency, o.frequency);
				if(freqComparison != 0) {
					return freqComparison;
				}
			return Integer.compare(this.character, o.character);
		}
		
		public char getValue() {
			return this.character;
		}
		public Node getLeft() {
			return this.leftChild;
		}
		public Node getRight() {
			return this.rightChild;
		}
		

		
}
