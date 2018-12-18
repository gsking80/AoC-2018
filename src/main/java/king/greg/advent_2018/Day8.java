package king.greg.advent_2018;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Day8 {
	
	String[] numbersArray;
	int currentPos = 0;
	int sumMetaData = 0;
	List<Node> nodes;
	
	public void createTree(final FileReader fileReader) {
		try {
			final BufferedReader buf = new BufferedReader(fileReader);
			
			while(true) {
				final String lineJustFetched = buf.readLine();
				if(null == lineJustFetched) {
					break;
				} else {
					numbersArray = lineJustFetched.split(" ");
				}
			}
		} catch (IOException ioe) {
			
		}
		nodes = addNodes(1);
	}
	
	public List<Node> addNodes(final int nodes) {
		final List<Node> nodeList = new ArrayList<Node>();
		for (int i = 0; i < nodes; i++) {
			final Node currentNode = new Node();
			final int numChildren = Integer.valueOf(numbersArray[currentPos++]);
			final int numMetaData = Integer.valueOf(numbersArray[currentPos++]);
			currentNode.setChildren(addNodes(numChildren));
			final List<Integer> metaData = new ArrayList<Integer>();
			for (int j = 0; j < numMetaData; j++) {
				metaData.add(Integer.valueOf(numbersArray[currentPos++]));
			}
			currentNode.setMetadata(metaData);
			nodeList.add(currentNode);
		}
		return nodeList;
	}
	
	public int getMetaDataSum() {
		return nodes.get(0).getMetaDataSum();
	}
	
	public int getValue() {
		return nodes.get(0).getNodeValue();
	}
	
	public int getNodeValue(final Node node) {
		int value = 0;
		for (Integer metadata : node.getMetadata()) {			
			if (node.getChildren().isEmpty()) {
				value += metadata;
			} else if (metadata <= node.getChildren().size()){
				value += getNodeValue(node.getChildren().get(metadata - 1));
			}
		}
		return value;
	}
	
	class Node {
		private List<Node> children;
		private List<Integer> metadata;
		
		public List<Node> getChildren() {
			return children;
		}
		public void setChildren(List<Node> children) {
			this.children = children;
		}
		public List<Integer> getMetadata() {
			return metadata;
		}
		public void setMetadata(List<Integer> metadata) {
			this.metadata = metadata;
		}
		
		public int getNodeValue() {
			int value = 0;
			for (Integer metadata : getMetadata()) {			
				if (getChildren().isEmpty()) {
					value += metadata;
				} else if (metadata <= getChildren().size()){
					value += getChildren().get(metadata - 1).getNodeValue();
				}
			}
			return value;
		}
		
		public int getMetaDataSum() {
			int sum = 0;
			for (final Node node: getChildren()) {
				sum += node.getMetaDataSum();
			}
			for (final Integer data: getMetadata()) {
				sum += data;
			}
			return sum;
		}
		
	}

}
